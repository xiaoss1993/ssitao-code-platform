package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.dal.dataobject.FileInfoDO;
import com.ssitao.code.modular.iam.dal.mapper.FileMapper;
import com.ssitao.code.modular.iam.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 文件服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    @Value("${file.upload.path:D:/upload}")
    private String uploadPath;

    @Value("${file.upload.url-prefix:/files}")
    private String urlPrefix;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadFile(MultipartFile file, String businessType) {
        if (file.isEmpty()) {
            throw new BusinessException("文件不能为空");
        }

        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            if (!StringUtils.hasText(originalFilename)) {
                throw new BusinessException("文件名不能为空");
            }

            // 获取文件扩展名
            String extension = "";
            int dotIndex = originalFilename.lastIndexOf(".");
            if (dotIndex > 0) {
                extension = originalFilename.substring(dotIndex + 1);
            }

            // 生成文件路径（按日期分目录）
            String datePath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String fileName = System.currentTimeMillis() + "_" + originalFilename;
            String relativePath = datePath + "/" + fileName;
            String fullPath = uploadPath + "/" + relativePath;

            // 确保目录存在
            Path filePath = Paths.get(fullPath);
            Files.createDirectories(filePath.getParent());

            // 保存文件
            file.transferTo(filePath);

            // 创建文件记录
            FileInfoDO fileInfo = new FileInfoDO();
            fileInfo.setName(originalFilename);
            fileInfo.setPath(relativePath);
            fileInfo.setUrl(urlPrefix + "/" + relativePath);
            fileInfo.setSize(file.getSize());
            fileInfo.setType(file.getContentType());
            fileInfo.setExtension(extension);
            fileInfo.setStorageType(1); // 本地存储

            LoginUser loginUser = SecurityUtil.getLoginUser();
            if (loginUser != null) {
                fileInfo.setUploaderId(loginUser.getId());
                fileInfo.setUploaderName(loginUser.getUsername());
                fileInfo.setTenantId(loginUser.getTenantId());
            }

            fileInfo.setBusinessType(businessType);
            fileInfo.setCreateTime(LocalDateTime.now());
            fileInfo.setDeleted(0);

            fileMapper.insert(fileInfo);
            return fileInfo.getUrl();

        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new BusinessException("文件上传失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFile(Long fileId) {
        FileInfoDO fileInfo = fileMapper.selectOneById(fileId);
        if (fileInfo == null) {
            throw new NotFoundException("文件不存在: " + fileId);
        }

        // 删除物理文件
        try {
            String fullPath = uploadPath + "/" + fileInfo.getPath();
            Files.deleteIfExists(Paths.get(fullPath));
        } catch (IOException e) {
            log.warn("删除物理文件失败: path={}", fileInfo.getPath(), e);
        }

        // 逻辑删除记录
        fileInfo.setDeleted(1);
        fileMapper.update(fileInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFiles(List<Long> fileIds) {
        if (fileIds == null || fileIds.isEmpty()) {
            return;
        }

        for (Long fileId : fileIds) {
            try {
                deleteFile(fileId);
            } catch (Exception e) {
                log.warn("删除文件失败: fileId={}", fileId, e);
            }
        }
    }

    @Override
    public void downloadFile(Long fileId, HttpServletResponse response) throws IOException {
        FileInfoDO fileInfo = fileMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", fileId)
                        .eq("deleted", 0)
        );

        if (fileInfo == null) {
            throw new NotFoundException("文件不存在: " + fileId);
        }

        String fullPath = uploadPath + "/" + fileInfo.getPath();
        Path filePath = Paths.get(fullPath);

        if (!Files.exists(filePath)) {
            throw new NotFoundException("文件不存在");
        }

        // 设置响应头
        response.setContentType(fileInfo.getType());
        response.setHeader("Content-Disposition", "attachment; filename=" + fileInfo.getName());
        response.setContentLengthLong(fileInfo.getSize());

        // 写入文件流
        Files.copy(filePath, response.getOutputStream());
    }

    @Override
    public FileInfoDO getFileInfo(Long fileId) {
        FileInfoDO fileInfo = fileMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", fileId)
                        .eq("deleted", 0)
        );

        if (fileInfo == null) {
            throw new NotFoundException("文件不存在: " + fileId);
        }

        return fileInfo;
    }

}
