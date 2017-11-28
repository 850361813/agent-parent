@echo off
set TITLE=collect
set BATCH_HOME=E:\agent-batch
echo BATCH_HOME
echo %BATCH_HOME%
set LIB_ROOT=%BATCH_HOME%\lib
set CONFIG_ROOT=%BATCH_HOME%\config

SETLOCAL enabledelayedexpansion

set CLASSPATH=
for %%c in (%LIB_ROOT%\*.jar) do set CLASSPATH=!CLASSPATH!;%%c

echo CLASSPATH
echo %CLASSPATH%

set JAVA_OPTS="%JAVA_OPTS% -server -Xms1024m -Xmx1024m -Xmn512m -Xss256K -XX:MaxPermSize=128m -XX:ReservedCodeCacheSize=64m -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseG1GC"

java -cp %CLASSPATH% com.eden.agent.common.launcher.Launcher publish

@pause