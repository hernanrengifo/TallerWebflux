package com.nubit.contacts.application;

import com.nubit.contacts.domain.Contact;
import com.nubit.contacts.domain.ContactRepositoryPort;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ContactService {

    private final ContactRepositoryPort repo;

    public ContactService(ContactRepositoryPort repo) {
        this.repo = repo;
    }

    public Mono<Contact> create(@Valid Contact c) {
        if (c.getId() == null) c.setId(UUID.randomUUID().toString());
        return repo.findByEmail(c.getEmail())
                .flatMap(existing -> Mono.<Contact>error(new IllegalStateException("email duplicado")))
                .switchIfEmpty(repo.save(c));
    }

    public Mono<Contact> get(String id) { return repo.findById(id); }

    public Mono<Contact> update(String id, @Valid Contact c) {
        return repo.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("no encontrado")))
                .flatMap(existing -> {
                    c.setId(id);
                    return repo.save(c);
                });
    }

    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }

    public Flux<Contact> list(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            return repo.findByNombreContainsIgnoreCase(nombre);
        }
        return repo.findAll();
    }
}
