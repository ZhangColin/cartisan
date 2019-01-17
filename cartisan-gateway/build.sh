#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true

docker build -t hub.c.163.com/zhangcolin/cartisan-gateway .

docker push hub.c.163.com/zhangcolin/cartisan-gateway