package com.nubit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> frutas = Flux.just("manzana", "pera", "uva", "banano", "mango");

        Flux<String> mayus = frutas
                .doOnNext(v -> System.out.println("procesando     " + v))
                .map(String::toUpperCase)
                .doOnNext(v -> System.out.println("[map]     " + v));

        Flux<String> filtradas = mayus
                .filter(s -> s.length() > 5)
                .doOnNext(v -> System.out.println("[filter]  " + v))
                .doOnComplete(() -> System.out.println("[filter]  COMPLETADO"));

        Flux<String> enriquecidas = filtradas.flatMap(palabra ->
                Mono.just(palabra + " OK")
                        .delayElement(Duration.ofMillis(80))
                        .doOnSubscribe(sub -> System.out.println("[flatMap] llamando para " + palabra))
        ).doOnNext(v -> System.out.println("[flatMap] " + v));


        enriquecidas.subscribe(v -> System.out.println("[sink-1]  " + v));

        Thread.sleep(1000);
    }
}