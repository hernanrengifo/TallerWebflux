package com.nubit.contacts.out.db;

import com.nubit.contacts.domain.Contact;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SpringDataContactRepository extends ReactiveCrudRepository<Contact, String> {
    Flux<Contact> findByNombreContainsIgnoreCase(String nombre);
    Mono<Contact> findByEmail(String email);
}
