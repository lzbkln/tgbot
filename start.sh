#!/bin/bash
git pull
mvn clean
mvn package
mvn assembly:single
docker-compose stop
export BOT_TOKEN=$1
docker-compose up --build