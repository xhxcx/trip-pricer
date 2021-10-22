FROM openjdk:8-jdk-alpine
COPY build/libs/*.jar tripPricer-1.0.0.jar
ENTRYPOINT ["java","-jar","/tripPricer-1.0.0.jar"]