# JobTracker

Application de gestion de candidatures développée avec Java (Spring Boot).

JobTracker centralise le suivi des candidatures : entreprises, offres et étapes du processus de recrutement.

Table des matières
- Fonctionnalités
- Quick Start
- Configuration
- Documentation API
- Exemples d'appels (curl)
- Structure du projet
- Stack technique
- Tests
- CI / Déploiement
- Contribution
- Roadmap


Fonctionnalités
- Gestion des entreprises : création, lecture, mise à jour, suppression
- Gestion des offres : création, lecture, mise à jour, suppression
- Filtrage des offres par statut et par entreprise
- Historique / suivi des étapes de candidature
- Tests unitaires et d'intégration


Quick Start
Prérequis
- Java 17 (ou OpenJDK 17)
- Maven (ou wrapper ./mvnw)
- Docker & Docker Compose (optionnel pour exécution complète)

Démarrer avec Docker Compose
```bash
docker compose up --build
```
Lancer localement avec Maven
```bash
./mvnw spring-boot:run
```

Accéder à l'API
- URL par défaut : http://localhost:8080
- Swagger UI : http://localhost:8080/swagger-ui/index.html


Configuration
Le projet utilise des profils Spring :
- application.properties (défaut)
- application-test.properties (tests)
- application-prod.properties (production)

Variables d'environnement recommandées (exemples) :
- SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/jobtracker
- SPRING_DATASOURCE_USERNAME=jobuser
- SPRING_DATASOURCE_PASSWORD=secret
- SPRING_PROFILES_ACTIVE=dev

Conseil : privilégier les variables d'environnement ou un secret manager en production.


Documentation API
La documentation OpenAPI/Swagger est exposée via Springdoc.
Accès : http://localhost:8080/swagger-ui/index.html


Exemples d'appels (curl)
- Créer une entreprise :
```bash
curl -X POST "http://localhost:8080/api/companies" \
  -H "Content-Type: application/json" \
  -d '{"name":"Acme Corp","website":"https://acme.example"}'
```
- Lister les entreprises :
```bash
curl http://localhost:8080/api/companies
```
- Créer une offre (associée à une entreprise) :
```bash
curl -X POST "http://localhost:8080/api/offers" \
  -H "Content-Type: application/json" \
  -d '{"title":"Backend Developer","companyId":1,"status":"APPLIED"}'
```
- Lister les offres et filtrer par statut :
```bash
curl "http://localhost:8080/api/offers?status=APPLIED"
```

(Remarque : ajuster les URLs si les endpoints diffèrent dans le code)


Structure du projet (résumé)
- src/main/java/.../controller : points d'entrée HTTP
- src/main/java/.../service : logique métier
- src/main/java/.../repository : accès aux données (Spring Data JPA)
- src/main/java/.../entity : entités JPA
- src/main/java/.../exception : gestion des erreurs
- src/test : tests unitaires et d'intégration


Stack technique
Backend
- Java 17
- Spring Boot (Web, Data JPA)
- Hibernate
- Bean Validation
- Maven

Base de données
- PostgreSQL (production)
- H2 (tests)

Tests
- JUnit 5
- Mockito
- Spring Boot Test

DevOps
- Docker & Docker Compose
- GitHub Actions (CI)


Tests
Lancer la suite de tests :
```bash
./mvnw test
```
Le projet contient des tests unitaires pour les services et des tests d'intégration pour l'API.


CI / Déploiement
Une pipeline GitHub Actions :
- exécution des tests
- build Maven
- construction de l'image Docker


Contribution
Les contributions sont bienvenues.
- Forkez le dépôt
- Créez une branche feature/bugfix
- Ouvrez une pull request avec une description claire

Un fichier CONTRIBUTING.md peut être ajouté si nécessaire.


Roadmap (priorités)
- Finaliser l'API REST et la documentation OpenAPI
- Interface frontend (Angular)
- Déploiement cloud (Heroku / Cloud provider)
- Amélioration du modèle de suivi des candidatures


Licence
Ajoutez ici le type de licence (ex: MIT) si le projet est open-source.


Contact
Pour toute question, ouvrir une issue sur le dépôt GitHub.

