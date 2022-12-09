#!/bin/bash

git pull
mvn clean
mvn package
mvn assembly:single
sudo docker-compose stop
rm .env
echo "BOT_TOKEN="$1 > .env
sudo docker-compose --env-file .env up --build -d
