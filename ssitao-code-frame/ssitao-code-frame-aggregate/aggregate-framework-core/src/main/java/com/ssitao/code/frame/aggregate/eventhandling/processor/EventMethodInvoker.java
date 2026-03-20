package com.ssitao.code.frame.aggregate.eventhandling.processor;

import com.ssitao.code.frame.aggregate.AggClient;
import com.ssitao.code.frame.aggregate.eventhandling.EventHandlerHook;
import com.ssitao.code.frame.aggregate.eventhandling.EventInvokerEntry;
import com.ssitao.code.frame.aggregate.eventhandling.annotation.EventHandler;
import com.ssitao.code.frame.aggregate.exception.SystemException;
import com.ssitao.code.frame.aggregate.support.BeanFactory;
import com.ssitao.code.frame.aggregate.support.FactoryBuilder;
import com.ssitao.code.frame.aggregate.threadcontext.ThreadContextSynchronizationManager;
import com.ssitao.code.frame.aggregate.transaction.Invocation;
import com.ssitao.code.frame.aggregate.transaction.Participant;
import com.ssitao.code.frame.aggregate.transaction.Transaction;
import com.ssitao.code.frame.aggregate.transaction.repository.TransactionRepository;
import com.ssitao.code.frame.aggregate.utils.ReflectionUtils;
import com.ssitao.code.frame.aggregate.xid.TransactionXid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * Created by changming.xie on 2/4/16.
 */
public class EventMethodInvoker {

    static final Logger logger = LoggerFactory.getLogger(EventMethodInvoker.class);

    private static volatile EventMethodInvoker INSTANCE = null;

    public static EventMethodInvoker getInstance() {

        if (INSTANCE == null) {

            synchronized (AsyncMethodInvoker.class) {

                if (INSTANCE == null) {
                    INSTANCE = new EventMethodInvoker();
                }
            }
        }

        return INSTANCE;
    }

    public static void preInvoke(EventInvokerEntry entry) {

        EventHandler eventHandler = ReflectionUtils.getAnnotation(entry.getMethod(), EventHandler.class);

        if (eventHandler.isTransactionMessage()) {

            if (StringUtils.isEmpty(eventHandler.transactionCheck().checkTransactionStatusMethod())) {

                throw new SystemException("checkTransactionStatusMethod cannot be empty when isTransactionMessage is true");
            }

            if (FactoryBuilder.factoryOf(AggClient.class) == null) {
                throw new SystemException("TransactionConfigurator cannot be found. Seems TransactionConfigurator(or its subclass RecoverConfiguration) instance is not correctly injected.");
            }

            TransactionRepository transactionRepository = FactoryBuilder.factoryOf(AggClient.class).getInstance().getTransactionRepository();

            Transaction transaction = new Transaction(TransactionXid.withUniqueIdentity(null));

            transaction.getAttachments().put(ThreadContextSynchronizationManager.THREAD_CONTEXT_SYNCHRONIZATION_KEY,
                    ThreadContextSynchronizationManager.getThreadContextSynchronization().getCurrentThreadContext());

            BeanFactory beanFactory = FactoryBuilder.getFactory(BeanFactory.class);

            Invocation invocation = new Invocation(beanFactory.getTargetClass(entry.getTarget()), entry.getMethod().getName(), eventHandler.transactionCheck().checkTransactionStatusMethod(), entry.getMethod().getParameterTypes(), entry.getParams());
            Participant participant = new Participant(invocation);

            transaction.enlistParticipant(participant);

            transactionRepository.create(transaction);

            entry.setTransaction(transaction);
        }
    }

    public void invoke(List<EventInvokerEntry> entries) {

        beforeInvoke(entries);

        doInvoke(entries);

        afterInvoke(entries);

        completeInvoke(entries);

    }

    private void beforeInvoke(List<EventInvokerEntry> entries) {

        for (EventInvokerEntry entry : entries) {
            final Method method = entry.getMethod();
            final Object target = entry.getTarget();
            final Object[] params = entry.getParams();
            try {
                EventHandlerHook.INSTANCE.beforeEventHandler(target, method, params);
            } catch (Exception e) {
                throw new SystemException(e);
            }
        }
    }

    private void doInvoke(List<EventInvokerEntry> entries) {

        Collection aggregateParams = (Collection) entries.get(0).getParams()[0];

        for (int i = 1; i < entries.size(); i++) {
            aggregateParams.addAll((Collection) entries.get(i).getParams()[0]);
        }

        try {
            EventInvokerEntry currentEventInvokerEntry = entries.get(0);
            currentEventInvokerEntry.getMethod().invoke(currentEventInvokerEntry.getTarget(), aggregateParams);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    private void afterInvoke(List<EventInvokerEntry> entries) {

        for (EventInvokerEntry entry : entries) {

            final Method method = entry.getMethod();
            final Object target = entry.getTarget();
            final Object[] params = entry.getParams();
            try {
                EventHandlerHook.INSTANCE.afterEventHandler(target, method, params, null);
            } catch (Exception e) {
                throw new SystemException(e);
            }
        }
    }

    private void completeInvoke(List<EventInvokerEntry> entries) {

        for (EventInvokerEntry entry : entries) {
            completeInvoke(entry);
        }
    }

    public void invoke(EventInvokerEntry entry) {

        final Method method = entry.getMethod();
        final Object target = entry.getTarget();
        final Object[] params = entry.getParams();

        try {
            try {
                EventHandlerHook.INSTANCE.beforeEventHandler(target, method, params);
                method.invoke(target, params);
            } catch (Exception e) {
                EventHandlerHook.INSTANCE.afterEventHandler(target, method, params, e);
                throw e;
            }

            EventHandlerHook.INSTANCE.afterEventHandler(target, method, params, null);

            if (entry.getTransaction() != null) {

                TransactionRepository transactionRepository = FactoryBuilder.factoryOf(AggClient.class).getInstance().getTransactionRepository();

                transactionRepository.delete(entry.getTransaction());
            }

        } catch (Throwable e) {
            throw new SystemException(e);
        }
    }

    public void cancelInvoke(EventInvokerEntry entry) {
        completeInvoke(entry);
    }

    private void completeInvoke(EventInvokerEntry entry) {
        try {
            if (entry.getTransaction() != null) {
                TransactionRepository transactionRepository = FactoryBuilder.factoryOf(AggClient.class).getInstance().getTransactionRepository();

                transactionRepository.delete(entry.getTransaction());
            }
        } catch (Exception e) {
            logger.error("remove transaction log failed. Ignore the error and the transaction log will be deleted by transaction recovery schedule job.", e);
        }
    }
}
