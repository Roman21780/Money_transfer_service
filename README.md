Структура папок
Серийный номер тома: 382F-88E9
C:.
├───.gradle
│   ├───8.14.2
│   │   ├───checksums
│   │   ├───executionHistory
│   │   ├───expanded
│   │   ├───fileChanges
│   │   ├───fileHashes
│   │   └───vcsMetadata
│   ├───buildOutputCleanup
│   └───vcs-1
├───.idea
│   ├───httpRequests
│   └───modules
├───build
│   ├───classes
│   │   └───java
│   │       ├───main
│   │       │   └───ru
│   │       │       └───netology
│   │       │           └───money_transfer_service
│   │       │               ├───config
│   │       │               ├───controller
│   │       │               ├───dto
│   │       │               ├───exception
│   │       │               ├───model
│   │       │               ├───repository
│   │       │               └───service
│   │       └───test
│   │           └───ru
│   │               └───netology
│   │                   └───money_transfer_service
│   ├───generated
│   │   └───sources
│   │       ├───annotationProcessor
│   │       │   └───java
│   │       │       ├───main
│   │       │       └───test
│   │       └───headers
│   │           └───java
│   │               ├───main
│   │               └───test
│   ├───libs
│   ├───reports
│   │   └───tests
│   │       └───test
│   │           ├───classes
│   │           ├───css
│   │           ├───js
│   │           └───packages
│   ├───resources
│   │   └───main
│   │       ├───static
│   │       └───templates
│   ├───test-results
│   │   └───test
│   │       └───binary
│   └───tmp
│       ├───bootJar
│       ├───compileJava
│       ├───compileTestJava
│       ├───jar
│       └───test
├───front
├───gradle
│   └───wrapper
├───logs
└───src
├───main
│   ├───java
│   │   └───ru
│   │       └───netology
│   │           └───money_transfer_service
│   │               ├───config
│   │               ├───controller
│   │               ├───dto
│   │               ├───exception
│   │               ├───model
│   │               ├───repository
│   │               └───service
│   └───resources
│       ├───static
│       └───templates
└───test
└───java
└───ru
└───netology
└───money_transfer_service



Запуск проекта
1. Dockerfile
   dockerfile
   FROM openjdk:17-alpine
   WORKDIR /app
   COPY build/libs/*.jar app.jar
   EXPOSE 5500
   ENTRYPOINT ["java", "-jar", "app.jar"]
2. docker-compose.yml
   yaml
   version: '3.8'
   services:
   backend:
   build: .
   ports:
    - "5500:5500"
      volumes:
    - ./logs:/app/logs # Маппинг логов
      frontend:
      image: node:14
      working_dir: /app
      volumes:
    - ./front:/app # Папка с FRONT (из репозитория FRONT)
      ports:
    - "3000:3000"
      command: "npm start"
3. Команды для запуска
   bash
# Сборка проекта
./gradlew build

# Создание Docker-образа
docker build -t money-transfer .

# Запуск через Docker Compose
docker-compose up


# Примеры запросов (cURL)

## Перевод:

bash

curl -X POST http://localhost:5500/transfer \
-H "Content-Type: application/json" \
-d '{
"cardFromNumber": "1234567890123456",
"cardFromValidTill": "12/25",
"cardFromCVV": "123",
"cardToNumber": "1111222233334444",
"amount": {
"value": 10000,
"currency": "RUB"
}
}'

Ответ:

json
{"operationId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"}

Подтверждение:

bash

curl -X POST http://localhost:5500/confirmOperation \
-H "Content-Type: application/json" \
-d '{
"operationId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"code": "0000"
}'

Ответ:

json
{"operationId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"}

# Интеграция с FRONT
Скачать FRONT:

bash
git clone https://github.com/serp-ya/card-transfer.git front
Запустить через Docker Compose (как выше)

FRONT будет доступен на: http://localhost:3000

Бэкенд на: http://localhost:5500

# Логирование операций
Формат записи в transfers.log:

text
[2025-06-16 14:30:45] From: 1234567890123456 To: 1111222233334444 Amount: 100.00 RUB Commission: 0.00 Result: SUCCESS
Каждая строка содержит:

Дата и время

Карта отправителя

Карта получателя

Сумма и валюта

Комиссия

Результат операции




# Тестирование
Юнит-тесты:

Mockito для сервисов и репозиториев

Тесты валидации, логики перевода, подтверждения

Интеграционные тесты:

Testcontainers для поднятия БД

SpringBootTest с полным контекстом

Тесты REST-эндпоинтов

Пример теста:

java
@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerTest {
@Autowired private MockMvc mockMvc;

    @Test
    void transferSuccess() throws Exception {
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"cardFromNumber\":\"1234567890123456\",...}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operationId").exists());
    }
}

# Важные замечания
Хранение данных:

Карты: JSON-файл (cards.json) при старте

Операции: In-Memory хранилище (ConcurrentHashMap)

Для продакшена заменить на БД (PostgreSQL)

Безопасность:

В реальном проекте добавить HTTPS

Шифрование чувствительных данных

Реальные коды подтверждения (SMS/email)

Комиссии:

В текущей реализации 0%

Логика комиссий добавляется в TransferService

Масштабирование:

Добавить Spring Cloud Gateway для роутинга

Балансировщик нагрузки (Nginx)

Кластеризация через Kubernetes

Проект соответствует всем требованиям: REST API, логирование, Docker-контейнеризация, интеграция с FRONT.


# Анализ

Как получить логи из контейнера:

bash
docker cp <container_id>:/app/logs/application.log ./local/path/


1. Запуск проекта:
   Соберите проект:

bash
./gradlew clean build
Запустите через Docker:

bash
docker-compose down && docker-compose up --build

2. Проверка работы:
   Бэкенд будет доступен на http://localhost:5500

Фронтенд на http://localhost:3000

Логи будут сохраняться в папке logs/ в вашем проекте

3. Альтернативный вариант (без Docker):

В одном терминале (бэкенд):

bash
./gradlew bootRun

В другом терминале (фронтенд):

bash
cd C:\Java\Project1\WEB\card-transfer
npm start

4. Проверка работы
   Фронтенд будет доступен по: http://localhost:3000

Бэкенд будет доступен по: http://localhost:5500

Для проверки API:

bash
curl -X POST http://localhost:5500/transfer -H "Content-Type: application/json" -d 
'{"cardFromNumber":"1234567890123456","cardToNumber":"1111222233334444",
"amount":{"value":1000}}'

5. Логирование
Логи будут доступны:

Бэкенд: в папке ./logs вашего проекта

Фронтенд: в терминале или через docker logs money_transfer_service-frontend-1
