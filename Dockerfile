FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE}
ENTRYPOINT ["java", "-jar", "/app.jar"]
CMD gunicorn --bind 0.0.0.0:$PORT wsgi