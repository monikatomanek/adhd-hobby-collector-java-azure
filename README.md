# ADHD Hobby Collector

A Java Spring Boot REST API for tracking hobbies, projects, supplies, costs, and project status.

The project models how someone might explore many hobbies, buy supplies, start projects, finish some, abandon others, and archive removed projects.

## Current status

The backend API is working locally with a MySQL database.

## Tools used

- Java
- Spring Boot
- Maven
- MySQL
- GitHub
- Visual Studio Code

## Database

The database stores:

- hobbies
- projects
- supplies
- archived/deleted projects

The SQL setup script is in:

```text
database/setup.sql
```

## Java app

The Java Spring Boot app is in:

```text
java-app
```

## API endpoints

### Basic

```text
GET /
```

Returns a simple message to confirm the API is running.

### Hobbies

```text
GET /hobbies
```

Returns all hobbies from MySQL.

### Supplies

```text
GET /supplies
```

Returns all supplies from MySQL.

### Projects

```text
GET /projects
```

Returns all current projects from MySQL.

```text
GET /projects/archived
```

Returns archived projects from the `DeletedProjects` table.

```text
POST /projects
```

Adds a new project.

Example JSON:

```json
{
  "hobbyId": 3,
  "projectName": "Budget Tracker App",
  "status": "Started",
  "notes": "Small Java API test project.",
  "startedDate": "2026-05-16"
}
```

```text
PUT /projects/{id}/status
```

Updates a project status.

Example JSON:

```json
{
  "status": "Finished"
}
```

```text
DELETE /projects/{id}
```

Archives a project into `DeletedProjects`, then removes it from `Projects`.

## Summary endpoints

```text
GET /summary
```

Returns basic API information and record counts.

```text
GET /summary/projects-by-status
```

Returns project counts by status.

```text
GET /summary/spending
```

Returns total spending, essential spending, non-essential spending, average supply cost, and most expensive supply.

```text
GET /summary/spending-by-hobby
```

Returns total supply spending grouped by hobby.

## Local database config

The safe template file is:

```text
java-app/src/main/resources/application.properties
```

The real local password should be stored in:

```text
java-app/src/main/resources/application-local.properties
```

This file is ignored by Git and should not be pushed to GitHub.

## Run locally

Open the terminal in:

```text
C:\Users\lemon\Documents\Code\my_projects\adhd_hobby_collector\java-app
```

Run:

```cmd
.\mvnw spring-boot:run "-Dspring-boot.run.profiles=local"
```

Then open:

```text
http://localhost:8080
```

## Test commands

Add a project:

```cmd
curl -X POST http://localhost:8080/projects -H "Content-Type: application/json" -d "{\"hobbyId\":3,\"projectName\":\"Budget Tracker App\",\"status\":\"Started\",\"notes\":\"Small Java API test project.\",\"startedDate\":\"2026-05-16\"}"
```

Update project status:

```cmd
curl -X PUT http://localhost:8080/projects/10/status -H "Content-Type: application/json" -d "{\"status\":\"Finished\"}"
```

Archive/delete project:

```cmd
curl -X DELETE http://localhost:8080/projects/10
```

## Next planned steps

- Deploy the database to Azure
- Deploy the Spring Boot app to Azure App Service
- Optionally add a simple frontend later