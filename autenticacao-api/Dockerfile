# Use a imagem Maven como base
FROM openjdk:17-jdk-alpine AS build

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo pom.xml e o Maven Wrapper para o diretório de trabalho
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn ./.mvn
COPY pom.xml .

# Copie o código-fonte da aplicação para o diretório de trabalho
COPY src ./src

# Dê permissões de execução ao Maven Wrapper
RUN chmod +x mvnw mvnw.cmd

# Compile a aplicação Spring Boot
RUN ./mvnw package -DskipTests

# Estágio de execução
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o artefato construído do estágio de build para o diretório de trabalho
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta 8080 para o acesso externo
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot
CMD ["java", "-jar", "app.jar"]
