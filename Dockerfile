FROM openjdk:latest
COPY target/transportation-service-0.0.1.jar transportation-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/transportation-service-0.0.1.jar"]