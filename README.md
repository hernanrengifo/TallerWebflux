# WebFlux Contacts (Hexagonal, 100% Reactivo)

CRUD de contactos con **Spring WebFlux** (Java 21), arquitectura **hexagonal (Ports & Adapters)**, persistencia **R2DBC PostgreSQL**, documentación **OpenAPI**, y despliegue en **Heroku**.

## Dominio
Entidad `Contact`:
- `id` (UUID)
- `nombre` (2–100, requerido)
- `direccion` (0–200, opcional)
- `telefono` (7–20, opcional, sólo dígitos y símbolos `+ - ( )`)
- `email` (válido, requerido, único)

## Arquitectura (Hexagonal)
```mermaid
flowchart LR
  subgraph Domain
    M[Contact]
    P[(RepositoryPort)]
    U1[UseCase: Create]
    U2[UseCase: Get/Update/Delete/Search]
  end
  subgraph Inbound (Web)
    H[Handler]
    R[Router]
    EH[ErrorHandler]
  end
  subgraph Outbound (DB)
    A[(R2DBC Adapter)]
    DB[(PostgreSQL)]
  end

  H-->U1
  H-->U2
  R-->H
  U1-->P
  U2-->P
  P<-->A
  A<-->DB
```
Paquetes:
- `domain/` (modelo y puertos)
- `application/` (casos de uso)
- `in/web/` (router + handlers + error handling)
- `out/db/` (adapter R2DBC + repositorio)

## Requisitos
- Java 21
- PostgreSQL local (opcional) ó addon de Heroku
- Gradle 8+ (o usar `./gradlew` si agregas wrapper localmente)

## Ejecutar local
1. Arranca PostgreSQL local y crea DB `contacts`. Aplica `init_db.sql`.
2. Exporta variables (ejemplo):
   ```bash
   export SPRING_R2DBC_URL=r2dbc:postgresql://localhost:5432/contacts
   export SPRING_R2DBC_USERNAME=postgres
   export SPRING_R2DBC_PASSWORD=postgres
   ```
3. Ejecuta:
   ```bash
   ./gradlew bootRun
   ```

## Endpoints
- `POST   /api/v1/contacts`
- `GET    /api/v1/contacts/{id}`
- `PUT    /api/v1/contacts/{id}`
- `DELETE /api/v1/contacts/{id}`
- `GET    /api/v1/contacts?nombre=...&email=...&page=0&size=20&sort=nombre,asc`

Swagger UI: `http://localhost:8080/swagger-ui.html`

## Despliegue en Heroku
1. Crea la app y el addon:
   ```bash
   heroku create
   heroku addons:create heroku-postgresql:mini
   ```
2. Obtén `DATABASE_URL` y expórtalo a variables R2DBC:
   ```bash
   # Heroku entrega: postgres://user:pass@host:port/db
   DATABASE_URL=$(heroku config:get DATABASE_URL)

   # Transforma a R2DBC
   export SPRING_R2DBC_URL=$(echo $DATABASE_URL | sed -e 's/^postgres:/r2dbc:postgresql:/')
   export SPRING_R2DBC_USERNAME=$(echo $DATABASE_URL | sed -E 's|^postgres://([^:]+):.*$|\1|')
   export SPRING_R2DBC_PASSWORD=$(echo $DATABASE_URL | sed -E 's|^postgres://[^:]+:([^@]+)@.*$|\1|')
   ```
   O define estas variables en Heroku:
   ```bash
   heroku config:set SPRING_R2DBC_URL=r2dbc:postgresql://HOST:PORT/DB
   heroku config:set SPRING_R2DBC_USERNAME=USER
   heroku config:set SPRING_R2DBC_PASSWORD=PASS
   ```
3. Configura el `Procfile` y despliega:
   ```bash
   git add . && git commit -m "deploy"
   git push heroku main
   ```

## DDL
Ver `init_db.sql` (aplica en local y en Heroku).

## Notas
- 100% reactivo (no `block()`).
- Validación y manejo de errores uniforme.
