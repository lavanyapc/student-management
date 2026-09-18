# Student Management System — CI/CD Demo

A small Spring Boot web application with two core operations — **add student** and
**view students** — used to demonstrate an end-to-end CI/CD pipeline with
Git, Maven, Docker, and Jenkins.

## Run locally with Maven
```bash
mvn clean package
java -jar target/student-management.jar
```
Open http://localhost:8080 in a browser.

## API
| Method | Endpoint             | Description          |
|--------|-----------------------|-----------------------|
| POST   | /api/students          | Add a student         |
| GET    | /api/students          | View all students     |
| GET    | /api/students/{id}     | View one student      |
| GET    | /api/students/health   | Health check          |

## Run with Docker
```bash
docker build -t student-management .
docker run -d --name student-management-app -p 8080:8080 student-management
```

## CI/CD
See `Jenkinsfile` for the pipeline definition, and the accompanying
`CI-CD-Setup-Guide.md` for full Mac setup instructions.
