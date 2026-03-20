package com.ssitao.code.frame.aggregate.dashboard.service;


import com.ssitao.code.frame.aggregate.dashboard.dto.DomainStoreDto;
import com.ssitao.code.frame.aggregate.dashboard.dto.DomainStoreRequestDto;
import com.ssitao.code.frame.aggregate.dashboard.dto.ResponseDto;

import java.util.List;

/**
 * @Author huabao.fang
 * @Date 2022/5/30 10:09
 **/
public interface DomainService extends TransactionStorageable {

    ResponseDto<List<String>> getAllDomainKeys();

    ResponseDto<List<DomainStoreDto>> getAllDomains();

    ResponseDto<Void> create(DomainStoreRequestDto requestDto);

    ResponseDto<Void> modify(DomainStoreRequestDto requestDto);

    ResponseDto<Void> delete(DomainStoreRequestDto requestDto);

}
