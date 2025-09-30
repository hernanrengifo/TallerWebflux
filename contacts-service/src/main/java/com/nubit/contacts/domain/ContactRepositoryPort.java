package com.nubit.contacts.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ContactRepositoryPort {
    Mono<Contact> save(Contact c);
    Mono<Contact> findById(UUID id);
    Mono<Void> deleteById(UUID id);
    Flux<Contact> findAll();
    Flux<Contact> findByNombreContainsIgnoreCase(String nombre);
    Mono<Contact> findByEmail(String email);
}
