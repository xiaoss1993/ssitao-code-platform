package com.ssitao.code.modular.devtools.web;

import com.ssitao.code.modular.devtools.reader.FileInfo;
import com.ssitao.code.modular.devtools.writer.CodeWriter;
import com.ssitao.code.modular.devtools.writer.GeneratedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import com.tweb.frame.authorization.annotation.Authorize;
import com.tweb.commons.controller.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @since 3.0
 */
@RestController
@RequestMapping("/dev/tools/file")
@Authorize(permission = "dev-tools", description = "开发人员工具-文件管理")
@Api(tags = "开发人员工具-文件管理", value = "开发人员工具-文件管理")
public class FileManagerDevToolsController {

    @Autowired
    private CodeWriter codeWriter;

    @PostMapping("/write")
    @ApiOperation("写出文件")
    @Authorize(action = "write", description = "写出文件")
    public ResponseMessage<String> write(@RequestBody List<GeneratedCode> codes) {
        return ResponseMessage.ok(codeWriter.write(codes));
    }

    @GetMapping("/list")
    @ApiOperation("获取目录下的全部文件,不包含子目录")
    @Authorize(action = "read", description = "读取文件")
    public ResponseMessage<List<FileInfo>> write(@RequestParam String path) {
        File file = new File(path);
        if (!file.exists()) {
            return ResponseMessage.error(404,"文件不存在");
        }
        List<FileInfo> list;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                list = new java.util.ArrayList<>();
            } else {
                list = Stream.of(files)
                        .map(FileInfo::from)
                        .collect(Collectors.toList());
            }
        } else {
            list = Collections.singletonList(FileInfo.from(file));
        }
        return ResponseMessage.ok(list);
    }

    @GetMapping("/read")
    @ApiOperation("读取文本文件内容")
    @SneakyThrows
    @Authorize(action = "read", description = "读取文件")
    public ResponseMessage<String> read(@RequestParam String file) {
        File fileInfo = new File(file);
        if (!fileInfo.exists()) {
            return ResponseMessage.error(404,"文件不存在");
        }
        if (fileInfo.length() > 2 * 1024 * 1024 * 1024L) {
            return ResponseMessage.error(500, "文件过大,无法读取");
        }
        List<String> list = Files.readAllLines(Paths.get(file));
        StringJoiner joiner = new StringJoiner("\n");
        list.forEach(joiner::add);
        return ResponseMessage.ok(joiner.toString());
    }

}
