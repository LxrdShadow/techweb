#!/bin/sh

./mvnw spring-boot:run &
APP_PID=$!

find src/main -type f | entr -r ./mvnw compile

kill $APP_PID
