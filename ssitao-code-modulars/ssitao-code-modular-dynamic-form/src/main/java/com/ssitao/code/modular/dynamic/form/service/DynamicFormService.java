package com.ssitao.code.modular.dynamic.form.service;

import com.ssitao.code.modular.dynamic.form.entity.DynamicFormColumnBindEntity;
import com.ssitao.code.modular.dynamic.form.entity.DynamicFormColumnEntity;
import com.ssitao.code.modular.dynamic.form.entity.DynamicFormEntity;
import com.ssitao.code.commons.service.CrudService;

import java.util.List;

/**
 * 动态表单 服务类
 *
 *
 */
public interface DynamicFormService extends CrudService<DynamicFormEntity, String> {
    void deployAllFromLog();

    void deployAll();

    void deploy(String formId);

    void unDeploy(String formId);

    String saveOrUpdate(DynamicFormColumnBindEntity bindEntity);

    String saveOrUpdateColumn(DynamicFormColumnEntity columnEntity);

    List<String> saveOrUpdateColumn(List<DynamicFormColumnEntity> columnEntities);

    DynamicFormColumnEntity deleteColumn(String id);

    List<DynamicFormColumnEntity> deleteColumn(List<String> ids);

    List<DynamicFormColumnEntity> selectColumnsByFormId(String formId);

    DynamicFormColumnBindEntity selectLatestDeployed(String formId);

    DynamicFormColumnBindEntity selectEditing(String formId);

    DynamicFormColumnBindEntity selectDeployed(String formId, int version);

    long selectDeployedVersion(String formId);
}
