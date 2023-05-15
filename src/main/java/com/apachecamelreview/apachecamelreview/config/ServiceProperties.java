package com.apachecamelreview.apachecamelreview.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@ConfigurationProperties(prefix = "service")
@Data
public class ServiceProperties {

    private List<Route> routes;

    @Data
    public static class Route{
        private  String from;
        private String routeId;
        private  String processor;
        private  List<String> to;
    }
}
