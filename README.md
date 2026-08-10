# JobTracker

Application de gestion de candidatures développée avec **Java Spring Boot**.

JobTracker permet de centraliser le suivi des candidatures en manipulant les entreprises, les offres associées et leur évolution dans le processus de recrutement.

Le projet a pour objectif de mettre en pratique une architecture backend professionnelle :
- API REST
- séparation des responsabilités
- persistance relationnelle
- tests automatisés
- conteneurisation
- intégration continue


## Architecture

Spring Boot REST API
|
|
Spring Data JPA
|
|
PostgreSQL

Une interface Angular est prévue pour compléter l'application.


## Stack technique

### Backend

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Bean Validation
- Maven

### Base de données

- PostgreSQL (production)
- H2 (tests)

### Tests

- JUnit 5
- Mockito
- Spring Boot Test

### DevOps

- Docker
- Docker Compose
- GitHub Actions


## Fonctionnalités

### Gestion des entreprises

- Création d'une entreprise
- Consultation des entreprises
- Modification d'une entreprise
- Suppression d'une entreprise

### Gestion des offres

- Création d'une offre
- Consultation des offres
- Modification d'une offre
- Suppression d'une offre
- Filtrage par statut
- Filtrage par entreprise


## Architecture du backend

Le projet suit une architecture en couches :

controller/
Gestion des requêtes HTTP
service/
Logique métier
repository/
Accès aux données
entity/
Modèle de données
exception/
Gestion globale des erreurs


## Configuration

Le projet utilise plusieurs profils Spring :

- `application.properties`
- `application-test.properties`
- `application-prod.properties`

Les informations sensibles sont configurées via variables d'environnement.


## Lancer le projet


### Prérequis

- Java 17
- Maven
- Docker


### Avec Docker Compose

```bash
docker compose up --build
```

Avec Maven
./mvnw spring-boot:run
Documentation API
La documentation des endpoints est disponible via Swagger/OpenAPI.
URL :
http://localhost:8080/swagger-ui/index.html
Tests
Lancer les tests :
./mvnw test
Le projet contient :
tests unitaires des services
tests d'intégration de l'API
CI/CD
Une pipeline GitHub Actions automatise :
exécution des tests
build Maven
build de l'image Docker
Roadmap

API REST Spring Boot

PostgreSQL

Docker Compose

Tests automatisés

Documentation Swagger

CI GitHub Actions

Interface Angular

Déploiement cloud