# Leo2000 : serveur Spring Boot. Image en deux etapes : compilation, puis execution seulement.
FROM eclipse-temurin:21-jdk AS compilation
WORKDIR /src
COPY . .
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew && ./gradlew bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=compilation /src/build/libs/*-SNAPSHOT.jar leo2000.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "leo2000.jar"]
