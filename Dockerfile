FROM ubuntu:latestaaaaaaa AS buildaaaa

RUN apt-get updateaaaaaaaaaa
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim
EXPOSE 8080sdadsa

COPY --from=build /target/gestao_vagas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
