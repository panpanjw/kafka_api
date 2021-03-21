package com.panjw.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author panjw
 * @date 2021/3/21 22:30
 */
public class TimeInterceptor implements ProducerInterceptor<String, String> {
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        ProducerRecord<String, String> producerRecordNew = new ProducerRecord<>(record.topic(),
                record.partition(),
                record.key(),
                System.currentTimeMillis() + "," + record.value());
        return producerRecordNew;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
