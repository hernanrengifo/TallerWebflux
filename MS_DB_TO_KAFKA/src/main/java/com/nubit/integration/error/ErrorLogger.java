package com.nubit.integration.error;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@EnableIntegration
public class ErrorLogger {

    @Bean
    public IntegrationFlow writeKafka() {
        return f -> f.handle(message -> {
            System.out.println("Error: " + message.getPayload());
        }).channel("errorAdapter");
    }
}
