package com.nubit.integration.process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;

@EnableIntegration
@Configuration
public class processDBEvent {

@Bean
    public IntegrationFlow processEvent() {
        return IntegrationFlow.from("txTransaccionEventChannel")
                .transform(Transformers.toJson())
                .aggregate()
                .gateway("kafkaOutputChannel")
                .get();
    }
}
