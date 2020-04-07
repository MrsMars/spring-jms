package com.aoher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"caom.aoher.model", "caom.aoher.messaging", "caom.aoher.service"})
@Import({MessagingConfiguration.class, MessagingListenerConfiguration.class})
public class SpringTestConfig {
}