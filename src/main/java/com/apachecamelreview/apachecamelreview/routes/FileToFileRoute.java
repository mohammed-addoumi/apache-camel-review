package com.apachecamelreview.apachecamelreview.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FileToFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:in")
                .to("file:out");
    }
}
