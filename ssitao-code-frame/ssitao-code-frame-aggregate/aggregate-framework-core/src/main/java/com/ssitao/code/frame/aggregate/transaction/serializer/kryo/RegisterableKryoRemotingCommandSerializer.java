package com.ssitao.code.frame.aggregate.transaction.serializer.kryo;

import com.google.common.collect.Lists;
import com.ssitao.code.frame.aggregate.remoting.protocol.RemotingCommand;
import com.ssitao.code.frame.aggregate.remoting.protocol.RemotingCommandCode;
import com.ssitao.code.frame.aggregate.transaction.serializer.RemotingCommandSerializer;
import com.ssitao.code.frame.aggregate.utils.CollectionUtils;

import java.util.List;

public class RegisterableKryoRemotingCommandSerializer extends RegisterableKryoSerializer<RemotingCommand> implements RemotingCommandSerializer {


    static List<Class> remotingCommandClasses = Lists.newArrayList(
            RemotingCommand.class,
            RemotingCommandCode.class);

    public RegisterableKryoRemotingCommandSerializer() {
        this(remotingCommandClasses);
    }

    public RegisterableKryoRemotingCommandSerializer(int initPoolSize) {
        this(initPoolSize, remotingCommandClasses);
    }

    public RegisterableKryoRemotingCommandSerializer(List<Class> registerClasses) {
        super(CollectionUtils.merge(remotingCommandClasses, registerClasses));
    }

    public RegisterableKryoRemotingCommandSerializer(int initPoolSize, List<Class> registerClasses) {
        super(initPoolSize, CollectionUtils.merge(remotingCommandClasses, registerClasses));
    }

    public RegisterableKryoRemotingCommandSerializer(int initPoolSize, List<Class> registerClasses, boolean warnUnregisteredClasses) {
        super(initPoolSize, CollectionUtils.merge(remotingCommandClasses, registerClasses), warnUnregisteredClasses);
    }
}
