FROM openjdk:17-jdk-alpine
LABEL authors="oleksii"

WORKDIR /backend-mongodb-kafka-receiver

EXPOSE 9000

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

ENV SPRING_PROFILES_ACTIVE=container
ENTRYPOINT ["./mvnw", "spring-boot:run"]