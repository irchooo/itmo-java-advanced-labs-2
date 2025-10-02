FROM mvkvl/maven:jdk-21-alpine AS build
WORKDIR /app

COPY spring-core/pom.xml .
COPY spring-core/src ./src

RUN mvn clean install -DskipTests

FROM alpine/java:21-jdk
WORKDIR /app

COPY --from=build /app/target/javaadvanced-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "javaadvanced-0.0.1-SNAPSHOT.jar"]