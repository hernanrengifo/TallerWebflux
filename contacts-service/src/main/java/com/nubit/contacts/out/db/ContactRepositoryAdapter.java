package com.nubit.contacts.out.db;

import com.nubit.contacts.domain.Contact;
import com.nubit.contacts.domain.ContactRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class ContactRepositoryAdapter implements ContactRepositoryPort {

    private final SpringDataContactRepository repo;

    public ContactRepositoryAdapter(SpringDataContactRepository repo) {
        this.repo = repo;
    }

    @Override
    public Mono<Contact> save(Contact c) { return repo.save(c); }

    @Override
    public Mono<Contact> findById(String id) { return repo.findById(id); }

    @Override
    public Mono<Void> deleteById(String id) { return repo.deleteById(id); }

    @Override
    public Flux<Contact> findAll() { return repo.findAll(); }

    @Override
    public Flux<Contact> findByNombreContainsIgnoreCase(String nombre) {
        return repo.findByNombreContainsIgnoreCase(nombre);
    }

    @Override
    public Mono<Contact> findByEmail(String email) { return repo.findByEmail(email); }
}
