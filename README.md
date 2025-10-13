# Taller Reactivo

Este taller tiene como propósito introducir de forma práctica los **principios básicos de la programación reactiva** y su aplicación en diferentes contextos de desarrollo.

---

## [Taller 1: Reactor](TALLER1.MD)

**Objetivo pedagógico:**  
Comprender el modelo de ejecución y funcionamiento de los operadores en Reactor.

**Instrucciones:**
- Cree un nuevo método `main` donde se emitan los números del 1 al 10.
- Agregue el código necesario para **filtrar los números impares**.
- Muestre los resultados en la consola.

---

## [Taller 2: Spring WebFlux](TALLER2.MD)

**Objetivo pedagógico:**  
Familiarizarse con un **stack web completamente reactivo** utilizando Spring WebFlux.

**Instrucciones:**
- Ejecute la aplicación en local (requiere tener **Docker** instalado).
- Agregue un nuevo atributo al modelo de *Contacto* que identifique el **tipo de relación** (por ejemplo: *comercial*, *familiar*, etc.).
- Implemente el código necesario para **buscar contactos** por este nuevo atributo.
- Exponga un nuevo **método HTTP y contexto** que permita realizar dicha búsqueda.

---

## [Taller 3: Spring Cloud Gateway](TALLER3.MD)

**Objetivo pedagógico:**  
Comprender el **rol y funcionamiento de un API Gateway** dentro de una arquitectura reactiva distribuida.

**Instrucciones:**
- Integre el método generado en el **Taller 2** dentro de la aplicación actual.
- Configure el *gateway* para enrutar correctamente las peticiones hacia el nuevo servicio.