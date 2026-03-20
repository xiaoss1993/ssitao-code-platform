package com.ssitao.code.frame.aggregate.sample.serializer;

import com.alibaba.fastjson.JSON;
import com.ssitao.code.frame.aggregate.sample.AbstractTestCase;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.event.OrderConfirmedEvent;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.factory.OrderFactory;
import com.ssitao.code.frame.aggregate.sample.quickstart.command.eventhandler.OrderHandler;
import com.ssitao.code.frame.aggregate.serializer.ObjectSerializer;
import com.ssitao.code.frame.aggregate.serializer.RegisterableKryoSerializer;
import com.ssitao.code.frame.aggregate.transaction.Invocation;
import com.ssitao.code.frame.aggregate.transaction.Participant;
import com.ssitao.code.frame.aggregate.transaction.Transaction;
import com.ssitao.code.frame.aggregate.xid.TransactionXid;
import org.junit.Test;

/**
 * Created by changming.xie on 9/18/17.
 */
public class SerializerTest extends AbstractTestCase {

//    private ObjectSerializer objectSerializer = new RegisterableKryoTransactionSerializer(Lists.newArrayList(
//            com.ssitao.code.frame.aggregate.sample.quickstart.command.domain.event.OrderConfirmedEvent.class
//    ));

//    private ObjectSerializer objectSerializer = new RegisterableKryoTransactionSerializer();


    private ObjectSerializer objectSerializer = new RegisterableKryoSerializer();

    @Test
    public void given_transaction_when_serialize_and_compare_then_all_cost_time_printed() {

        Transaction transaction = new Transaction(TransactionXid.withUniqueIdentity(null));

        transaction.enlistParticipant(
                new Participant(
                        new Invocation(
                                OrderHandler.class, "handleOrderCreatedEvent", "checkOrderIsConfirmed",
                                new Class[]{OrderConfirmedEvent.class}, new Object[]{new OrderConfirmedEvent(OrderFactory.buildOrder(1, 1001, 1))}
                        )));

        String jsons = JSON.toJSONString(transaction);

        byte[] jsonBytes = jsons.getBytes();
        System.out.println("json size:" + jsonBytes.length);

        byte[] bytes = objectSerializer.serialize(transaction);

        System.out.println("kryo size:" + bytes.length);

        Transaction deserialized = (Transaction) objectSerializer.deserialize(bytes);


        long totalTime = 0;

        long currentTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {

            objectSerializer.serialize(transaction);
        }

        totalTime = (System.currentTimeMillis() - currentTime);

        System.out.println("1000 count serialize cost time:" + totalTime);
    }
}
