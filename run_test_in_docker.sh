#!/bin/bash 
#

# set mvn command
. /etc/profile

cd /Code/szt-java

# API tests
mvn test -Dtest=RunCucumberTests

# unit tests
mvn test -Dtest=*Test
