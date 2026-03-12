package com.ssitao.code.modular.iam.menu.infrastructure.converter;

import com.ssitao.code.modular.iam.menu.api.dto.IamMenuDTO;
import com.ssitao.code.modular.iam.menu.domain.model.IamMenu;
import com.ssitao.code.modular.iam.menu.dal.dataobject.IamMenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * IAM菜单对象转换器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IamMenuConverter {

    /**
     * LocalDateTime 转换为字符串
     */
    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 字符串转换为 LocalDateTime
     */
    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            // 解析失败，返回 null
            return null;
        }
    }

    /**
     * Boolean 转换为 Integer (true=1, false=0)
     */
    @Named("booleanToInteger")
    default Integer booleanToInteger(Boolean value) {
        if (value == null) {
            return null;
        }
        return value ? 1 : 0;
    }

    /**
     * Integer 转换为 Boolean (1=true, 0=false)
     */
    @Named("integerToBoolean")
    default Boolean integerToBoolean(Integer value) {
        if (value == null) {
            return null;
        }
        return value == 1;
    }

    /**
     * DO转领域模型
     */
    @Mappings({
        @Mapping(source = "menuId", target = "menuId"),
        @Mapping(source = "menuCode", target = "menuCode"),
        @Mapping(source = "menuName", target = "menuName"),
        @Mapping(source = "menuType", target = "menuType"),
        @Mapping(source = "menuParentId", target = "menuParentId"),
        @Mapping(source = "menuPath", target = "menuPath"),
        @Mapping(source = "menuComponent", target = "menuComponent"),
        @Mapping(source = "menuIcon", target = "menuIcon"),
        @Mapping(source = "menuSort", target = "menuSort"),
        @Mapping(source = "menuIsVisible", target = "menuIsVisible"),
        @Mapping(source = "menuIsCached", target = "menuIsCached"),
        @Mapping(source = "menuIsAffix", target = "menuIsAffix"),
        @Mapping(source = "menuPermission", target = "menuPermission"),
        @Mapping(source = "menuRedirect", target = "menuRedirect"),
        @Mapping(source = "menuDesc", target = "menuDesc"),
        @Mapping(source = "menuStatus", target = "menuStatus"),
        @Mapping(source = "menuIsBuiltin", target = "menuIsBuiltin"),
        @Mapping(source = "menuTreePath", target = "menuTreePath"),
        @Mapping(source = "menuTreeLevel", target = "menuTreeLevel"),
        @Mapping(target = "children", ignore = true)
    })
    IamMenu toDomain(IamMenuDO menuDO);

    /**
     * 领域模型转DO
     */
    @Mappings({
        @Mapping(source = "id", target = "menuId"),
        @Mapping(source = "menuCode", target = "menuCode"),
        @Mapping(source = "menuName", target = "menuName"),
        @Mapping(source = "menuType", target = "menuType"),
        @Mapping(source = "menuParentId", target = "menuParentId"),
        @Mapping(source = "menuPath", target = "menuPath"),
        @Mapping(source = "menuComponent", target = "menuComponent"),
        @Mapping(source = "menuIcon", target = "menuIcon"),
        @Mapping(source = "menuSort", target = "menuSort"),
        @Mapping(source = "menuIsVisible", target = "menuIsVisible"),
        @Mapping(source = "menuIsCached", target = "menuIsCached"),
        @Mapping(source = "menuIsAffix", target = "menuIsAffix"),
        @Mapping(source = "menuPermission", target = "menuPermission"),
        @Mapping(source = "menuRedirect", target = "menuRedirect"),
        @Mapping(source = "menuDesc", target = "menuDesc"),
        @Mapping(source = "menuStatus", target = "menuStatus"),
        @Mapping(source = "menuIsBuiltin", target = "menuIsBuiltin"),
        @Mapping(source = "menuTreePath", target = "menuTreePath"),
        @Mapping(source = "menuTreeLevel", target = "menuTreeLevel")
    })
    IamMenuDO toDO(IamMenu domain);

    /**
     * DO转DTO
     */
    @Mappings({
        @Mapping(source = "menuId", target = "id"),
        @Mapping(source = "menuParentId", target = "parentId"),
        @Mapping(source = "menuName", target = "menuName"),
        @Mapping(source = "menuType", target = "menuType"),
        @Mapping(source = "menuPath", target = "path"),
        @Mapping(source = "menuComponent", target = "component"),
        @Mapping(source = "menuPermission", target = "permission"),
        @Mapping(source = "menuIcon", target = "icon"),
        @Mapping(source = "menuSort", target = "sortOrder"),
        @Mapping(source = "menuIsVisible", target = "visible"),
        @Mapping(source = "menuStatus", target = "status")
    })
    IamMenuDTO toDTO(IamMenuDO menuDO);

    /**
     * 领域模型转DTO
     */
    @Mappings({
        @Mapping(source = "menuId", target = "id"),
        @Mapping(source = "menuParentId", target = "parentId"),
        @Mapping(source = "menuName", target = "menuName"),
        @Mapping(source = "menuType", target = "menuType"),
        @Mapping(source = "menuPath", target = "path"),
        @Mapping(source = "menuComponent", target = "component"),
        @Mapping(source = "menuPermission", target = "permission"),
        @Mapping(source = "menuIcon", target = "icon"),
        @Mapping(source = "menuSort", target = "sortOrder"),
        @Mapping(source = "menuIsVisible", target = "visible"),
        @Mapping(source = "menuStatus", target = "status")
    })
    IamMenuDTO toDTO(IamMenu domain);

    /**
     * DO列表转领域模型列表
     */
    List<IamMenu> toDomainList(List<IamMenuDO> menuDOList);

    /**
     * 领域模型列表转DTO列表
     */
    List<IamMenuDTO> toDTOList(List<IamMenu> domainList);

}
