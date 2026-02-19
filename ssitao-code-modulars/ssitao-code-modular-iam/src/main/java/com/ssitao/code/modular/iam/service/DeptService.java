package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.dept.DeptCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dept.DeptListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dept.DeptUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DeptDO;

import java.util.List;

/**
 * 部门服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface DeptService {

    /**
     * 创建部门
     *
     * @param createReqVO 创建请求
     * @return 部门ID
     */
    Long createDept(DeptCreateReqVO createReqVO);

    /**
     * 更新部门
     *
     * @param updateReqVO 更新请求
     */
    void updateDept(DeptUpdateReqVO updateReqVO);

    /**
     * 删除部门
     *
     * @param id 部门ID
     */
    void deleteDept(Long id);

    /**
     * 获取部门详情
     *
     * @param id 部门ID
     * @return 部门信息
     */
    DeptDO getDept(Long id);

    /**
     * 获取部门列表
     *
     * @param reqVO 查询请求
     * @return 部门列表
     */
    List<DeptDO> getDeptList(DeptListReqVO reqVO);

    /**
     * 获取部门树
     *
     * @return 部门树
     */
    List<DeptDO> getDeptTree();

}
