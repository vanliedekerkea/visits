FROM eclipse-temurin:17.0.4_8-jdk
COPY ./target/visits-0.0.1-SNAPSHOT.jar visits-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","visits-0.0.1-SNAPSHOT.jar"]
