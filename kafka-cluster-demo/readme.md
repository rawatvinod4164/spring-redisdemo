Kafka Cluster setup
Step 1.
Create multiple zookeeper.properties file 
cp config/zookeeper.properties config/zookeeper1.properties
cp config/zookeeper.properties config/zookeeper2.properties

For all zookeeprs
mkdir /zookeeper
echo '1' > /zookeeper/myid
echo '2' > /zookeeper/myid
echo '3' > /zookeeper/myid

Also add data dir in zookeeper.properties to property to this /zookeeper
Edit the port number inside it
clientPort=2181 //of zookeeper.propeties
clientPort=2182 //of zookeeper1.propeties
clientPort=2183/ /of zookeeper2.propeties
Also add in each .properties file add these config
tickTime=2000
initLimit=5
syncLimit=2
server.1=localhost:2666:3666
server.2=localhost:2667:3667
server.3=localhost:2668:3668


Step 2.
Create multiple server.properties file (number of brokers you want to have)
cp config/server.properties config/server1.properties
cp config/server.properties config/server2.properties

Edit each file with these:
broker.id=1
listeners=PLAINTEXT://:9093
log.dir=/tmp/kafka-logs-1
broker.id=2
listeners=PLAINTEXT://:9094
log.dir=/tmp/kafka-logs-2

Step 3. 
Start kafka server 
Kafka-server-start.sh /config/server.properties
Kafka-server-start.sh /config/server1.properties
Kafka-server-start.sh /config/server2.properties

Step 3 
Create kafka topicc
kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic MultipleBrokerTopic --partitions 6 --create --replication-factor 3

Step 4 
produce and consume
