package com.nubit.contacts.in.web;

import com.nubit.contacts.application.ContactService;
import com.nubit.contacts.domain.Contact;
import jakarta.validation.Validator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ContactHandler {

    private final ContactService service;
    private final SpringValidatorAdapter validator;

    public ContactHandler(ContactService service, Validator jakartaValidator) {
        this.service = service;
        this.validator = new SpringValidatorAdapter(jakartaValidator);
    }

    public Mono<ServerResponse> create(ServerRequest req) {
        return req.bodyToMono(Contact.class)
                .flatMap(this::validate)
                .flatMap(service::create)
                .flatMap(c -> ServerResponse.created(req.uriBuilder().pathSegment(c.getId().toString()).build())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(c));
    }

    public Mono<ServerResponse> get(ServerRequest req) {
        UUID id = UUID.fromString(req.pathVariable("id"));
        return service.get(id)
                .flatMap(c -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(c))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest req) {
        UUID id = UUID.fromString(req.pathVariable("id"));
        return req.bodyToMono(Contact.class)
                .flatMap(this::validate)
                .flatMap(c -> service.update(id, c))
                .flatMap(c -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(c));
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        UUID id = UUID.fromString(req.pathVariable("id"));
        return service.delete(id)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> list(ServerRequest req) {
        String nombre = req.queryParam("nombre").orElse(null);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.list(nombre), Contact.class);
    }

    private Mono<Contact> validate(Contact c) {
        var errors = new BeanPropertyBindingResult(c, "contact");
        validator.validate(c, errors);
        if (errors.hasErrors()) {
            return Mono.error(new IllegalArgumentException(errors.toString()));
        }
        return Mono.just(c);
    }
}
