# 🏀 NBA Stats Engine

Motor de análisis estadístico de jugadores de la NBA con CLI interactivo, construido con Java 25 (LTS) + Spring Boot 3.4 + Gradle.

## Descripción

NBA Stats Engine permite buscar jugadores de la NBA y consultar sus estadísticas por partido directamente desde la línea de comandos. Los datos se obtienen de la API gratuita [BallDontLie](https://www.balldontlie.io) y se persisten localmente en base de datos para optimizar el rendimiento en consultas sucesivas.

## Requisitos

- **Java 25** (LTS) — instalar con [SDKMAN](https://sdkman.io/): `sdk install java 25-open`
- **Gradle 8.13+** (o usar el wrapper incluido: `./gradlew`)
- **API Key de BallDontLie** — registro gratuito en [balldontlie.io](https://www.balldontlie.io)

## Cómo ejecutar

### Modo desarrollo (H2 en memoria)

```bash
# Clonar el repositorio
git clone https://github.com/josegs98/nba-stats-engine.git
cd nba-stats-engine

# Exportar la API key
export BALLDONTLIE_API_KEY=tu_api_key_aqui

# Ejecutar la aplicación (usa el perfil dev por defecto)
./gradlew bootRun
```

### Modo producción (PostgreSQL)

```bash
# Asegúrate de tener PostgreSQL corriendo (o usa Docker):
docker run -d --name nba-postgres -p 5432:5432 \
  -e POSTGRES_DB=nbastats \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  postgres:16

# Ejecutar con perfil prod
export BALLDONTLIE_API_KEY=tu_api_key_aqui
export DB_URL=jdbc:postgresql://localhost:5432/nbastats
export DB_USERNAME=postgres
export DB_PASSWORD=postgres

./gradlew bootRun --args='--spring.profiles.active=prod'
```

## Estructura del proyecto

```
nba-stats-engine/
├── build.gradle.kts                          # Build Gradle con Kotlin DSL
├── settings.gradle.kts
├── gradlew / gradlew.bat                     # Gradle Wrapper
├── .gitignore
├── README.md
└── src/
    ├── main/
    │   ├── java/com/josegs98/nbastats/
    │   │   ├── NbaStatsApplication.java       # @SpringBootApplication entry point
    │   │   ├── cli/                           # Capa CLI interactiva
    │   │   │   ├── CliRunner.java             # CommandLineRunner con menú interactivo
    │   │   │   └── command/                   # Comandos del CLI
    │   │   │       ├── Command.java           # Sealed interface
    │   │   │       ├── SearchPlayerCommand.java
    │   │   │       ├── PlayerGameLogsCommand.java
    │   │   │       └── ExitCommand.java
    │   │   ├── client/                        # Cliente HTTP para BallDontLie API
    │   │   │   ├── BallDontLieClient.java
    │   │   │   ├── dto/                       # DTOs como Records
    │   │   │   │   ├── ApiPlayerResponse.java
    │   │   │   │   ├── ApiStatsResponse.java
    │   │   │   │   └── ApiPaginatedResponse.java
    │   │   │   └── config/
    │   │   │       └── RestClientConfig.java
    │   │   ├── domain/                        # Modelo de dominio y persistencia
    │   │   │   ├── model/
    │   │   │   │   ├── Player.java            # @Entity JPA
    │   │   │   │   ├── Team.java              # @Entity JPA
    │   │   │   │   └── GameStats.java         # @Entity JPA
    │   │   │   └── repository/
    │   │   │       ├── PlayerRepository.java
    │   │   │       ├── TeamRepository.java
    │   │   │       └── GameStatsRepository.java
    │   │   ├── service/                       # Lógica de negocio
    │   │   │   ├── PlayerService.java
    │   │   │   └── StatsIngestionService.java
    │   │   └── concurrency/                   # Placeholder para Fase 5 (Virtual Threads)
    │   │       └── package-info.java
    │   └── resources/
    │       ├── application.yml                # Config general + perfil activo por defecto
    │       ├── application-dev.yml            # Perfil dev: H2 in-memory
    │       └── application-prod.yml           # Perfil prod: PostgreSQL
    └── test/
        └── java/com/josegs98/nbastats/
            └── NbaStatsApplicationTests.java
```

## Features de Java 25 utilizadas

| Feature | Dónde se usa |
|---------|-------------|
| **Records** | DTOs de la API (`ApiPlayerResponse`, `ApiStatsResponse`, `ApiPaginatedResponse`) y comandos del CLI |
| **Sealed Classes** | `Command` interface sellada que permite solo `SearchPlayerCommand`, `PlayerGameLogsCommand` y `ExitCommand` |
| **Pattern Matching en switch** | `CliRunner.handleCommand()` para despacho de comandos de forma exhaustiva y segura en tipos |

## Roadmap de fases

| Fase | Objetivo | Estado |
|------|----------|--------|
| **1. Scaffolding** | Estructura del proyecto, Spring Boot + Gradle, configuración, conexión BD | ✅ Completada |
| **2. Cliente API** | `BallDontLieClient` con `RestClient`, DTOs como Records | ✅ Completada |
| **3. Dominio + Persistencia** | Entidades JPA, repositorios, lógica de caché (BD → API) | ✅ Completada |
| **4. CLI Interactivo** | Menú, búsqueda de jugadores, visualización de game logs | ✅ Completada |
| **5. Threading** | Ingesta paralela con Virtual Threads + Structured Concurrency | 🔜 Planificada |
| **6. Motor de Análisis** | Rankings, promedios, tendencias sobre datos locales | 🔜 Planificada |
| **7. Tests** | Tests unitarios de servicios, tests de integración | 🔜 Planificada |

## API utilizada

[BallDontLie](https://www.balldontlie.io) — API gratuita con datos NBA desde 1946 hasta la actualidad.
- Free tier: 60 peticiones/minuto
- Endpoints utilizados: `/players`, `/stats`
