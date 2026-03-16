package com.ssitao.code.modular.file.service;

import com.ssitao.code.modular.file.entity.FileInfoEntity;
import com.ssitao.code.modular.common.service.CrudService;

/**
 * 文件信息 服务类
 *
 *
 */
public interface FileInfoService extends CrudService<FileInfoEntity, String> {
    FileInfoEntity selectByMd5(String md5);

    FileInfoEntity selectByIdOrMd5(String idOrMd5);
}
