KAFKA_LISTENERS: The container will listen on
    kafka1:29091 => Internal Docker
    0.0.0.0:9091 => Can connect from Host machine port
    127.0.0.1:9091 => This container port (Only work in case call direct call like kafka:9091)
When connect to port localhost:9091
    KAFKA_ADVERTISED_LISTENERS => return localhost:9091 => App will read/write on localhost:9091

--Create topic
docker exec -it kafka1 kafka-topics --create --topic my-topic --bootstrap-server localhost:9091,localhost:9092,localhost:9093 --partitions 5 --replication-factor 3
docker exec -it kafka1 kafka-topics --describe --topic my-topic --bootstrap-server localhost:9091,localhost:9092,localhost:9093
docker exec -it kafka1 kafka-topics --list --bootstrap-server localhost:9091,localhost:9092,localhost:9093
docker exec -it kafka1 kafka-topics --delete --topic test-topic --bootstrap-server localhost:9091,localhost:9092,localhost:9093

--Create producer
docker exec -it kafka1 kafka-console-producer --topic my-topic --bootstrap-server localhost:9091,localhost:9092,localhost:9093
docker exec -it kafka1 kafka-console-producer --topic my-topic --bootstrap-server localhost:9091,localhost:9092,localhost:9093 --property parse.key=true --property key.separator=:
--Create consumer
docker exec -it kafka1 kafka-console-consumer --topic my-topic --from-beginning --bootstrap-server localhost:9091,localhost:9092,localhost:9093
--Create consumer group
docker exec -it kafka1 kafka-console-consumer --bootstrap-server localhost:9091,localhost:9092,localhost:9093 --topic my-topic --group my-topic-cons
-- Describe
docker exec kafka1 kafka-consumer-groups --bootstrap-server localhost:9091,localhost:9092,localhost:9093 --group my-topic-cons --describe
docker exec kafka1 kafka-consumer-groups --bootstrap-server localhost:9091,localhost:9092,localhost:9093 --group my-topic-cons --describe
