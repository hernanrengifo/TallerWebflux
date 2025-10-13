# Spring Cloud Gateway

**Spring Cloud Gateway** define cómo el *gateway* enruta las solicitudes HTTP entrantes hacia diferentes servicios internos, aplicando filtros, reglas de reescritura de rutas y mecanismos de control centralizados.  
Su propósito principal es actuar como punto de entrada único (*entry point*) para las aplicaciones distribuidas, mejorando la seguridad, la observabilidad y la gestión de tráfico.

---

## 🔍 Descripción de los elementos principales

### `server.port`
Define el **puerto** en el que se ejecuta el gateway.
> Valor por defecto: `9090`

---

### `spring.application.name`
Especifica el **nombre de la aplicación gateway**, útil para propósitos de identificación y monitoreo.

---

### `spring.cloud.gateway.routes`
Lista las **rutas** que el gateway manejará.  
Cada ruta define cómo se redirige una solicitud HTTP hacia un servicio interno.

**Elementos dentro de una ruta:**
- **`id`** → Identificador único de la ruta.
- **`uri`** → Dirección del servicio de destino (puede variar según el entorno).
- **`predicates`** → Condiciones bajo las cuales se activa la ruta (por ejemplo, el *path* de la URL).
- **`filters`** → Filtros que pueden modificar la solicitud o la respuesta, como la **reescritura de rutas** o la **inyección de cabeceras**.

---

### `default-filters`
Filtros que se aplican **a todas las rutas** de forma global.  
Un ejemplo común es la **deduplicación de cabeceras CORS** o la aplicación de políticas de seguridad compartidas.

---

### `management.endpoints.web.exposure.include`
Configura qué **endpoints de administración** se exponen para fines de monitoreo y observabilidad.  
Por ejemplo: `health`, `info`, `metrics`.

---

Spring Cloud Gateway actúa como un **enrutador inteligente y reactivo**, integrando control de tráfico, seguridad y monitoreo en una arquitectura distribuida basada en microservicios.
