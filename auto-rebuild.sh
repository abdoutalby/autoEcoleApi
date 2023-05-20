#!/bin/bash

# Set the path to your code directory
CODE_DIR="./"

# Set the path to your Docker Compose file
DOCKER_COMPOSE_FILE="./docker-compose.yml"

# Watch for changes in the code directory and trigger Docker Compose rebuild
while inotifywait -r -e modify,create,delete $CODE_DIR; do
   sudo  docker-compose -f $DOCKER_COMPOSE_FILE up --build -d
done
