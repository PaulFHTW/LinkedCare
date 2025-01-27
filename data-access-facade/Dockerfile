FROM openjdk:17-oracle
WORKDIR /app
EXPOSE 8081
COPY data-access-facade/target/facade-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]