
package com.ssitao.code.frame.mybatisflex.core.audit;

import com.ssitao.code.frame.mybatisflex.core.FlexConsts;
import com.ssitao.code.frame.mybatisflex.core.audit.http.HttpUtil;

/**
 * 默认的审计消息创建器，用来创建带有 hostIp 的审计消息。
 */
public class DefaultMessageFactory implements MessageFactory {

    private String hostIp;

    @Override
    public AuditMessage create() {
        AuditMessage message = new AuditMessage();
        message.setPlatform(FlexConsts.NAME);
        message.setHostIp(getHostIp());
        return message;
    }

    public String getHostIp() {
        if (hostIp == null) {
            hostIp = HttpUtil.getHostIp();
        }
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }
}
