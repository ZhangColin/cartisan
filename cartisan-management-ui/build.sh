#!/usr/bin/env bash
npm run build

docker build -t hub.c.163.com/zhangcolin/cartisan-management-ui .

docker push hub.c.163.com/zhangcolin/cartisan-management-ui
