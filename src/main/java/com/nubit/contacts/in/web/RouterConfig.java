package com.example.contacts.in.web;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    @Hidden
    public RouterFunction<ServerResponse> routes(ContactHandler h) {
        return RouterFunctions.route()
                .path("/api/v1/contacts", builder -> builder
                        .POST("", h::create)
                        .GET("", h::list)
                        .GET("/{id}", h::get)
                        .PUT("/{id}", h::update)
                        .DELETE("/{id}", h::delete)
                )
                .build();
    }
}
