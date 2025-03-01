import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerService {

    private Properties props;

    public KafkaProducerService(Properties props) {
        this.props = props;
    }

    // Gửi message đến topic và không define key.
    // Message được route đến partition bất kì của topic.
    public <V> void ProducerRecord(String topic, V value) {

    }

    // Gửi message đến topic có define key.
    // Các message cùng key được route đến cùng một partition.
    public <K, V> void ProducerRecord(String topic, K key, V value) {
        try (KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props)) {
            for (int i = 0; i < 15; i++) {
                Thread.sleep(100);
                final ProducerRecord<String, String> message = new ProducerRecord<>(
                        topic,
                        "key-" + i,
                        "message: " + value + i
                );
                producer.send(message);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // Gửi message đến chính xác topic partition.
    public <K, V> void ProducerRecord(String topic, Integer partition, K key, V value) {

    }


}
