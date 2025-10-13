# Explicación del programa `Main.java`

Este programa es un ejemplo básico de uso de **Project Reactor** en Java para procesar una secuencia de cadenas de texto de manera reactiva.

## Descripción general

- Utiliza la librería Reactor para crear y manipular flujos de datos asíncronos y no bloqueantes.
- El flujo principal procesa una lista de frutas, las convierte a mayúsculas, filtra las que tienen más de 5 caracteres y enriquece cada resultado agregando " OK" con un retardo artificial.

## Pasos del flujo

1. **Creación del Flux inicial**
   Se crea un `Flux` con varias frutas:
   ```java
   Flux<String> frutas = Flux.just("manzana", "pera", "uva", "banano", "mango");

2. Transformación a mayúsculas Se imprime cada elemento procesado y luego se transforma a mayúsculas:
     ```java
   .doOnNext(v -> System.out.println("procesando     " + v))
   .map(String::toUpperCase)
   .doOnNext(v -> System.out.println("[map]     " + v));
    ```
3. **Filtrado de frutas**
   Se filtran las frutas que tienen más de 5 caracteres:
   ```java
    .filter(s -> s.length() > 5)
    .doOnNext(v -> System.out.println("[filter]  " + v))
    .doOnComplete(() -> System.out.println("[filter]  COMPLETADO"));
    ```
4. Enriquecimiento asíncrono simulando un llamado externo con un retardo de 80 ms:
   ```java
    .flatMap(palabra ->
   Mono.just(palabra + " OK")
   .delayElement(Duration.ofMillis(80))
   .doOnSubscribe(sub -> System.out.println("[flatMap] llamando para " + palabra))
   )
   .doOnNext(v -> System.out.println("[flatMap] " + v));
    ```
5. Suscripción Se suscribe al flujo final e imprime los resultados:
     ```java
      frutas.subscribe(v -> System.out.println("Resultado final: " + v));
    ```