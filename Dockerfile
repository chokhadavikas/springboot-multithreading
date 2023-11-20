FROM openjdk:17-jdk-slim

VOLUME /tmp

WORKDIR /apps

COPY target/springboot-multithreading-*-SNAPSHOT.jar /apps/springboot-multithreading.jar

EXPOSE 8080

CMD ["java", "-jar", "springboot-multithreading.jar"]