FROM eclipse-temurin:17-jdk-alpine
LABEL name="notification-service"
LABEL version="1.0.0"
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]