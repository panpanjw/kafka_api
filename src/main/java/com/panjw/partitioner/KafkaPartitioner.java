package com.panjw.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.List;
import java.util.Map;

/**
 * @author panjw
 * @date 2021/3/21 21:42
 */
public class KafkaPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //控制分区
        int hashCode = Math.abs(key.hashCode());
        int partitionsSize = cluster.partitionCountForTopic(topic);
        System.out.println("计算分区：" + hashCode % partitionsSize);
        return hashCode % partitionsSize;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
