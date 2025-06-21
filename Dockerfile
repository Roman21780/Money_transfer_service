FROM openjdk:24-ea-2-jdk-slim
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 5500
ENTRYPOINT ["java", "-jar", "app.jar"]