#!/bin/bash

git pull
mvn clean
mvn package
mvn assembly:single
sudo docker-compose stop
export BOT_TOKEN=$1
sudo docker-compose up -d --build
