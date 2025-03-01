import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) throws InterruptedException {
        // Kafka Consumer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9091,localhost:9092"); // Change this if needed
        props.put("group.id", "my-group");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest"); // Start reading from the beginning

        Thread thread1 = new Thread(() -> {
            // Create Kafka Consumer
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

            // Subscribe to the topic
            String topic = "my-topic";
            consumer.subscribe(Collections.singletonList(topic));

            // Poll for new data
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("Received message: Key=%s, Value=%s, Partition=%d, Offset=%d%n",
                                record.key(), record.value(), record.partition(), record.offset());
                    }
                }
            } finally {
                consumer.close();
            }
        });
        Thread thread2 = new Thread(() -> {
            // Create Kafka Consumer
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

            // Subscribe to the topic
            String topic = "my-topic";
            consumer.subscribe(Collections.singletonList(topic));

            // Poll for new data
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("Received message: Key=%s, Value=%s, Partition=%d, Offset=%d%n",
                                record.key(), record.value(), record.partition(), record.offset());
                    }
                }
            } finally {
                consumer.close();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

}
