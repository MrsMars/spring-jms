package com.aoher.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;

import java.util.Collections;

@Configuration
@PropertySource("classpath:application.properties")
public class MessagingConfiguration {

    private static final String PACKAGE = "com.aoher";

    @Value("${mdp.jms.url}")
    private String defaultBrokerUrl;

    @Value("${myApp.jms.inbound.queue.name}")
    private String orderQueue;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", PACKAGE);

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(defaultBrokerUrl);
        connectionFactory.setTrustedPackages(Collections.singletonList(PACKAGE));

        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setInitialRedeliveryDelay(0);
        redeliveryPolicy.setRedeliveryDelay(3000);
        redeliveryPolicy.setUseExponentialBackOff(false);
        redeliveryPolicy.setMaximumRedeliveries(3);

        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();

        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(orderQueue);
        template.setReceiveTimeout(1000L);
        template.setSessionAcknowledgeModeName("CLIENT_ACKNOWLEDGE");

        return template;
    }
}
