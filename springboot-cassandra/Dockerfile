FROM openjdk:17-alpine
EXPOSE 7171
#ARG JAR_FILE
#ADD target/${JAR_FILE} app.jar
ADD target/springboot-cassandra.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]