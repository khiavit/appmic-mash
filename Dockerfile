FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY APPMIC-AccessVisitMash-boot-0.0.1-SNAPSHOT.jar APPMIC-AccessVisitMash-0.0.1-KO.jar
ENTRYPOINT ["java","-jar","/APPMIC-AccessVisitMash-0.0.1-KO.jar"]