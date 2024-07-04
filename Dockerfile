FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8888/tcp
##docker build -t attin/spring-boot-hello .
##docker run -d -p 8888:8888  attin/spring-boot-hello
