package com.example.contacts.in.web;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerResponse.BodyBuilder;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.Map;

@Component
@Order(-2)
@Primary
public class ErrorHandler implements ErrorWebExceptionHandler {

    private final ErrorAttributes errorAttributes;

    public ErrorHandler(ErrorAttributes errorAttributes) { this.errorAttributes = errorAttributes; }

    @Override
    public Mono<Void> handle(org.springframework.web.server.ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof IllegalArgumentException) status = HttpStatus.BAD_REQUEST;
        if (ex instanceof IllegalStateException) status = HttpStatus.CONFLICT;
        if (ex instanceof ResponseStatusException rse) status = HttpStatus.valueOf(rse.getStatusCode().value());

        Map<String, Object> body = Map.of(
                "timestamp", OffsetDateTime.now().toString(),
                "path", exchange.getRequest().getPath().value(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", ex.getMessage()
        );

        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        exchange.getResponse().setStatusCode(status);
        byte[] bytes;
        try {
            bytes = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsBytes(body);
        } catch (Exception e) {
            bytes = ("{status:" + status.value() + "}").getBytes();
        }
        var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
