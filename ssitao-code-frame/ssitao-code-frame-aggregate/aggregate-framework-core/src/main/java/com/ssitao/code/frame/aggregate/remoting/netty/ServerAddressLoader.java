package com.ssitao.code.frame.aggregate.remoting.netty;

import java.util.List;

public interface ServerAddressLoader {
    String selectOneAvailableAddress();

    List<String> getAllAvailableAddresses();

    boolean isAvailableAddress(String address);
}
