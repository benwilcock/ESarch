#!/usr/bin/env bash

docker-compose down

cd discovery-server
mvn clean compile jib:dockerBuild
cd ..

cd trading-engine
mvn clean compile jib:dockerBuild
cd ..

cd trader-app
mvn clean compile jib:dockerBuild
cd ..

cd trader-app-ui
docker build -t benwilcock/trader-app-ui .
cd ..

docker-compose up -d