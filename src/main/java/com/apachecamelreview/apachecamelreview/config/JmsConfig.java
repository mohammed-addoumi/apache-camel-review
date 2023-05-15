package com.apachecamelreview.apachecamelreview.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;

@Configuration
public class JmsConfig {

    @Autowired
    private JmsProperties jmsProperties;

    @Bean
    public JmsComponent activemqJmsComponent(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(jmsProperties.getActivemq().getBrokerUrl());
        activeMQConnectionFactory.setUserName(jmsProperties.getActivemq().getUsername());
        activeMQConnectionFactory.setPassword(jmsProperties.getActivemq().getPassword());
        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(activeMQConnectionFactory);
        JmsComponent jmsComponent = new ActiveMQComponent();
        jmsComponent.setConnectionFactory(singleConnectionFactory);
        return jmsComponent;
    }

    @Bean
    public JmsComponent artemisJmsComponent(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(jmsProperties.getArtemis().getBrokerUrl());
        activeMQConnectionFactory.setUserName(jmsProperties.getArtemis().getUsername());
        activeMQConnectionFactory.setPassword(jmsProperties.getArtemis().getPassword());
        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(activeMQConnectionFactory);
        JmsComponent jmsComponent = new ActiveMQComponent();
        jmsComponent.setConnectionFactory(singleConnectionFactory);
        return jmsComponent;
    }
}
