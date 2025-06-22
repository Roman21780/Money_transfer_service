# Этап 1: Сборка проекта
FROM eclipse-temurin:21-jdk AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем только необходимые файлы для сборки
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Собираем проект
RUN ./gradlew clean build --no-daemon && \
    rm -rf ~/.gradle # Очищаем кэш Gradle

# Этап 2: Финальный образ
FROM eclipse-temurin:21-jre

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем только собранный JAR из первого этапа
COPY --from=build /app/build/libs/*.jar app.jar

# Открываем порт для приложения
EXPOSE 5500

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]