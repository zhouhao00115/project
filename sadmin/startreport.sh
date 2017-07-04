#!/bin/sh
APP_HOME="/data/services/report"
CLASSPATH=$APP_HOME/bin

export PATH="/usr/java/jdk1.7.0_04/bin"
export LC_ALL=zh_CN.GBK
MAIN_FUNCTION="com.zhaopin.core.task.ReportTask"
echo $1
if [ -n "$1" ]
then
        MAIN_FUNCTION=$1
fi

CLASSPATH="$CLASSPATH":"$APP_HOME"/classes/
for i in "$APP_HOME"/bin/*.jar
do
 CLASSPATH="$CLASSPATH":"$i"
done
export CLASSPATH=.:$CLASSPATH
echo ${CLASSPATH}
echo $MAIN_FUNCTION
java -Xms128m -Xmx256m `echo $MAIN_FUNCTION`
exit
