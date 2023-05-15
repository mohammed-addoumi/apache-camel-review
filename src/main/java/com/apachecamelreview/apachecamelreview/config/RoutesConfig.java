package com.apachecamelreview.apachecamelreview.config;

import lombok.RequiredArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(ServiceProperties.class)
public class RoutesConfig {

    private final CamelContext camelContext;
    private final ServiceProperties serviceProperties;
    private final ApplicationContext applicationContext;

    @Autowired
    private JmsComponent activemqJmsComponent;

    @Autowired
    private JmsComponent artemisJmsComponent;

    @EventListener(ApplicationReadyEvent.class)
    public void configureRoutes(){
        camelContext.addComponent("activemq",activemqJmsComponent);
        camelContext.addComponent("artemis",artemisJmsComponent);
        serviceProperties.getRoutes().forEach(
                route -> {
                    try {
                        camelContext.addRoutes(constructRoute(route));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

    }

    private RoutesBuilder constructRoute(ServiceProperties.Route route) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                RouteDefinition routeDefinition = from(route.getFrom());
                routeDefinition.process(route.getProcessor());
                routeDefinition.routeId(route.getRouteId());
                route.getTo().forEach(routeDefinition::to);
            }
        };
    }
}
