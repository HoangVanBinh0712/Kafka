# Kafka Demo
A simple repository that contains the `docker-compose.yml` file to create multiple brokers within a Kafka cluster (managed by `Zookeeper`) and Java code that creates producers and consumers from a topic of existed kafka brokers what are running inside the docker. 
# Framework
<strong>Framework:</strong>  `Java`, `Docker`, `Kafka`
# How to use
<strong>Step 1:</strong> Run command `docker-compose up -d` to pull the images and create containers
<br>
<strong>Step 2:</strong> Run command `docker exec -it kafka1 kafka-topics --create --topic my-topic --bootstrap-server localhost:9091,localhost:9092,localhost:9093 --partitions 5 --replication-factor 3` to create topic
<br>
<strong>Step 3:</strong> Run `Consumer.java` and `Producer.java`

<strong>Note that this repo is just an example to show my current knowledge and experience. </strong>
<div>Author: Hoàng Văn Bình</div>
<div>Mail: thebest11447@gmail.com</div>
<div>Phone: 0337445596</div>
