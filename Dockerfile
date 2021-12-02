FROM openjdk:11
EXPOSE 8080
VOLUME /var/lib/mysql
ARG JAR_FILE=target/employee-service.jar
COPY ${JAR_FILE} employee-service.jar
ENTRYPOINT [ "java", "-jar", "/employee-service.jar" ]