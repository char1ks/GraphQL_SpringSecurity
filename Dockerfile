FROM openjdk:11
ARG JAR_FILE=target/GraphQL_with_SpringSecurity-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]