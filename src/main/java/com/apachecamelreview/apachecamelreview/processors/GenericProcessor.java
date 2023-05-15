package com.apachecamelreview.apachecamelreview.processors;

import com.apachecamelreview.apachecamelreview.config.ServiceProperties;
import com.apachecamelreview.apachecamelreview.dao.RouteLog;
import com.apachecamelreview.apachecamelreview.dao.RouteLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public abstract class GenericProcessor implements Processor {

    private final RouteLogRepository routeLogRepository;
    private final ServiceProperties serviceProperties;

    public RouteLog saveRouteLog(Exchange exchange){
        RouteLog routeLog = new RouteLog();
        routeLog.setRouteId(exchange.getFromRouteId());
        routeLog.setTimestamp(LocalDateTime.now());
        routeLog.setFromEndpoint(exchange.getFromEndpoint().getEndpointUri());
        routeLog.setBody(exchange.getIn().getBody(String.class));
        Optional<ServiceProperties.Route> firstRoute = serviceProperties.getRoutes().stream()
                .filter(route -> route.getRouteId().equalsIgnoreCase(exchange.getFromRouteId()))
                .findFirst();
        String destinationEndpoint = firstRoute.get().getTo().stream().collect(Collectors.joining(","));
        routeLog.setToEndpoint(destinationEndpoint);
        routeLog = routeLogRepository.save(routeLog);
        return routeLog;
    }

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {
        customProcess(exchange);
        saveRouteLog(exchange);
    }

    protected abstract void customProcess(Exchange exchange);
}
