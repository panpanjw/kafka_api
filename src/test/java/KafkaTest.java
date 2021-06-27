import com.panjw.customer.KafkaCustomer;
import com.panjw.producer.MyKafkaProducer;
import com.panjw.service.KafkaOperate;
import com.panjw.threadPool.ThreadPool;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author panjw
 * @date 2021/3/21 15:55
 */
public class KafkaTest {

    @Test
    public void Producer(){
        MyKafkaProducer myKafkaProducer = new MyKafkaProducer();
        myKafkaProducer.sendData();
    }

    @Test
    public void consumer(){

        KafkaCustomer kafkaCustomer = new KafkaCustomer();
        kafkaCustomer.consumer();
    }

    @Test
    public void consumerGroup() {
        ThreadPool threadPool = new ThreadPool();
        threadPool.execute(() -> {
            KafkaCustomer kafkaCustomer = new KafkaCustomer();
            kafkaCustomer.consumer();

        });

        threadPool.execute(() -> {
            KafkaCustomer kafkaCustomer = new KafkaCustomer();
            kafkaCustomer.consumer();

        });

        threadPool.execute(() -> {
            KafkaCustomer kafkaCustomer = new KafkaCustomer();
            kafkaCustomer.consumer();

        });
        threadPool.shutdown();
    }
}
