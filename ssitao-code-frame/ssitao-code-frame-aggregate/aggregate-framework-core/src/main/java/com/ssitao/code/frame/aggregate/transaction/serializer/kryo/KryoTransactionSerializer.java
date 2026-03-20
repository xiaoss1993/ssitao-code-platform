package com.ssitao.code.frame.aggregate.transaction.serializer.kryo;


import com.ssitao.code.frame.aggregate.serializer.KryoPoolSerializer;
import com.ssitao.code.frame.aggregate.transaction.Transaction;
import com.ssitao.code.frame.aggregate.transaction.serializer.TransactionSerializer;

/**
 * Created by changming.xie on 9/18/17.
 */
public class KryoTransactionSerializer extends KryoPoolSerializer<Transaction> implements TransactionSerializer {

}
