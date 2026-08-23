# Gestion des salles de classe — Backend

Backend REST Spring Boot pour gérer les professeurs, les salles de classe et leurs occupations. Les données sont persistées dans PostgreSQL avec Spring Data JPA.

## Modèle de données

| Entité | Champs | Description |
| --- | --- | --- |
| `Prof` | `codeprof`, `nom`, `prenom`, `grade` | Un professeur. `codeprof` est généré par la base de données. |
| `Salle` | `codesal`, `designation` | Une salle de classe. `codesal` est généré par la base de données. |
| `Occuper` | `codeprof`, `codesal`, `date` | L'occupation d'une salle par un professeur à une date donnée. Ces trois champs forment la clé primaire composée. |

`Occuper` référence un professeur et une salle existants. Une demande qui utilise un identifiant inexistant retourne `404 Not Found`.

## Prérequis

- Java 25
- PostgreSQL
- Aucun Maven global n'est nécessaire : le projet inclut Maven Wrapper (`mvnw`).

## Configuration de la base de données

Créez une base PostgreSQL, par exemple :

```sql
CREATE DATABASE techweb;
```

Définissez ensuite les variables d'environnement utilisées par l'application :

```sh
export DB_URL=jdbc:postgresql://localhost:5432/techweb
export DB_USERNAME=postgres
export DB_PASSWORD=mot_de_passe
```

Les valeurs d'exemple sont disponibles dans `src/main/resources/application-dev.properties.example`. Au démarrage, Hibernate crée ou met à jour les tables grâce à `spring.jpa.hibernate.ddl-auto=update`.

## Démarrage

```sh
./mvnw spring-boot:run
```

L'API est disponible sur `http://localhost:8080`.

Vérifier la compilation sans exécuter les tests :

```sh
./mvnw -DskipTests compile
```

## API REST

Tous les corps de requête sont au format JSON et doivent inclure l'en-tête `Content-Type: application/json`.

### Professeurs

| Méthode | URL | Description |
| --- | --- | --- |
| `GET` | `/api/profs` | Liste tous les professeurs. |
| `GET` | `/api/profs/{codeprof}` | Recherche un professeur par code. |
| `GET` | `/api/profs/search?nom={nom}` | Recherche les professeurs dont le nom contient `nom`, sans tenir compte de la casse. |
| `POST` | `/api/profs` | Crée un professeur. |
| `PUT` | `/api/profs/{codeprof}` | Met à jour un professeur. |
| `DELETE` | `/api/profs/{codeprof}` | Supprime un professeur. |

Créer un professeur — ne pas envoyer `codeprof`, il est généré par PostgreSQL :

```json
{
  "nom": "Rakoto",
  "prenom": "Jean",
  "grade": "Maître de conférences"
}
```

Exemple de recherche :

```text
GET /api/profs/search?nom=rak
```

### Salles

| Méthode | URL | Description |
| --- | --- | --- |
| `GET` | `/api/salles` | Liste toutes les salles. |
| `GET` | `/api/salles/{codesal}` | Recherche une salle par code. |
| `POST` | `/api/salles` | Crée une salle. |
| `PUT` | `/api/salles/{codesal}` | Met à jour une salle. |
| `DELETE` | `/api/salles/{codesal}` | Supprime une salle. |

Créer une salle :

```json
{
  "designation": "Salle A101"
}
```

### Occupations

| Méthode | URL | Description |
| --- | --- | --- |
| `GET` | `/api/occupations` | Liste toutes les occupations. |
| `GET` | `/api/occupations/{codeprof}/{codesal}/{date}` | Recherche une occupation. |
| `POST` | `/api/occupations` | Crée une occupation. |
| `PUT` | `/api/occupations/{codeprof}/{codesal}/{date}` | Remplace une occupation existante. |
| `DELETE` | `/api/occupations/{codeprof}/{codesal}/{date}` | Supprime une occupation. |

Le format de date est ISO 8601 : `YYYY-MM-DD`.

Créer une occupation :

```json
{
  "codeprof": 1,
  "codesal": 2,
  "date": "2026-08-23"
}
```

Exemple d'URL pour lire ou supprimer cette occupation :

```text
/api/occupations/1/2/2026-08-23
```

Pour modifier une occupation, utilisez l'URL qui identifie l'ancienne occupation et envoyez le nouveau corps JSON. L'opération est transactionnelle : une demande invalide ne supprime pas l'occupation existante.

## Exemples avec curl

```sh
curl -X POST http://localhost:8080/api/profs \
  -H 'Content-Type: application/json' \
  -d '{"nom":"Rakoto","prenom":"Jean","grade":"Maître de conférences"}'

curl -X POST http://localhost:8080/api/salles \
  -H 'Content-Type: application/json' \
  -d '{"designation":"Salle A101"}'

curl -X POST http://localhost:8080/api/occupations \
  -H 'Content-Type: application/json' \
  -d '{"codeprof":1,"codesal":1,"date":"2026-08-23"}'
```
