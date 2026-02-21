
package com.ssitao.code.frame.mybatisflex.core.audit.http;

import com.ssitao.code.frame.mybatisflex.core.audit.AuditMessage;
import com.ssitao.code.frame.mybatisflex.core.audit.MessageReporter;

import java.util.List;

public class HttpMessageReporter implements MessageReporter {

    private final String endpoint;
    private final String secretKey;
    private final JSONFormatter jsonFormatter;

    public HttpMessageReporter(String endpoint, String secretKey, JSONFormatter jsonFormatter) {
        this.endpoint = endpoint;
        this.secretKey = secretKey;
        this.jsonFormatter = jsonFormatter;
    }

    @Override
    public void sendMessages(List<AuditMessage> messages) {
        long timeMillis = System.currentTimeMillis();
        String sign = HashUtil.md5(secretKey + timeMillis);
        String url = endpoint + "?time=" + timeMillis + "&sign=" + sign;
        HttpUtil.post(url, jsonFormatter.toJSONString(messages));
    }

    public interface JSONFormatter {

        String toJSONString(Object object);

    }

}
