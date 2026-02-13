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

**ForoHub API** es una API REST robusta y escalable desarrollada con Spring Boot que permite la gestión completa de un sistema de foros de discusión. La plataforma proporciona un backend completo para crear, listar, actualizar y eliminar tópicos de discusión, responder a tópicos, gestionar usuarios, administrar cursos y cuenta con un sistema de autenticación seguro basado en JWT (JSON Web Tokens) con control de acceso por roles.

### ¿Para qué sirve?

Este proyecto está diseñado para ser el backend de una plataforma de foros donde los usuarios pueden:
- 💬 **Crear y gestionar tópicos de discusión** sobre diferentes cursos
- 📚 **Gestionar cursos** por categorías (solo administradores)
- 💭 **Responder a tópicos** y participar en discusiones
- ✅ **Marcar respuestas como solución** (autor del tópico o moderadores/admins)
- 🔐 **Autenticarse de forma segura** con JWT
- 👥 **Sistema de roles**: USER, MODERATOR, ADMIN con permisos diferenciados
- 👤 **Gestionar su cuenta** (actualizar email, eliminar cuenta)
- 📊 **Consultar tópicos, respuestas y cursos** con paginación y filtros
- ✏️ **Actualizar el estado** de tópicos (ABIERTO/CERRADO)
- 🗑️ **Eliminar contenido** (borrado lógico que mantiene integridad referencial)
- 🔒 **Protección de datos**: Solo el autor puede modificar/eliminar su contenido (o moderadores/admins)
- 🛡️ **Control de acceso basado en roles**: Permisos específicos por endpoint

La API implementa las mejores prácticas de desarrollo, incluyendo validaciones robustas, manejo centralizado de excepciones, seguridad con Spring Security, documentación automática con Swagger/OpenAPI, migraciones de base de datos con Flyway e inyección de dependencias por constructor para código limpio y testeable.

---

## 📊 Estado del Proyecto

<div align="center">

### ⚠️ En Desarrollo Activo

![Progreso](https://img.shields.io/badge/Progreso-80%25-yellow?style=for-the-badge)

</div>

**Versión Actual:** v0.0.1-SNAPSHOT

### ✅ Funcionalidades Implementadas:

#### 🔐 Autenticación y Seguridad
- ✔️ **Sistema de Autenticación JWT**: Login seguro con tokens JWT
- ✔️ **Registro de Usuarios**: Endpoint para crear nuevos usuarios con validación de email único
- ✔️ **Sistema de Roles**: Implementación completa de roles (USER, MODERATOR, ADMIN)
- ✔️ **Control de Acceso**: Permisos específicos por rol usando @PreAuthorize
- ✔️ **Validación de Usuarios Activos**: Solo usuarios habilitados pueden iniciar sesión
- ✔️ **Manejo de Usuarios Deshabilitados**: Mensaje específico al intentar login con cuenta inactiva

#### 📝 Gestión de Tópicos
- ✔️ **CRUD Completo de Tópicos**: Crear, listar, actualizar y eliminar tópicos
- ✔️ **Validación de Propietario**: Solo el autor puede modificar/eliminar sus tópicos (o moderadores/admins)
- ✔️ **Estados de Tópicos**: Sistema de estados (ABIERTO, CERRADO)
- ✔️ **Prevención de Duplicados**: Validación de tópicos duplicados (título + mensaje)
- ✔️ **Asociación con Cursos**: Cada tópico pertenece a un curso específico
- ✔️ **Cierre de Tópicos**: Moderadores y admins pueden cerrar tópicos ajenos
- ✔️ **Eliminación Protegida**: Moderadores y admins pueden eliminar tópicos ajenos

#### 💭 Sistema de Respuestas
- ✔️ **CRUD Completo de Respuestas**: Sistema de respuestas a tópicos con gestión completa
- ✔️ **Sistema de Soluciones**: Marcar respuestas como solución (autor del tópico, moderadores o admins)
- ✔️ **Filtros Avanzados**: Listar respuestas por tópico, usuario o ambos
- ✔️ **Validación de Propietario**: Solo el autor puede modificar/eliminar sus respuestas
- ✔️ **Respuestas Bidireccionales**: Relación completa entre tópicos y respuestas

#### 📚 Gestión de Cursos
- ✔️ **CRUD Completo de Cursos**: Solo administradores pueden gestionar cursos
- ✔️ **Categorías de Cursos**: Sistema de categorías con enum (BACKEND, FRONTEND, MOBILE, etc.)
- ✔️ **Filtrado por Categoría**: Listar cursos por categoría específica
- ✔️ **Validación de Cursos**: No permitir crear tópicos en cursos inactivos
- ✔️ **Endpoint de Categorías**: Listar todas las categorías disponibles
- ✔️ **Borrado Lógico**: Cursos desactivados sin afectar tópicos existentes

#### 👤 Gestión de Usuarios
- ✔️ **Actualización de Email**: Usuarios pueden actualizar su email con validación de unicidad
- ✔️ **Eliminación de Cuenta**: Borrado lógico de cuenta del usuario
- ✔️ **Desactivación por Admin**: Administradores pueden desactivar usuarios
- ✔️ **Perfil de Usuario**: Endpoint para obtener información del usuario autenticado
- ✔️ **Listado de Usuarios**: Solo administradores pueden listar todos los usuarios
- ✔️ **Deshabilitación en Cascada**: Al eliminar usuario se deshabilitan sus tópicos y respuestas

#### 🛡️ Control de Acceso por Roles
| Acción | USER | MODERATOR | ADMIN |
|--------|------|-----------|-------|
| Crear tópico | ✅ | ✅ | ✅ |
| Responder tópico | ✅ | ✅ | ✅ |
| Editar su respuesta | ✅ | ✅ | ✅ |
| Eliminar su respuesta | ✅ | ✅ | ✅ |
| Marcar solución (autor tópico) | ✅ | ✅ | ✅ |
| Marcar solución (no autor) | ❌ | ✅ | ✅ |
| Cerrar tópico ajeno | ❌ | ✅ | ✅ |
| Eliminar tópico ajeno | ❌ | ✅ | ✅ |
| CRUD cursos | ❌ | ❌ | ✅ |
| Desactivar usuarios | ❌ | ❌ | ✅ |
| Listar usuarios | ❌ | ❌ | ✅ |

#### 🔧 Características Técnicas
- ✔️ **Paginación y Ordenamiento**: Listados eficientes con soporte de paginación
- ✔️ **Validaciones Robustas**: Validación de datos con Bean Validation
- ✔️ **Seguridad Spring Security**: Protección de endpoints con JWT
- ✔️ **Manejo de Errores Centralizado**: Sistema global de excepciones con mensajes claros
- ✔️ **Manejo de Errores Específicos**: Handlers para AuthenticationException, AccessDeniedException, enum inválidos, etc.
- ✔️ **Documentación Swagger/OpenAPI**: Interfaz interactiva para probar la API
- ✔️ **Migraciones con Flyway**: Control de versiones de base de datos
- ✔️ **Inyección de Dependencias por Constructor**: Código limpio usando Lombok @RequiredArgsConstructor
- ✔️ **Relaciones Bidireccionales**: Usuario-Tópicos, Usuario-Respuestas, Tópico-Respuestas, Curso-Tópicos
- ✔️ **Borrado Lógico**: Los registros se desactivan en lugar de eliminarse físicamente
- ✔️ **Deshabilitación en Cascada**: Integridad referencial completa

### 🚧 Próximas Características:
- ⏳ **Verificación por Email**: Sistema de verificación por correo para actualización de email y contraseña
- ⏳ **Recuperación de Contraseña**: Funcionalidad de "olvidé mi contraseña" con token por email
- ⏳ **Tests Unitarios**: Cobertura completa de tests unitarios y de integración
- ⏳ Sistema de votación (upvotes/downvotes)
- ⏳ Búsqueda avanzada de tópicos con filtros múltiples
- ⏳ Notificaciones en tiempo real
- ⏳ Sistema de etiquetas/tags para tópicos
- ⏳ Perfiles de usuario personalizables
- ⏳ Estadísticas y métricas del foro

---

## 🎯 Demostración de Funciones y Aplicaciones

### 🔐 Autenticación

La API utiliza JWT para autenticación segura:

```json
POST /auth/login
{
  "email": "usuario@ejemplo.com",
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
  "curso": "Spring Boot Avanzado"
}
```

### 📖 Listar Tópicos

```http
GET /topicos?page=0&size=10&sort=fechaCreacion,asc
Headers: Authorization: Bearer {token}
```

Respuesta paginada con información de los tópicos activos, ordenados por fecha de creación.

### 💭 Crear una Respuesta

```json
POST /respuestas
Headers: Authorization: Bearer {token}

{
  "idTopico": 1,
  "mensaje": "Puedes seguir esta guía oficial de Spring Security..."
}
```

### ✅ Marcar Respuesta como Solución

```http
PATCH /respuestas/{id}/solucion
Headers: Authorization: Bearer {token}
```

Solo el autor del tópico puede marcar respuestas como solución.

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

Implementa borrado lógico, marcando el tópico como inactivo sin eliminarlo físicamente de la base de datos. También deshabilita todas las respuestas asociadas.

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
- 🔐 **Autenticación y Registro**: Sistema completo de login y registro de usuarios
- 👥 **Sistema de Roles**: Control de acceso basado en roles (USER, MODERATOR, ADMIN)
- 📚 **Gestión de Cursos**: CRUD completo de cursos con categorías (solo administradores)
- 💭 **Sistema de Respuestas**: Respuestas completas a tópicos con validación de propietario
- ✅ **Soluciones**: Marcar respuestas como solución (autor del tópico, moderadores o admins)
- 👤 **Gestión de Usuarios**: Actualización de email, eliminación de cuenta, listado (admin)
- 📄 **Paginación**: Listado eficiente de tópicos, respuestas, cursos y usuarios con soporte para paginación
- 🔍 **Filtros Avanzados**: Listar respuestas por tópico, usuario o combinación; cursos por categoría
- ✅ **Validaciones**: Validación de datos de entrada con Bean Validation
- 🗃️ **Migraciones**: Control de versiones de base de datos con Flyway
- 📖 **Documentación Automática**: Swagger UI para documentación interactiva de la API
- 🛡️ **Manejo de Errores**: Sistema centralizado de manejo de excepciones con mensajes claros
- 🔄 **Borrado Lógico**: Los registros se desactivan en lugar de eliminarse
- 🔗 **Deshabilitación en Cascada**: Al eliminar usuario/tópico se deshabilitan sus dependencias
- 🎯 **RESTful**: Diseño de API siguiendo principios REST
- 🔍 **Validación de Duplicados**: Prevención de tópicos duplicados y cursos duplicados activos
- 📊 **Estados de Tópicos**: Sistema de estados (ABIERTO, CERRADO)
- 🧩 **Código Limpio**: Inyección de dependencias por constructor con Lombok
- 🔐 **Usuarios Deshabilitados**: Manejo específico de cuentas inactivas
- 🛡️ **Control de Permisos**: Validación de propietario y permisos por rol en operaciones sensibles

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

#### 🔐 Autenticación
| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/auth/login` | Iniciar sesión y obtener token | ❌ No |
| POST | `/auth/register` | Registrar un nuevo usuario | ❌ No |

#### 📝 Tópicos
| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/topicos` | Crear un nuevo tópico | ✅ Sí |
| GET | `/topicos` | Listar todos los tópicos (paginado) | ✅ Sí |
| GET | `/topicos/{id}` | Obtener un tópico específico | ✅ Sí |
| PUT | `/topicos/{id}` | Actualizar un tópico (solo autor) | ✅ Sí |
| DELETE | `/topicos/{id}` | Eliminar un tópico (solo autor) | ✅ Sí |

#### 💭 Respuestas
| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/respuestas` | Crear una respuesta a un tópico | ✅ Sí |
| GET | `/respuestas` | Listar respuestas (con filtros) | ✅ Sí |
| GET | `/respuestas/{id}` | Obtener una respuesta específica | ✅ Sí |
| PUT | `/respuestas/{id}` | Actualizar una respuesta (solo autor) | ✅ Sí |
| PATCH | `/respuestas/{id}/solucion` | Marcar respuesta como solución | ✅ Sí |
| DELETE | `/respuestas/{id}` | Eliminar una respuesta (solo autor) | ✅ Sí |

#### 📚 Cursos
| Método | Endpoint | Descripción | Autenticación | Rol Requerido |
|--------|----------|-------------|---------------|---------------|
| POST | `/cursos` | Crear un nuevo curso | ✅ Sí | ADMIN |
| GET | `/cursos` | Listar todos los cursos (con filtro por categoría) | ✅ Sí | Todos |
| GET | `/cursos/categorias` | Listar todas las categorías disponibles | ✅ Sí | Todos |
| PUT | `/cursos/{id}` | Actualizar un curso | ✅ Sí | ADMIN |
| DELETE | `/cursos/{id}` | Eliminar un curso (borrado lógico) | ✅ Sí | ADMIN |

#### 👤 Usuarios
| Método | Endpoint | Descripción | Autenticación | Rol Requerido |
|--------|----------|-------------|---------------|---------------|
| GET | `/usuarios/me` | Obtener perfil del usuario autenticado | ✅ Sí | USER/MODERATOR |
| GET | `/usuarios` | Listar todos los usuarios (paginado) | ✅ Sí | ADMIN |
| PUT | `/usuarios` | Actualizar email del usuario | ✅ Sí | USER/MODERATOR |
| DELETE | `/usuarios` | Eliminar cuenta propia (borrado lógico) | ✅ Sí | USER/MODERATOR |
| DELETE | `/usuarios/{id}` | Desactivar un usuario | ✅ Sí | ADMIN |

### 📝 Ejemplos de Uso

#### 🔐 Registro de Usuario

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@ejemplo.com",
    "password": "password123"
  }'
```

#### 🔑 Obtener Token de Autenticación

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@ejemplo.com",
    "password": "password123"
  }'
```

#### 📝 Crear un Tópico

```bash
curl -X POST http://localhost:8080/topicos \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "¿Cómo implementar Spring Security?",
    "mensaje": "Necesito ayuda para configurar Spring Security",
    "curso": "Spring Boot Avanzado"
  }'
```

#### 📖 Listar Tópicos

```bash
curl -X GET "http://localhost:8080/topicos?page=0&size=10&sort=fechaCreacion,asc" \
  -H "Authorization: Bearer {token}"
```

#### 💭 Crear una Respuesta

```bash
curl -X POST http://localhost:8080/respuestas \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "idTopico": 1,
    "mensaje": "Puedes seguir esta guía oficial..."
  }'
```

#### 🔍 Listar Respuestas por Tópico

```bash
curl -X GET "http://localhost:8080/respuestas?idTopico=1&page=0&size=10" \
  -H "Authorization: Bearer {token}"
```

#### ✅ Marcar Respuesta como Solución

```bash
curl -X PATCH http://localhost:8080/respuestas/1/solucion \
  -H "Authorization: Bearer {token}"
```

#### 👤 Actualizar Email del Usuario

```bash
curl -X PUT http://localhost:8080/usuarios \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "nuevo@ejemplo.com"
  }'
```

#### 📚 Crear un Curso (Solo ADMIN)

```bash
curl -X POST http://localhost:8080/cursos \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Spring Boot Avanzado",
    "categoria": "BACKEND"
  }'
```

#### 📖 Listar Cursos por Categoría

```bash
curl -X GET "http://localhost:8080/cursos?categoria=BACKEND&page=0&size=10" \
  -H "Authorization: Bearer {token}"
```

#### 🏷️ Listar Categorías de Cursos

```bash
curl -X GET http://localhost:8080/cursos/categorias \
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
│   │   │   ├── 📂 controller/
│   │   │   │   ├── 📄 AutenticacionController.java
│   │   │   │   ├── 📄 TopicoController.java
│   │   │   │   ├── 📄 RespuestaController.java
│   │   │   │   ├── 📄 CursoController.java
│   │   │   │   └── 📄 UsuarioController.java
│   │   │   ├── 📂 domain/
│   │   │   │   ├── 📂 topico/
│   │   │   │   │   ├── 📄 Topico.java             # Entidad
│   │   │   │   │   ├── 📄 TopicoRepository.java   # Repositorio JPA
│   │   │   │   │   ├── 📄 Estado.java             # Enum
│   │   │   │   │   └── 📂 dto/
│   │   │   │   │       ├── 📄 DatosRegistroTopico.java
│   │   │   │   │       ├── 📄 DatosActualizacionTopico.java
│   │   │   │   │       ├── 📄 DatosDetalleTopico.java
│   │   │   │   │       └── 📄 DatosListaTopico.java
│   │   │   │   ├── 📂 respuesta/
│   │   │   │   │   ├── 📄 Respuesta.java          # Entidad
│   │   │   │   │   ├── 📄 RespuestaRepository.java
│   │   │   │   │   └── 📂 dto/
│   │   │   │   │       ├── 📄 DatosRegistroRespuesta.java
│   │   │   │   │       ├── 📄 DatosActualizacionRespuesta.java
│   │   │   │   │       └── 📄 DatosDetalleRespuesta.java
│   │   │   │   ├── 📂 curso/
│   │   │   │   │   ├── 📄 Curso.java              # Entidad
│   │   │   │   │   ├── 📄 CursoRepository.java
│   │   │   │   │   ├── 📄 Categoria.java          # Enum
│   │   │   │   │   └── 📂 dto/
│   │   │   │   │       ├── 📄 DatosRegistroCurso.java
│   │   │   │   │       ├── 📄 DatosActualizacionCurso.java
│   │   │   │   │       └── 📄 DatosDetalleCurso.java
│   │   │   │   └── 📂 usuario/
│   │   │   │       ├── 📄 Usuario.java
│   │   │   │       ├── 📄 UsuarioRepository.java
│   │   │   │       ├── 📄 Rol.java                # Enum
│   │   │   │       └── 📂 dto/
│   │   │   │           ├── 📄 DatosRegistroUsuario.java
│   │   │   │           ├── 📄 DatosLoginUsuario.java
│   │   │   │           ├── 📄 DatosActualizacionEmail.java
│   │   │   │           └── 📄 DatosRespuestaUsuario.java
│   │   │   ├── 📂 service/
│   │   │   │   ├── 📄 AutenticacionService.java
│   │   │   │   ├── 📄 TopicoService.java
│   │   │   │   ├── 📄 RespuestaService.java
│   │   │   │   ├── 📄 CursoService.java
│   │   │   │   ├── 📄 UsuarioService.java
│   │   │   │   ├── 📄 UsuarioDetailsService.java
│   │   │   │   └── 📄 TopicoValidacionService.java
│   │   │   └── 📂 infra/
│   │   │       ├── 📂 exception/
│   │   │       │   ├── 📄 GlobalExceptionHandler.java
│   │   │       │   ├── 📄 ErrorResponse.java
│   │   │       │   └── 📄 ValidacionException.java
│   │   │       ├── 📂 security/
│   │   │       │   ├── 📄 SecurityConfigurations.java
│   │   │       │   ├── 📄 SecurityFilter.java
│   │   │       │   ├── 📄 TokenService.java
│   │   │       │   ├── 📄 DatosTokenJWT.java
│   │   │       │   ├── 📄 RoleConstants.java
│   │   │       │   ├── 📄 CustomAuthenticationEntryPoint.java
│   │   │       │   └── 📄 CustomAccessDeniedHandler.java
│   │   │       └── 📂 springdoc/
│   │   │           └── 📄 SpringDocConfiguration.java
│   │   └── 📂 resources/
│   │       ├── 📄 application.properties
│   │       └── 📂 db/migration/
│   │           ├── 📄 V1__create-table-topicos.sql
│   │           ├── 📄 V2__create-table-usuarios.sql
│   │           ├── 📄 V3__add-autor-to-topicos.sql
│   │           ├── 📄 V4__create-table-respuestas.sql
│   │           ├── 📄 V5__add-activo-to-usuarios.sql
│   │           ├── 📄 V6__create-table-cursos.sql
│   │           ├── 📄 V7__alter-topicos-add-curso-fk.sql
│   │           ├── 📄 V8__alter-cursos-remove-unique-nombre.sql
│   │           ├── 📄 V9__add-rol-to-usuarios.sql
│   │           ├── 📄 V10__update-rol-values.sql
│   │           ├── 📄 V11__add-unique-constraint-curso-nombre-activo.sql
│   │           └── 📄 V12__alter-usuarios-add-unique-email-activo.sql
│   └── 📂 test/
│       └── 📂 java/com/fabio/forohub/
│           └── 📄 ForohubApiApplicationTests.java
├── 📄 pom.xml                                      # Configuración Maven
├── 📄 README.md                                    # Este archivo
└── 📄 HELP.md
```

### 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas limpia y escalable:

- **Controller**: Capa de presentación (REST endpoints)
- **Service**: Lógica de negocio y orquestación
- **Domain**: Entidades, repositorios y DTOs
- **Infra**: Configuración de infraestructura (seguridad, documentación, excepciones)

### 🔧 Patrones y Buenas Prácticas Implementadas:

- ✅ **Inyección de Dependencias por Constructor**: Usando Lombok @RequiredArgsConstructor
- ✅ **DTOs (Data Transfer Objects)**: Separación clara entre entidades y datos de API
- ✅ **Repository Pattern**: Abstracción de acceso a datos con Spring Data JPA
- ✅ **Service Layer**: Lógica de negocio separada de los controllers
- ✅ **Exception Handling Global**: Manejo centralizado de errores
- ✅ **Validaciones**: Bean Validation en DTOs
- ✅ **Security Filter Chain**: Autenticación JWT con Spring Security
- ✅ **Migraciones Versionadas**: Control de esquema con Flyway

---

## 📖 Documentación de la API

### 🌐 Swagger UI

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación interactiva de Swagger:

```
http://localhost:8080/swagger-ui/index.html#/
```

Desde aquí podes:
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
