FROM openjdk:17-oracle
EXPOSE 8081
ARG JAR_FILE=target/facade-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]