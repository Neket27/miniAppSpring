# Используем образ OpenJDK
FROM 1stblue/openjdk-17
LABEL authors="neket"
# Добавление JAR-файла в контейнер
ADD backend/target/backendApp-0.0.1-SNAPSHOT.jar /app/backend.jar
# Установка рабочего каталога
WORKDIR /app
# Определение точки входа
ENTRYPOINT ["java", "-jar", "backend.jar"]
