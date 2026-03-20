package com.ssitao.code.frame.aggregate.remoting.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import com.ssitao.code.frame.aggregate.transaction.serializer.RemotingCommandSerializer;

import java.util.List;

public class NettyDecoder extends ByteToMessageDecoder {

    RemotingCommandSerializer serializer;

    public NettyDecoder(RemotingCommandSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> out) throws Exception {

        byte[] bytes = new byte[in.capacity()];
        in.readBytes(bytes, 0, in.capacity());
        out.add(serializer.deserialize(bytes));
    }
}
