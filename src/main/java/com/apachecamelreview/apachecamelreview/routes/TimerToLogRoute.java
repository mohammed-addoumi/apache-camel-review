package com.apachecamelreview.apachecamelreview.routes;

import org.apache.camel.builder.RouteBuilder;

//@Component
public class TimerToLogRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:timer-test")
                .transform(constant("test apache camel"))
                .to("log:timer-test");
    }
}
