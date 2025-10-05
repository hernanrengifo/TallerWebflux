package com.nubit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {

        Flux<String> colores = Flux.fromIterable(Arrays.asList("rojo", "verde", "azul"))
                .delayElements(Duration.ofMillis(50))
                .map(c -> "COLOR:" + c.toUpperCase())
                .doOnNext(v -> System.out.println("[merge-A] " + v));

        Flux<String> numeros = Flux.just(1, 2, 3, 4)
                .delayElements(Duration.ofMillis(30))
                .map(n -> "NUM:" + n)
                .doOnNext(v -> System.out.println("[merge-B] " + v));

        Flux<String> mezclado = Flux.merge(colores, numeros)
                .doOnSubscribe(s -> System.out.println("[merge]   iniciando merge"))
                .doOnComplete(() -> System.out.println("[merge]   COMPLETADO"))
                .doOnError(e -> System.out.println("[merge]   ERROR: " + e.getMessage()));

        mezclado
                .publishOn(Schedulers.parallel())
                .subscribe(v -> System.out.println("[sink-2]  " + v));

        Thread.sleep(1000);
    }
}