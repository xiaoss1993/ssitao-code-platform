package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.dal.dataobject.FileInfoDO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param file         文件
     * @param businessType 业务类型
     * @return 文件访问URL
     */
    String uploadFile(MultipartFile file, String businessType);

    /**
     * 删除文件
     *
     * @param fileId 文件ID
     */
    void deleteFile(Long fileId);

    /**
     * 批量删除文件
     *
     * @param fileIds 文件ID列表
     */
    void deleteFiles(List<Long> fileIds);

    /**
     * 下载文件
     *
     * @param fileId   文件ID
     * @param response 响应
     */
    void downloadFile(Long fileId, HttpServletResponse response) throws IOException;

    /**
     * 获取文件信息
     *
     * @param fileId 文件ID
     */
    FileInfoDO getFileInfo(Long fileId);

}
