package com.ssitao.code.frame.aggregate.dashboard.service.impl;


import com.ssitao.code.frame.aggregate.alert.ResponseCodeEnum;
import com.ssitao.code.frame.aggregate.dashboard.dto.ModifyCronDto;
import com.ssitao.code.frame.aggregate.dashboard.dto.ResponseDto;
import com.ssitao.code.frame.aggregate.dashboard.dto.TaskDto;
import com.ssitao.code.frame.aggregate.dashboard.exception.TransactionException;
import com.ssitao.code.frame.aggregate.dashboard.service.TaskService;

import java.util.List;

/**
 * @Author huabao.fang
 * @Date 2022/6/9 17:03
 **/
public class BaseTaskServiceImpl implements TaskService {

    @Override
    public ResponseDto<List<TaskDto>> all() {
        throw new TransactionException(ResponseCodeEnum.TASK_OPERATE_NOT_SUPPORT);
    }

    @Override
    public ResponseDto<Void> pause(String domain) {
        throw new TransactionException(ResponseCodeEnum.TASK_OPERATE_NOT_SUPPORT);
    }

    @Override
    public ResponseDto<Void> resume(String domain) {
        throw new TransactionException(ResponseCodeEnum.TASK_OPERATE_NOT_SUPPORT);
    }

    @Override
    public ResponseDto<Void> modifyCron(ModifyCronDto requestDto) {
        throw new TransactionException(ResponseCodeEnum.TASK_OPERATE_NOT_SUPPORT);
    }

    @Override
    public ResponseDto<Void> delete(String domain) {
        throw new TransactionException(ResponseCodeEnum.TASK_OPERATE_NOT_SUPPORT);
    }

}
