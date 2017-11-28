#!/usr/bin/env bash

# Launcher
BATCH_HOME=$(cd $(dirname $0);cd ..;pwd)

# BatchLauncher/lib
LIB_ROOT=$BATCH_HOME/lib
echo $BATCH_HOME
# BatchLauncher/conf
CONFIG_ROOT=$BATCH_HOME/config

CLASSPATH=$CLASSPATH:$CONFIG_ROOT:$LIB_ROOT/*

export JAVA_OPTS="$JAVA_OPTS -server -Xms1024m -Xmx1024m -Xmn512m -Xss256K -XX:MaxPermSize=128m
-XX:ReservedCodeCacheSize=64m -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:../logs/gc_schedule.log"
export JAVA_OPTS="$JAVA_OPTS -XX:+UseG1GC"

application_launcher='com.eden.agent.common.launcher.Launcher'

start java $JAVA_OPTS -cp $CLASSPATH $application_launcher publish