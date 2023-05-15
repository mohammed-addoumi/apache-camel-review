package com.apachecamelreview.apachecamelreview.processors;

import com.apachecamelreview.apachecamelreview.config.ServiceProperties;
import com.apachecamelreview.apachecamelreview.dao.RouteLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Slf4j
public class JmsProcessor extends GenericProcessor {

    public JmsProcessor(RouteLogRepository routeLogRepository, ServiceProperties serviceProperties) {
        super(routeLogRepository, serviceProperties);
    }

    @Override
    @Transactional
    public void customProcess(Exchange exchange){
        String messageBody = exchange.getIn().getBody(String.class);
        String fileName = LocalDateTime.now().toString();
        String fileExtension;
        String messageFormat;
        if (isXml(messageBody)) {
            messageFormat = "XML";
            fileExtension = ".xml";
        } else if (isJson(messageBody)) {
            messageFormat = "JSON";
            fileExtension = ".json";
        } else {
            messageFormat = "Text";
            fileExtension = ".txt";
        }
        log.info("Message format: {}", messageFormat);
        String fullFileName = fileName + fileExtension;
        exchange.getIn().setHeader("CamelFileName", fullFileName);
    }

    private boolean isXml(String message) {
        return message != null && message.startsWith("<");
    }

    private boolean isJson(String message) {
        return message != null && message.startsWith("{");
    }
}

