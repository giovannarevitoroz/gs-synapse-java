# Etapa 1: Build da aplicação com Maven
FROM ubuntu:latest AS build

# Instala dependências
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Define diretório de trabalho
WORKDIR /app

# Copia tudo para o container
COPY . .

# Build da aplicação (sem testes)
RUN mvn clean install -DskipTests

# Etapa 2: Imagem leve para rodar o app
FROM eclipse-temurin:17-jre-jammy

# Define porta
EXPOSE 8080

# Copia o JAR gerado para a nova imagem
COPY --from=build /app/target/gs-synapse-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar o app
ENTRYPOINT ["java", "-jar", "app.jar"]