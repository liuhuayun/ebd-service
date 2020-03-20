#!/bin/bash 
# 
ssh root@112.74.167.163 "cd /root/Code/ebd-java; git pull; ./restart_docker_server.sh"
