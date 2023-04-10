FROM openjdk:18-jdk-alpine3.13

EXPOSE 5050

ADD target/DiplomWork-0.0.1-SNAPSHOT.jar diplom.jar

ENTRYPOINT ["java", "-jar", "diplom.jar"]