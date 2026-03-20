package com.ssitao.code.frame.aggregate.dashboard.service.impl.aggserver;


import com.ssitao.code.frame.aggregate.dashboard.dto.DomainStoreDto;
import com.ssitao.code.frame.aggregate.dashboard.dto.DomainStoreRequestDto;
import com.ssitao.code.frame.aggregate.dashboard.dto.ResponseDto;
import com.ssitao.code.frame.aggregate.dashboard.service.DomainService;
import com.ssitao.code.frame.aggregate.dashboard.service.condition.AggServerStorageCondition;
import com.ssitao.code.frame.aggregate.storage.TransactionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author huabao.fang
 * @Date 2022/5/30 14:17
 **/
@Conditional(AggServerStorageCondition.class)
@Service
public class AggServerDomainServiceImpl implements DomainService {

    @Autowired
    private AggServerFeignClient aggServerFeignClient;

    @Override
    public ResponseDto<List<String>> getAllDomainKeys() {
        return aggServerFeignClient.allDomainKeys();
    }

    @Override
    public ResponseDto<List<DomainStoreDto>> getAllDomains() {
        return aggServerFeignClient.all();
    }

    @Override
    public ResponseDto<Void> create(DomainStoreRequestDto requestDto) {
        return aggServerFeignClient.createDomain(requestDto);
    }

    @Override
    public ResponseDto<Void> modify(DomainStoreRequestDto requestDto) {
        return aggServerFeignClient.modifyDomain(requestDto);
    }

    @Override
    public ResponseDto<Void> delete(DomainStoreRequestDto requestDto) {
        return aggServerFeignClient.deleteDomain(requestDto);
    }

    @Override
    public TransactionStorage getTransactionStorage() {
        return null;
    }
}
