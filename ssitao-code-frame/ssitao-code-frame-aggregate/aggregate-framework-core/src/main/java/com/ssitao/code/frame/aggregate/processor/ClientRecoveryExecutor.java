package com.ssitao.code.frame.aggregate.processor;

import com.ssitao.code.frame.aggregate.support.FactoryBuilder;
import com.ssitao.code.frame.aggregate.threadcontext.ThreadContextSynchronizationManager;
import com.ssitao.code.frame.aggregate.transaction.Transaction;
import com.ssitao.code.frame.aggregate.recovery.RecoveryExecutor;
import com.ssitao.code.frame.aggregate.transaction.repository.TransactionConvertor;
import com.ssitao.code.frame.aggregate.transaction.repository.TransactionRepository;
import com.ssitao.code.frame.aggregate.transaction.serializer.TransactionSerializer;
import com.ssitao.code.frame.aggregate.transaction.serializer.json.FastjsonTransactionSerializer;
import com.ssitao.code.frame.aggregate.storage.TransactionOptimisticLockException;
import com.ssitao.code.frame.aggregate.storage.TransactionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ClientRecoveryExecutor implements RecoveryExecutor {

    private static final Logger logger = LoggerFactory.getLogger(ClientRecoveryExecutor.class.getSimpleName());

    private TransactionRepository transactionRepository;
    private TransactionSerializer transactionSerializer;

    public ClientRecoveryExecutor(TransactionSerializer transactionSerializer, TransactionRepository transactionRepository) {
        this.transactionSerializer = transactionSerializer;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void recover(TransactionStore transactionStore) {

        Transaction transaction = TransactionConvertor.getTransaction(transactionSerializer, transactionStore);

        transaction.setRetriedCount(transaction.getRetriedCount() + 1);
        try {
            transactionRepository.update(transaction);
        } catch (TransactionOptimisticLockException e) {
            logger.debug("multiple instances try to recovery<commit> the same transaction<{}>, this instance ignore the recovery.", transactionStore.getXid());
            return;
        }
        Map<String, String> map = transaction.getAttachments();
        String threadContext = map.get(ThreadContextSynchronizationManager.THREAD_CONTEXT_SYNCHRONIZATION_KEY);
        ThreadContextSynchronizationManager threadContextSynchronizationManager = new ThreadContextSynchronizationManager(threadContext);
        threadContextSynchronizationManager.executeWithBindThreadContext(transaction::commit);
        transactionRepository.delete(transaction);
    }

    @Override
    public byte[] transactionVisualize(String domain, byte[] content) {
        Transaction transaction = transactionSerializer.deserialize(content);
        FastjsonTransactionSerializer fastjsonTransactionSerializer = FactoryBuilder.factoryOf(FastjsonTransactionSerializer.class).getInstance();
        return fastjsonTransactionSerializer.serialize(transaction);
    }
}
