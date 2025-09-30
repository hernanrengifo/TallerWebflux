# Contacts API Gateway

Este modulo incorpora una aplicacion Spring Cloud Gateway que centraliza el acceso al servicio de contactos webflux-contacts-hex. El objetivo es ofrecer un punto de entrada uniforme, documentar las rutas disponibles y permitir sobreescribir el destino mediante variables de entorno.

## Ejecucion local

1. Levanta el servicio de contactos (puerto 8080 por defecto):

        ./gradlew :contacts-service:bootRun

2. En otra terminal ejecuta el gateway (puerto 9090 por defecto):

        ./gradlew :gateway:bootRun

Variables relevantes:
- CONTACTS_SERVICE_URI: URL base del servicio de contactos (por defecto http://localhost:8080).
- GATEWAY_PORT: Puerto HTTP del gateway (por defecto 9090).

## Rutas publicadas

| Ruta gateway | Metodo | Ruta interna | Descripcion |
| --- | --- | --- | --- |
| /contacts | POST | /api/v1/contacts | Crea un contacto. Devuelve el recurso creado y la cabecera Location con el ID. |
| /contacts | GET | /api/v1/contacts | Lista contactos. Soporta el query param opcional nombre para filtrar por nombre. |
| /contacts/{id} | GET | /api/v1/contacts/{id} | Obtiene un contacto por su UUID. Responde 404 si no existe. |
| /contacts/{id} | PUT | /api/v1/contacts/{id} | Actualiza un contacto existente con los datos enviados. |
| /contacts/{id} | DELETE | /api/v1/contacts/{id} | Elimina el contacto indicado. Responde 204 sin cuerpo. |
| /contacts-api-docs | GET | /v3/api-docs | Expone el documento OpenAPI JSON generado por el servicio de contactos. |
| /contacts-api-docs/{*} | GET | /v3/api-docs/{*} | Permite acceder a variantes segmentadas del contrato, por ejemplo /v3/api-docs/contact. |
| /contacts-swagger/** | GET | /swagger-ui/** | Publica la interfaz Swagger UI. Accede a http://localhost:9090/contacts-swagger/index.html. |
| /contacts-actuator/** | GET | /actuator/** | Reexpone los endpoints de observabilidad (health, info, etc.). |

Las rutas REST principales trabajan sobre objetos Contact con la siguiente estructura (valores de ejemplo):

        {
          "id": "9b0f8a46-2f8f-4c1f-9e4a-6cfa6131a9f4",
          "nombre": "Ada Lovelace",
          "direccion": "10 Downing St, London",
          "telefono": "+44 20 7222 1234",
          "email": "ada@example.com"
        }

Validaciones aplicadas por el servicio de contactos:
- nombre: texto de 2 a 100 caracteres.
- email: debe tener formato valido.
- telefono: opcional, 7 a 20 caracteres numericos y simbolos comunes.
- direccion: opcional, hasta 200 caracteres.

## Como funciona

Spring Cloud Gateway aplica filtros RewritePath para traducir los prefijos expuestos (por ejemplo /contacts y /contacts-api-docs) a las rutas reales del servicio. El destino se controla con la variable CONTACTS_SERVICE_URI, por lo que es sencillo apuntar el gateway a otro entorno (QA, produccion, etc.) sin recompilar.

Los encabezados Access-Control-Allow-Origin y Access-Control-Allow-Credentials se deduplican automaticamente para evitar respuestas duplicadas cuando los servicios de origen y el gateway agregan el mismo valor, lo que simplifica escenarios de CORS.

## Proximos pasos sugeridos

- Anadir autenticacion (por ejemplo OAuth2 o API keys) directamente en el gateway si las APIs necesitan proteccion.
- Instrumentar metricas del gateway (endpoint /actuator/metrics) y agregarlas a su stack de observabilidad.
- Automatizar despliegues conjuntos del servicio de contactos y del gateway mediante la plataforma preferida (Docker Compose, Kubernetes, etc.).
