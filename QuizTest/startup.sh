#!/bin/bash
#nohup java -jar build/QuizTest-0.0.1-SNAPSHOT.jar > log.txt 2>&1 &
nohup ./mvnw spring-boot:run > log.txt 2>&1 &
echo $! > ./pid.file