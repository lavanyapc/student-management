# ---------- Stage 1: Build the application with Maven ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom first so dependency layer is cached separately from source changes
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

# ---------- Stage 2: Run the application on a lightweight JRE ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy only the built jar from the build stage (keeps final image small)
COPY --from=build /app/target/student-management.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
