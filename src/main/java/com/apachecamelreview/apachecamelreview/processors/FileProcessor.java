package com.apachecamelreview.apachecamelreview.processors;

import com.apachecamelreview.apachecamelreview.config.ServiceProperties;
import com.apachecamelreview.apachecamelreview.dao.RouteLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileProcessor extends GenericProcessor {


    public FileProcessor(RouteLogRepository routeLogRepository, ServiceProperties serviceProperties) {
        super(routeLogRepository, serviceProperties);
    }

    @Override
    public void customProcess(Exchange exchange) {
        log.info("processing file : {} ", exchange.getIn().getBody());
        String content = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(content);
    }
}
