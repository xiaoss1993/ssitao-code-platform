package com.ssitao.code.modular.codegen.infrastructure.persistence.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表功能配置 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_table_service_config")
public class TbCodeTableServiceConfig implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 对应表
     */
    private Integer tableId;

    /**
     * 功能 
     */
    private String type;

    /**
     * 是否启用改功能
     */
    private Integer isEnable;

    /**
     * 是否需要授权
     */
    private Integer isPermission;

    /**
     * 是否开启事务
     */
    private Integer isTransactional;

    /**
     * 事务类型
     */
    private String transactionalType;

}
