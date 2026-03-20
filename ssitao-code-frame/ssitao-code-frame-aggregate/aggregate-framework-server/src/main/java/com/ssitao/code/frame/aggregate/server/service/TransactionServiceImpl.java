package com.ssitao.code.frame.aggregate.server.service;

import com.ssitao.code.frame.aggregate.AggServer;
import com.ssitao.code.frame.aggregate.alert.ResponseCodeEnum;
import com.ssitao.code.frame.aggregate.dashboard.dto.ResponseDto;
import com.ssitao.code.frame.aggregate.dashboard.dto.TransactionDetailRequestDto;
import com.ssitao.code.frame.aggregate.dashboard.dto.TransactionStoreDto;
import com.ssitao.code.frame.aggregate.dashboard.service.impl.BaseTransactionServiceImpl;
import com.ssitao.code.frame.aggregate.storage.TransactionStorage;
import com.ssitao.code.frame.aggregate.storage.TransactionStore;
import com.ssitao.code.frame.aggregate.xid.TransactionXid;
import com.ssitao.code.frame.aggregate.xid.Xid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author huabao.fang
 * @Date 2022/5/24 23:17
 **/
@Service
public class TransactionServiceImpl extends BaseTransactionServiceImpl {

    @Autowired
    private AggServer aggServer;

    @Override
    public TransactionStorage getTransactionStorage() {
        return aggServer.getTransactionStorage();
    }

    @Override
    public ResponseDto<TransactionStoreDto> detail(TransactionDetailRequestDto requestDto) {
        String domain = requestDto.getDomain();
        Xid xid = new TransactionXid(requestDto.getXidString());
        TransactionStore transactionStore = getTransactionStorage().findByXid(domain, xid);
        if (transactionStore == null) {
            transactionStore = getTransactionStorage().findMarkDeletedByXid(domain, xid);
        }
        if (transactionStore == null) {
            return ResponseDto.returnFail(ResponseCodeEnum.TRANSACTION_DETAIL_NOT_EXIST);
        }
        TransactionStoreDto transactionStoreDto = toTransactionStoreDto(transactionStore);
        if (!isJSONString(transactionStoreDto.getContent())) {
            byte[] visualizedContent = null;
            try {
                visualizedContent = aggServer.getRecoveryExecutor().transactionVisualize(domain, transactionStore.getContent());
            } catch (Exception e) {
                String errorMessage = e.getMessage();
                if (errorMessage != null && errorMessage.length() > 100) {
                    errorMessage = errorMessage.substring(0, 100).concat("...");
                }
                return ResponseDto.returnFail(ResponseCodeEnum.TRANSACTION_CONTENT_VISUALIZE_ERROR_WITH_MESSAGE, errorMessage);
            }
            transactionStoreDto.setContent(new String(visualizedContent));
        }
        return ResponseDto.returnSuccess(transactionStoreDto);
    }

}
