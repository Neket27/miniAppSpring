# Используем официальный образ OpenJDK
FROM 1stblue/openjdk-17

LABEL authors="neket"

# Добавление JAR-файла в контейнер
ADD target/miniappspring-0.0.1-SNAPSHOT.jar /app/backend.jar

# Установка рабочего каталога
# Используем образ Node.js в качестве базового
FROM node:16
# Устанавливаем рабочую директорию
WORKDIR /app
# Копируем файлы package.json и package-lock.json
COPY package*.json ./
# Устанавливаем зависимости
RUN npm install
# Копируем все файлы проекта
COPY . .
# Открываем порт, используемый Vite
EXPOSE 5173
# Команда для запуска  сервера
CMD ["npx", "vite"]

# Определение точки входа
ENTRYPOINT ["java", "-jar", "backend.jar"]
