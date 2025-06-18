# FROM eclipse-temurin:21-jdk-alpine AS build

# Usando a imagem base padrão (Debian-based) para o build
# para evitar problemas com o plugin protoc-gen-grpc-java no Alpine
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn ./.mvn
COPY pom.xml .

COPY src ./src

RUN ls -l /app
RUN ls -l /app/mvnw

RUN chmod +x mvnw mvnw.cmd

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]