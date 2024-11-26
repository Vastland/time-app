FROM openjdk:17-jdk-alpine
LABEL authors="oleksii"

WORKDIR /backend-postgres-kafka-producer

EXPOSE 5000

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

ENTRYPOINT ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=container"]