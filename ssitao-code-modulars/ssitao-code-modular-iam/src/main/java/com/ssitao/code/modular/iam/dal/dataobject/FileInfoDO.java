package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文件信息数据对象
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Table(value = "iam_file")
public class FileInfoDO {

    /**
     * 文件ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件URL
     */
    private String url;

    /**
     * 文件大小（字节）
     */
    private Long size;

    /**
     * 文件类型（MIME类型）
     */
    private String type;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 存储类型：1-本地 2-阿里云OSS 3-MinIO
     */
    private Integer storageType;

    /**
     * 上传用户ID
     */
    private Long uploaderId;

    /**
     * 上传用户名
     */
    private String uploaderName;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否删除：0-否 1-是
     */
    private Integer deleted;

}
