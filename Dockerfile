FROM openjdk:11
MAINTAINER baeldung.com
COPY target/customerorder-0.0.1-SNAPSHOT.jar customerorder-1.0.0.jar
ENTRYPOINT ["java","-jar","/customerorder-1.0.0.jar"]
