FROM openjdk:17-jdk-alpine
COPY /target/parquimetro-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","parquimetro-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080