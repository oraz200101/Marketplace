FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/marketPlace-0.0.1-SNAPSHOT.jar /marketPlace-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","marketPlace-0.0.1-SNAPSHOT.jar"]