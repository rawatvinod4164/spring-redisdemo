# **Redis-Jedis Spring-Boot Configuration**

Requirements : 
1. Java8
2. Redis

How to Run:
1. mvn clean install
2. Run the springBootApplication  
redis-spring/src/main/java/com/redis/test/redisspring/RedisSpringApplication.java
3. Run the redis server
Go to redis installtaion library then run 
**./src/redis-server**
4. Use Below postman for the adding and getting the values
**POST**
curl --location --request POST '127.0.0.1:8080/programmer-string/' \
--header 'Content-Type: application/json' \
--data-raw '{
	"id":110,
	"name":"hello",
	"compnay":"testcompany"
}'
**GET**
curl --location --request GET '127.0.0.1:8080/programmer-string/110' \


P.S.Do Check the port by default it is 6379