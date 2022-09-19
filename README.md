# The project uses Spring Framework and PostgreSql

[![Build Status](https://travis-ci.com/xxxFilosoFxxx/Spring_PSql.svg?branch=master)](https://travis-ci.com/xxxFilosoFxxx/Spring_PSql)

## The project is built using Gradle version 6.3

- **`Below is the logical and physical layout of a PostgreSQL database:`**

![Image alt](Физическая%20и%20логическая%20схема%20бд.png)

- **Attention:** The script for creating tables lies [this](https://github.com/xxxFilosoFxxx/Spring_PSql/tree/master/bd_for_spring), entitled **create_db_for_spring.sql**

## For Linux

- `git clone https://github.comxxxFilosoFxxx/Spring_PSql.git`
- `chmod +x gradlew`
- `./gradlew build` 

## Using

**After creating the tables, open pgAdmin and connect to the server on port 5432**

- Now you can run `./gradlew run`
- The web application is available at: `https://localhost:8081`

