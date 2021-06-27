package com.panjw.interceptor;

import com.panjw.msgVo.UserVo;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author panjw
 * @date 2021/3/21 22:30
 */
public class TimeInterceptor implements ProducerInterceptor<String, UserVo> {
    @Override
    public ProducerRecord<String, UserVo> onSend(ProducerRecord<String, UserVo> record) {
        ProducerRecord<String, UserVo> producerRecordNew = new ProducerRecord<String, UserVo>(record.topic(),
                record.partition(),
                record.key(),
                record.value());
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
