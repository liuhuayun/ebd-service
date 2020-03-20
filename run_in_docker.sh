#!/bin/bash 
#
active=$1
[ ! ${active} ] && active='aliyun'
echo ${active}

# kill java task
kill -9 $(ps aux | grep -v 'grep' | grep 'ebd.jar' | awk '{print $2}')

# start redis
# /etc/init.d/redis-server restart

# set evironment variables
. /etc/profile

cd /Code/ebd-java
# mvn compile flyway:migrate
mvn clean package -DskipTests

cd /Code/ebd-java/target
java -jar -Dspring.profiles.active=${active} ebd.jar > ebd.log &

