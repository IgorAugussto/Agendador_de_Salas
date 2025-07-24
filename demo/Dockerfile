#Pegando a versão do Maven
FROM maven:3.9.11-eclipse-temurin-21 AS build

#Trazer arquivo da nossa aplicação
#Dentro do container vai ter uma pasta app e dentro da pasta app vai ter uma pasta src com o projeto
COPY src /app/src
COPY pom.xml /app

#Mudando para o diretório /app
WORKDIR /app

#Agora dentro do diretório /app, fazer as intalações das dependencia com o Maven o build da aplicação Spring
RUN mvn clean install

#Coloando o jdk no nosso container
#Pegando imagem da Amazon
FROM openjdk:17-alpine

#Copiar arquivo .jar gerado acima para dentro de /app/jar
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

#Comando que irá executar a nossa aplicação
CMD ["java", "-jar", "app.jar"]