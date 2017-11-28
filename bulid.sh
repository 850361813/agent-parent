#!/usr/bin/env bash

mvn clean package

homeDir=/Users/baidu/

cp agent-batch/target/agent-batch-1.0.zip $homeDir

cd $homeDir
unzip agent-batch-1.0.zip
rm -rf agent-batch-1.0.zip