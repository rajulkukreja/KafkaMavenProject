Kafka Startup :- 


For Basic local setup :- 

1) Download Apache kafka from http://kafka.apache.org/
2) Unzip it at a location

3) Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

4) Start Broker
bin/kafka-server-start.sh config/server.properties

5) Topic Creation
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

6) List Topic
bin/kafka-topics.sh --list --zookeeper localhost:2181

7) Start Console Producer
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test 

8) Start Console Consumer
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning



========================================================================


For Running above Project :- 

How To Compile
mvn clean package


How To Run

1) Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

2) Start Broker
bin/kafka-server-start.sh config/server.properties

3) Topic Creation
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

4) Java producer example
java -cp ./target/KafkaMavenExample-0.0.1-SNAPSHOT.jar com.kafka.examples.SampleProducer

5) Java consumer example
java -cp ./target/KafkaMavenExample-0.0.1-SNAPSHOT.jar com.kafka.examples.SampleConsumer
