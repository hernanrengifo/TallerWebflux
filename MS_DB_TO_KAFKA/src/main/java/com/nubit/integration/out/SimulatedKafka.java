package com.nubit.integration.out;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

import java.util.logging.Level;
import java.util.logging.Logger;

@EnableIntegration
@Configuration
public class SimulatedKafka {
    private final Logger LOGGER  = Logger.getLogger(SimulatedKafka.class.getName());
    @Bean
    public IntegrationFlow writeKafka() {

        return flow ->
                flow
                        .log(message -> {
                                LOGGER.log(Level.ALL, message.toString());
                                return message;
                        })
                        .channel("kafkaOutputChannel");
    }
}
