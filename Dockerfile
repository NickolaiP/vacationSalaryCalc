# Используем официальный образ Maven для сборки приложения
FROM maven:3.8.5-openjdk-17 AS build

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем pom.xml и загружаем зависимости
COPY pom.xml ./
RUN mvn dependency:go-offline

# Копируем весь исходный код в контейнер
COPY src ./src

# Собираем Spring Boot приложение
RUN mvn clean package -DskipTests

# Используем официальный образ OpenJDK для запуска приложения
FROM openjdk:17-jdk-slim

# Указываем рабочую директорию для финального образа
WORKDIR /app

# Копируем JAR файл из предыдущего этапа сборки
COPY --from=build /app/target/vacation-calculator-0.0.1-SNAPSHOT.jar ./vacation-calculator.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "vacation-calculator.jar"]

# Указываем, на каком порту приложение будет работать
EXPOSE 8080
