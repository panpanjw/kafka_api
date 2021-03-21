import com.panjw.customer.KafkaCustomer;
import com.panjw.producer.KafkaProducer;
import org.junit.Test;

/**
 * @author panjw
 * @date 2021/3/21 15:55
 */
public class KafkaTest {

    @Test
    public void Producer(){
        KafkaProducer kafkaProducer = new KafkaProducer();
        kafkaProducer.sendData();
    }

    @Test
    public void consumer(){
        KafkaCustomer kafkaCustomer = new KafkaCustomer();
        kafkaCustomer.consumer();
    }
}
