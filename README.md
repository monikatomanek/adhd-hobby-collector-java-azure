# ADHD Hobby Collector

A Java Spring Boot REST API for tracking hobbies, projects, supplies, costs, and project status.

The project models how someone might explore many hobbies, buy supplies, start projects, finish some, abandon others, and archive removed projects.

## Current status

The backend API is deployed online with Render and connected to an Azure SQL Database.

The app also works locally with MySQL.

## Live API

```text
https://adhd-hobby-api.onrender.com
```

Useful live endpoints:

```text
https://adhd-hobby-api.onrender.com/
https://adhd-hobby-api.onrender.com/hobbies
https://adhd-hobby-api.onrender.com/supplies
https://adhd-hobby-api.onrender.com/projects
https://adhd-hobby-api.onrender.com/projects/archived
https://adhd-hobby-api.onrender.com/summary
https://adhd-hobby-api.onrender.com/summary/projects-by-status
```

## Tools used

- Java
- Spring Boot
- Maven
- MySQL
- Azure SQL Database
- Render
- Docker
- GitHub
- Visual Studio Code

## Database

The database stores:

- hobbies
- projects
- supplies
- archived/deleted projects

Local MySQL setup script:

```text
database/setup.sql
```

Azure SQL setup script:

```text
database/setup-azure-sql.sql
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

Returns all hobbies.

### Supplies

```text
GET /supplies
```

Returns all supplies.

### Projects

```text
GET /projects
```

Returns all current projects.

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

## Local MySQL config

Safe template file:

```text
java-app/src/main/resources/application.properties
```

Real local MySQL password file:

```text
java-app/src/main/resources/application-local.properties
```

This file is ignored by Git and should not be pushed to GitHub.

Run locally with MySQL from inside `java-app`:

```cmd
.\mvnw spring-boot:run "-Dspring-boot.run.profiles=local"
```

Then open:

```text
http://localhost:8080
```

## Azure SQL config

Real Azure SQL password file:

```text
java-app/src/main/resources/application-azure.properties
```

This file is ignored by Git and should not be pushed to GitHub.

Run locally with Azure SQL from inside `java-app`:

```cmd
.\mvnw spring-boot:run "-Dspring-boot.run.profiles=azure"
```

Then open:

```text
http://localhost:8080/projects
```

## Render deployment

Render uses the root `Dockerfile`.

Render environment variables:

```text
SPRING_PROFILES_ACTIVE=render
SPRING_DATASOURCE_URL=jdbc:sqlserver://adhd-hobby-sql-server.database.windows.net:1433;database=free-sql-db-8167972;encrypt=true;trustServerCertificate=true;loginTimeout=60;
SPRING_DATASOURCE_USERNAME=adhdadmin
SPRING_DATASOURCE_PASSWORD=your Azure SQL password
SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

The real password is stored only in Render environment variables, not in GitHub.

## Test commands

Add a project:

```cmd
curl -X POST https://adhd-hobby-api.onrender.com/projects -H "Content-Type: application/json" -d "{\"hobbyId\":3,\"projectName\":\"Budget Tracker App\",\"status\":\"Started\",\"notes\":\"Small Java API test project.\",\"startedDate\":\"2026-05-16\"}"
```

Update project status:

```cmd
curl -X PUT https://adhd-hobby-api.onrender.com/projects/10/status -H "Content-Type: application/json" -d "{\"status\":\"Finished\"}"
```

Archive/delete project:

```cmd
curl -X DELETE https://adhd-hobby-api.onrender.com/projects/10
```

## Notes

Azure App Service deployment was attempted but blocked because the subscription had VM quota set to `0`.

The app was deployed successfully on Render instead.

## Next possible steps

- Add a simple frontend
- Add motivation level later
- Improve validation for project creation and status updates
