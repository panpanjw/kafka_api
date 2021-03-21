package com.panjw.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Map;

/**
 * @author panjw
 * @date 2021/3/21 22:46
 */
public class CounterInterceptor  implements ProducerInterceptor<String, String> {
    private long successCount = 0;
    private long errorCount = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null){
            successCount++;
        }else {
            errorCount++;
        }
    }

    @Override
    public void close() {
        System.out.println("成功的个数：" + successCount);
        System.out.println("失败的个数：" + errorCount);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
