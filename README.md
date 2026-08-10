# 💼 JobTracker

Bienvenue dans **JobTracker**, une application développée avec **Java Spring Boot** permettant de centraliser ses opportunités de recrutement.

Ce projet a pour objectif de mettre en pratique une architecture backend professionnelle avec une **API REST**, une **base de données relationnelle**, des **tests automatisés** et une **intégration continue**.

---

## 🎯 Objectif du projet

JobTracker permet de gérer :

- les entreprises
- les offres d'emploi
- le suivi des opportunités de recrutement

L'objectif principal est de travailler sur :

- la conception d'une API REST
- la séparation des responsabilités (**Controller / Service / Repository**)
- la persistance des données avec **JPA/Hibernate**
- la validation des données et la gestion des erreurs
- l'automatisation avec **Docker** et **GitHub Actions**

---

### 🛠️ Technologies utilisées

### Backend

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Bean Validation
- Maven

### Base de données

- PostgreSQL
- H2 (tests)

### DevOps

- Docker
- Docker Compose
- GitHub Actions
- Qodana (analyse statique du code)

### Tests

- JUnit 5
- Mockito
- Spring Boot Test

---

## 🚀 Fonctionnalités implémentées

### Entreprises

✅ Création d'une entreprise  
✅ Consultation des entreprises  
✅ Modification d'une entreprise  
✅ Suppression d'une entreprise

### Offres d'emploi

✅ Création d'une offre  
✅ Consultation des offres  
✅ Modification d'une offre  
✅ Suppression d'une offre  
✅ Filtrage par statut  
✅ Filtrage par entreprise

### Qualité du projet

✅ Validation des données  
✅ Gestion globale des exceptions  
✅ Codes HTTP adaptés  
✅ Tests unitaires  
✅ Tests d'intégration  
✅ Documentation Swagger/OpenAPI

---

## 🏗️ Architecture

L'application suit une architecture en couches :

```text
              Client HTTP
                  |
                  ↓
          Spring Boot Controller
                  |
                  ↓
               Service
                  |
                  ↓
          Repository / JPA
                  |
                  ↓
             PostgreSQL

        (Docker Compose)
```

Chaque couche possède une responsabilité dédiée :

- **Controller** : gestion des requêtes HTTP
- **Service** : logique métier
- **Repository** : accès aux données
- **Model** : représentation des données métier

---

## 📂 Structure du projet

```text
JobTracker/

├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/cgorin/jobtracker/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── model/
│   │   │       └── exception/
│   │   │
│   │   └── resources/
│   │
│   └── test/
│       └── java/
│           └── Tests unitaires et tests d'intégration
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── qodana.yaml
└── README.md
```

---

## 🔧 Installation

### Prérequis

- Java 17
- Docker
- Docker Compose

---

## 🐳 Lancer avec Docker Compose

```bash
docker compose up --build
```

L'application sera disponible sur :

```text
http://localhost:8080
```

Documentation Swagger :

```text
http://localhost:8080/swagger-ui/index.html
```

---

## ▶️ Lancer localement avec Maven

### Linux / MacOS

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

---

## 🧪 Tests

Lancer les tests :

### Linux / MacOS

```bash
./mvnw test
```

### Windows

```bash
mvnw.cmd test
```

Le projet contient :

- tests unitaires des services
- tests des controllers
- tests d'intégration de l'API REST

---

## 🔄 Intégration continue

Le projet utilise **GitHub Actions** pour automatiser :

✅ l'exécution des tests  
✅ le build Maven  
✅ la création de l'image Docker

Une analyse statique du code est également réalisée avec **Qodana**.

---

## 📌 Roadmap

### Backend

✅ API REST Spring Boot  
✅ PostgreSQL  
✅ Docker Compose  
✅ Tests automatisés  
✅ Swagger/OpenAPI  
✅ CI GitHub Actions

### Améliorations futures

⬜ Pipeline CD  
⬜ Publication d'une image Docker  
⬜ Déploiement cloud  
⬜ Configuration production  
⬜ HTTPS  
⬜ Authentification utilisateur  
⬜ Interface frontend Angular  
⬜ Gestion avancée des candidatures

---

## 💡 Projet personnel

Ce projet me permet d'approfondir :

- le développement backend Java/Spring
- les bonnes pratiques d'architecture logicielle
- les tests automatisés
- la conteneurisation
- les outils DevOps

---

🖥️ Développé par : **Camille Gorin**

🏫 École 42 Nice
