package com.panjw.customer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author panjw
 * @date 2021/3/21 16:20
 */
public class KafkaCustomer {
    private Properties properties = new Properties();

    public KafkaCustomer() {
        //定义kafka服务的地址，不需要指定所有broken
        properties.put("bootstrap.servers", "localhost:9092");
        //指定消费组
        properties.put("group.id", "group1");
        //是否自动确认offset
        properties.put("enable.auto.commit", "true");
        //自动确认offset的时间间隔 1s
        properties.put("auto.commit.interval.ms", "1000");
        //key的序列化类
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //value的序列号类
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

    }

    public void consumer(){
        //定义consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("panjw"));
        while (true) {
            //读取数据，读取超时时间为100ms
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : records){
                System.out.printf("offset = %d, key = %s, value = %s%n", consumerRecord.offset(), consumerRecord.key(), consumerRecord.value());
            }
        }
    }
}
