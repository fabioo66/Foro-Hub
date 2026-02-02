<div align="center">

# 🚀 ForoHub API

### Plataforma RESTful API para gestión de foros de discusión

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](LICENSE)
[![Status](https://img.shields.io/badge/Status-En%20Desarrollo-yellow?style=for-the-badge)]()

</div>

---

## 📋 Índice

- [Descripción del Proyecto](#-descripción-del-proyecto)
- [Estado del Proyecto](#-estado-del-proyecto)
- [Demostración de Funciones](#-demostración-de-funciones-y-aplicaciones)
- [Acceso al Proyecto](#-acceso-al-proyecto)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Características Principales](#-características-principales)
- [Instalación y Configuración](#️-instalación-y-configuración)
- [Uso de la API](#-uso-de-la-api)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Documentación de la API](#-documentación-de-la-api)
- [Desarrolladores](#-personas-desarrolladoras-del-proyecto)
- [Licencia](#-licencia)

---

## 📝 Descripción del Proyecto

**ForoHub API** es una API REST robusta y escalable desarrollada con Spring Boot que permite la gestión completa de un sistema de foros de discusión. La plataforma proporciona un backend completo para crear, listar, actualizar y eliminar tópicos de discusión, con un sistema de autenticación seguro basado en JWT (JSON Web Tokens).

### ¿Para qué sirve?

Este proyecto está diseñado para ser el backend de una plataforma de foros donde los usuarios pueden:
- 💬 Crear y gestionar tópicos de discusión
- 🔐 Autenticarse de forma segura
- 📊 Consultar tópicos con paginación
- ✏️ Actualizar el estado de las discusiones
- 🗑️ Eliminar tópicos (borrado lógico)

La API implementa las mejores prácticas de desarrollo, incluyendo validaciones, manejo de excepciones, seguridad con Spring Security, documentación automática con Swagger/OpenAPI y migraciones de base de datos con Flyway.

---

## 📊 Estado del Proyecto

<div align="center">

### ⚠️ En Desarrollo Activo

![Progreso](https://img.shields.io/badge/Progreso-80%25-yellow?style=for-the-badge)

</div>

**Versión Actual:** v0.0.1-SNAPSHOT

### ✅ Funcionalidades Implementadas:
- ✔️ Sistema de autenticación con JWT
- ✔️ CRUD completo de tópicos
- ✔️ Paginación y ordenamiento de resultados
- ✔️ Validaciones de datos
- ✔️ Seguridad con Spring Security
- ✔️ Documentación Swagger/OpenAPI
- ✔️ Migraciones de base de datos con Flyway

### 🚧 Próximas Características:
- ⏳ **Sistema de Respuestas**: Implementar respuestas completas a los tópicos con jerarquía de comentarios
- ⏳ **Gestión de Usuarios**: CRUD completo de usuarios con roles y permisos
- ⏳ **Perfiles de Usuario**: Perfiles personalizables con información adicional
- ⏳ Sistema de votación (upvotes/downvotes)
- ⏳ Búsqueda avanzada de tópicos con filtros
- ⏳ Notificaciones en tiempo real
- ⏳ Sistema de etiquetas/tags para tópicos
- ⏳ Tests unitarios y de integración completos

---

## 🎯 Demostración de Funciones y Aplicaciones

### 🔐 Autenticación

La API utiliza JWT para autenticación segura:

```json
POST /login
{
  "username": "usuario@ejemplo.com",
  "password": "contraseña123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 📝 Crear un Tópico

```json
POST /topicos
Headers: Authorization: Bearer {token}

{
  "titulo": "¿Cómo implementar Spring Security?",
  "mensaje": "Necesito ayuda para configurar Spring Security en mi proyecto",
  "autor": "Juan Pérez",
  "curso": "Spring Boot Avanzado"
}
```

### 📖 Listar Tópicos

```http
GET /topicos?page=0&size=10&sort=fechaCreacion,asc
Headers: Authorization: Bearer {token}
```

Respuesta paginada con información de los tópicos activos, ordenados por fecha de creación.

### ✏️ Actualizar un Tópico

```json
PUT /topicos/{id}
Headers: Authorization: Bearer {token}

{
  "titulo": "Título actualizado",
  "mensaje": "Mensaje actualizado",
  "estado": "CERRADO"
}
```

### 🗑️ Eliminar un Tópico

```http
DELETE /topicos/{id}
Headers: Authorization: Bearer {token}
```

Implementa borrado lógico, marcando el tópico como inactivo sin eliminarlo físicamente de la base de datos.

---

## 🔓 Acceso al Proyecto

### 📥 Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/forohub-api.git
cd forohub-api
```

### 📦 Descargar ZIP

Podes descargar el proyecto directamente desde GitHub como archivo ZIP:

[⬇️ Descargar ForoHub API](https://github.com/tu-usuario/forohub-api/archive/refs/heads/main.zip)

---

## 🛠️ Tecnologías Utilizadas

<div align="center">

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white) | 17 | Lenguaje de programación principal |
| ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) | 3.5.10 | Framework para desarrollo de aplicaciones |
| ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat&logo=spring-security&logoColor=white) | 3.x | Autenticación y autorización |
| ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=flat&logo=spring&logoColor=white) | 3.x | Persistencia de datos |
| ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white) | 8.0+ | Base de datos relacional |
| ![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=flat&logo=flyway&logoColor=white) | Latest | Migraciones de base de datos |
| ![JWT](https://img.shields.io/badge/JWT-000000?style=flat&logo=json-web-tokens&logoColor=white) | 4.5.0 | Tokens de autenticación |
| ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=swagger&logoColor=black) | 2.8.15 | Documentación de API |
| ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white) | 3.x | Gestión de dependencias |
| ![Lombok](https://img.shields.io/badge/Lombok-BC4521?style=flat&logo=lombok&logoColor=white) | Latest | Reducción de código boilerplate |

</div>

### 📚 Dependencias Principales

```xml
<!-- Spring Boot Starters -->
spring-boot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-validation

<!-- Base de Datos -->
mysql-connector-j
flyway-core
flyway-mysql

<!-- Seguridad -->
java-jwt (Auth0)

<!-- Documentación -->
springdoc-openapi-starter-webmvc-ui

<!-- Herramientas -->
lombok
spring-boot-devtools
```

---

## ⚡ Características Principales

- 🔒 **Seguridad Robusta**: Implementación completa de Spring Security con JWT
- 📄 **Paginación**: Listado eficiente de tópicos con soporte para paginación
- ✅ **Validaciones**: Validación de datos de entrada con Bean Validation
- 🗃️ **Migraciones**: Control de versiones de base de datos con Flyway
- 📖 **Documentación Automática**: Swagger UI para probar endpoints
- 🛡️ **Manejo de Errores**: Sistema centralizado de manejo de excepciones
- 🔄 **Borrado Lógico**: Los registros se desactivan en lugar de eliminarse
- 🎯 **RESTful**: Diseño de API siguiendo principios REST
- 🔍 **Validación de Duplicados**: Prevención de tópicos duplicados
- 📊 **Estados de Tópicos**: Sistema de estados (ABIERTO, CERRADO)

---

## ⚙️ Instalación y Configuración

### 📋 Prerrequisitos

Antes de comenzar, asegúrate de tener instalado:

- ☕ **Java JDK 17** o superior
- 🗄️ **MySQL 8.0** o superior
- 📦 **Maven 3.6** o superior
- 🔧 **IDE** (IntelliJ IDEA, Eclipse, VS Code, etc.)

### 🚀 Pasos de Instalación

#### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/forohub-api.git
cd forohub-api
```

#### 2️⃣ Configurar la Base de Datos

Crea una base de datos en MySQL:

```sql
CREATE DATABASE forohub_api;
```

#### 3️⃣ Configurar Variables de Entorno

Edita el archivo `src/main/resources/application.properties`:

```properties
# Base de Datos
spring.datasource.url=jdbc:mysql://localhost/forohub_api
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# JWT Secret (Opcional - Variable de entorno recomendada)
api.security.token.secret=${JWT_SECRET:tu_clave_secreta_segura}
```

**🔐 Configuración de JWT Secret (Recomendado):**

Establece la variable de entorno `JWT_SECRET`:

**Windows (PowerShell):**
```powershell
$env:JWT_SECRET="tu_clave_secreta_muy_segura_y_larga"
```

**Linux/Mac:**
```bash
export JWT_SECRET="tu_clave_secreta_muy_segura_y_larga"
```

#### 4️⃣ Instalar Dependencias

```bash
mvn clean install
```

#### 5️⃣ Ejecutar las Migraciones

Las migraciones de Flyway se ejecutan automáticamente al iniciar la aplicación.

#### 6️⃣ Iniciar la Aplicación

```bash
mvn spring-boot:run
```

O desde tu IDE, ejecuta la clase `ForohubApiApplication.java`

#### 7️⃣ Verificar la Instalación

La aplicación estará disponible en: `http://localhost:8080`

Accede a la documentación Swagger: `http://localhost:8080/swagger-ui.html`

---

## 📘 Uso de la API

### 🔑 Autenticación

Todos los endpoints (excepto `/login`) requieren un token JWT en el header:

```http
Authorization: Bearer {tu_token_jwt}
```

### 📍 Endpoints Disponibles

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/login` | Iniciar sesión y obtener token | ❌ No |
| POST | `/topicos` | Crear un nuevo tópico | ✅ Sí |
| GET | `/topicos` | Listar todos los tópicos (paginado) | ✅ Sí |
| GET | `/topicos/{id}` | Obtener un tópico específico | ✅ Sí |
| PUT | `/topicos/{id}` | Actualizar un tópico | ✅ Sí |
| DELETE | `/topicos/{id}` | Eliminar un tópico (borrado lógico) | ✅ Sí |

### 📝 Ejemplos de Uso

#### Obtener Token de Autenticación

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario@ejemplo.com",
    "password": "password123"
  }'
```

#### Crear un Tópico

```bash
curl -X POST http://localhost:8080/topicos \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Mi primer tópico",
    "mensaje": "Este es el contenido del tópico",
    "autor": "Fabio Torres",
    "curso": "Spring Boot"
  }'
```

#### Listar Tópicos

```bash
curl -X GET "http://localhost:8080/topicos?page=0&size=10" \
  -H "Authorization: Bearer {token}"
```

---

## 📁 Estructura del Proyecto

```
forohub-api/
├── 📂 src/
│   ├── 📂 main/
│   │   ├── 📂 java/com/fabio/forohub/
│   │   │   ├── 📄 ForohubApiApplication.java      # Clase principal
│   │   │   ├── 📄 ValidacionException.java        # Excepción personalizada
│   │   │   ├── 📂 controller/
│   │   │   │   ├── 📄 AutenticacionController.java
│   │   │   │   └── 📄 TopicoController.java
│   │   │   ├── 📂 domain/
│   │   │   │   ├── 📂 topico/
│   │   │   │   │   ├── 📄 Topico.java             # Entidad
│   │   │   │   │   ├── 📄 TopicoRepository.java   # Repositorio JPA
│   │   │   │   │   ├── 📄 CrearTopico.java        # Servicio
│   │   │   │   │   ├── 📄 DatosRegistroTopico.java
│   │   │   │   │   ├── 📄 DatosActualizacionTopico.java
│   │   │   │   │   ├── 📄 DatosDetalleTopico.java
│   │   │   │   │   ├── 📄 DatosListaTopico.java
│   │   │   │   │   └── 📄 Estado.java             # Enum
│   │   │   │   └── 📂 usuario/
│   │   │   │       ├── 📄 Usuario.java
│   │   │   │       ├── 📄 UsuarioRepository.java
│   │   │   │       ├── 📄 AutenticacionService.java
│   │   │   │       └── 📄 DatosRegistroUsuario.java
│   │   │   └── 📂 infra/
│   │   │       ├── 📂 security/
│   │   │       │   ├── 📄 SecurityConfigurations.java
│   │   │       │   ├── 📄 SecurityFilter.java
│   │   │       │   ├── 📄 TokenService.java
│   │   │       │   └── 📄 DatosTokenJWT.java
│   │   │       └── 📂 springdoc/
│   │   │           └── 📄 SpringDocConfiguration.java
│   │   └── 📂 resources/
│   │       ├── 📄 application.properties
│   │       └── 📂 db/migration/
│   │           ├── 📄 V1__create-table-topicos.sql
│   │           └── 📄 V2__create-table-usuarios.sql
│   └── 📂 test/
│       └── 📂 java/com/fabio/forohub/
│           └── 📄 ForohubApiApplicationTests.java
├── 📄 pom.xml                                      # Configuración Maven
├── 📄 README.md                                    # Este archivo
└── 📄 HELP.md
```

### 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas:

- **Controller**: Capa de presentación (REST endpoints)
- **Domain**: Lógica de negocio y entidades
- **Repository**: Capa de acceso a datos
- **Infra**: Configuración de infraestructura (seguridad, documentación)

---

## 📖 Documentación de la API

### 🌐 Swagger UI

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de Swagger:

```
http://localhost:8080/swagger-ui.html
```

Desde aquí puedes:
- 📋 Ver todos los endpoints disponibles
- 🧪 Probar las peticiones directamente
- 📝 Ver los modelos de datos
- 🔍 Consultar los códigos de respuesta

### 📄 OpenAPI JSON

La especificación OpenAPI en formato JSON está disponible en:

```
http://localhost:8080/v3/api-docs
```

---

## 👨‍💻 Personas Desarrolladoras del Proyecto

<div align="center">

### Fabio Ignacio Torrejon

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/fabioo66)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/fabio-ignacio-torrejon/)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:torrejonfabio@gmail.com)

**Desarrollador Backend | Spring Boot Specialist**

</div>

---

## 📄 Licencia

<div align="center">

Este proyecto está bajo la Licencia MIT - ve el archivo [LICENSE](LICENSE) para más detalles.

### MIT License

```
Copyright (c) 2026 Fabio Torres

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

![Hecho con Java](https://img.shields.io/badge/Hecho%20con-Java%20%E2%98%95-ED8B00?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Powered%20by-Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot)

</div>
