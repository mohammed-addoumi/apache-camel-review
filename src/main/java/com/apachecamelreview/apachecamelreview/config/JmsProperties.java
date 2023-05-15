package com.apachecamelreview.apachecamelreview.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jms")
@Data
public class JmsProperties {
    private ActivemqProperties activemq;
    private ArtemisProperties artemis;

    @Data
    public static class ActivemqProperties {
        private String brokerUrl;
        private String username;
        private String password;
    }

    @Data
    public static class ArtemisProperties {
        private String brokerUrl;
        private String username;
        private String password;
    }
}
