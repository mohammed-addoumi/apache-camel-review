package com.apachecamelreview.apachecamelreview.processors;

import com.apachecamelreview.apachecamelreview.config.ServiceProperties;
import com.apachecamelreview.apachecamelreview.dao.RouteLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class SftpProcessor extends GenericProcessor {

    public SftpProcessor(RouteLogRepository routeLogRepository, ServiceProperties serviceProperties) {
        super(routeLogRepository, serviceProperties);
    }

    @Override
    @Transactional
    public void customProcess(Exchange exchange) {
        log.info("processing sftp : {} ", exchange.getIn().getBody());
    }
}
