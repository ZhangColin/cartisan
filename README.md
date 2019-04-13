# cartisan



## 开发环境

### RabbitMQ 

```bash
docker pull rabbitmq:3.7.13-management
docker run -d -p 5672:5672 -p 15672:15672 -v ~/docker/rabbitmq:/var/rabbitmq/lib --hostname cartisan-rabbitmq -e RABBITMQ_DEFAULT_USER=rabbitmqadmin -e RABBITMQ_DEFAULT_PASS=123456 --name cartisan-rabbitmq rabbitmq:3.7.13-management 
```

### redis 

```bash
docker pull redis
docker run -d -p 6379:6379 -v ~/docker/redis:/data --restart=always --name cartisan-redis redis redis-server --appendonly yes --requirepass "123456"
```

### mongodb 

```bash
docker pull mongo
docker run -d -p 27017:27017 -v ~/docker/mongodb:/data/db -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=123456 --name cartisan-mongodb mongo
```

### elasticsearch 

```bash
docker pull elasticsearch:6.7.0
docker run -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" --name cartisan-elasticsearch elasticsearch:6.7.0
docker pull kibana:6.7.0
docker run -d -p 5601:5601 -v ~/docker/kibana/kibana.yml:/usr/share/kibana/config/kibana.yml --name cartisan-kibana kibana:6.7.0
```

## 端口规划

### Web 应用

| 名称 | 端口 |
| --- | --- |
| management-ui | 8088 |

### 服务

| 名称 | 端口 |
| --- | --- |
| management | 8081 |
| permission | 8082 |
| base | 9001 |
| goods | 9002 |

### 基础框架

| 名称 | 端口 |
| --- | --- |
| eureka | 8761 |
| gateway | 8889 |
| config | 8888 |
| hystrix-dashboard | 8887 |
| zipkin | 9411 |
