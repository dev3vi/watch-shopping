FROM openjdk:11.0.11-jre

VOLUME /tmp

ENV DATABASE_HOST=host.docker.internal
ENV DATABASE_PORT=5432
ENV DATABASE_NAME=test
ENV DATABASE_USERNAME=test
ENV DATABASE_PASSWORD=1

ADD target/spring-boot-docker-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT exec java -jar app.jar