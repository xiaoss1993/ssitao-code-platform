package com.ssitao.code.frame.aggregate.dashboard.config;


import com.ssitao.code.frame.aggregate.dashboard.enums.ConnectionMode;
import com.ssitao.code.frame.aggregate.properties.registry.RegistryProperties;

/**
 * @Author huabao.fang
 * @Date 2022/6/12 00:32
 **/
public class DashboardProperties {
    private String userName = "admin";
    private String password = "123456";
    private ConnectionMode connectionMode;

    public DashboardProperties() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ConnectionMode getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(ConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
    }
}
