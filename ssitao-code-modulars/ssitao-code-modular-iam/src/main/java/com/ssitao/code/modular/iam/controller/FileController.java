package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.dal.dataobject.FileInfoDO;
import com.ssitao.code.modular.iam.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 文件管理控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "管理后台 - 文件管理")
@RestController
@RequestMapping("/iam/file")
@RequiredArgsConstructor
@Validated
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    public CommonResult<String> uploadFile(
            @Parameter(description = "文件", required = true)
            @RequestParam("file") MultipartFile file,
            @Parameter(description = "业务类型")
            @RequestParam(value = "businessType", required = false) String businessType) {
        String url = fileService.uploadFile(file, businessType);
        return success(url);
    }

    @DeleteMapping("/delete/{fileId}")
    @Operation(summary = "删除文件")
    @Parameter(name = "fileId", description = "文件ID", required = true)
    public CommonResult<Void> deleteFile(@PathVariable("fileId") Long fileId) {
        fileService.deleteFile(fileId);
        return success();
    }

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除文件")
    public CommonResult<Void> deleteFiles(@RequestBody List<Long> fileIds) {
        fileService.deleteFiles(fileIds);
        return success();
    }

    @GetMapping("/download/{fileId}")
    @Operation(summary = "下载文件")
    @Parameter(name = "fileId", description = "文件ID", required = true)
    public void downloadFile(@PathVariable("fileId") Long fileId, HttpServletResponse response) throws IOException {
        fileService.downloadFile(fileId, response);
    }

    @GetMapping("/get/{fileId}")
    @Operation(summary = "获取文件信息")
    @Parameter(name = "fileId", description = "文件ID", required = true)
    public CommonResult<FileInfoDO> getFileInfo(@PathVariable("fileId") Long fileId) {
        FileInfoDO fileInfo = fileService.getFileInfo(fileId);
        return success(fileInfo);
    }

}
