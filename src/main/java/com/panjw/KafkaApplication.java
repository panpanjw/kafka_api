package com.panjw;

import com.panjw.customer.KafkaCustomer;
import com.panjw.threadPool.ThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author panjw
 * @date 2021/3/21 14:32
 */

@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
        ThreadPool threadPool = new ThreadPool();
        threadPool.execute(() -> {
            KafkaCustomer kafkaCustomer = new KafkaCustomer();
            kafkaCustomer.consumer();

        });

        threadPool.execute(() -> {
            KafkaCustomer kafkaCustomer = new KafkaCustomer();
            kafkaCustomer.consumer();

        });

    }
}
