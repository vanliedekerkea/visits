#! /bin/bash

mvn clean install -DskipTests
docker image build -t visits .
docker container stop visits-container
docker container rm visits-container
docker container run --network visits-postgresql --name visits-container -p 8080:8080 -d visits