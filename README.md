# Проект использует Spring Framework и PostgreSql

[![Build Status](https://travis-ci.com/xxxFilosoFxxx/Spring_PSql.svg?branch=master)](https://travis-ci.com/xxxFilosoFxxx/Spring_PSql)

## Проект собран с помощью Gradle версии 6.3

- **`Ниже представлена логическая и физическая схема базы данных PostgreSQL:`**

![Image alt](Физическая%20и%20логическая%20схема%20бд.png)

- **Внимание:** Скрипт создания таблиц лежит [здесь](https://github.com/xxxFilosoFxxx/Spring_PSql/tree/master/bd_for_spring), под названием **create_db_for_spring.sql**

## Установка для Linux

- `git clone https://github.comxxxFilosoFxxx/Spring_PSql.git`
- `chmod +x gradlew`
- `./gradlew build` 

## Использование 

**После создания таблиц, откройте pgAdmin и подключитесь к серверу по порту 5432**

- Теперь вы можете запустить `./gradlew run`
- Вэб-приложение доступно по адресу: `https://localhost:8081`

