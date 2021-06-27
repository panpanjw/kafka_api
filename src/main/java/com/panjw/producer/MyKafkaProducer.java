package com.panjw.producer;

import com.alibaba.fastjson.JSON;
import com.panjw.msgVo.UserVo;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author panjw
 * @date 2021/3/21 14:47
 */
public class MyKafkaProducer {
    private Properties properties;

    public MyKafkaProducer(){
        this.properties = new Properties();
        //Kafka服务器的主机名和端口号
        this.properties.put("bootstrap.servers", "127.0.0.1:9092");
        //等待所有副本节点的应答
        this.properties.put("acks", "all");
        //消息发送最大尝试次数
        this.properties.put("retries", 0);
        //一批消息处理大小
        this.properties.put("batch.size", 16384);
        //请求延时
        this.properties.put("linger.ms", 1);
        //发送缓存区内存大小
        this.properties.put("buffer.memory", 33554432);
        //key序列化
        this.properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //value序列化
        this.properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //this.properties.put("value.serializer", "com.panjw.msgVo.UserVoSerializer");
        //自定义分区,自定义一个类实现org.apache.kafka.clients.producer.Partitioner接口
        //properties.put("partitioner.class", "com.panjw.partitioner.KafkaPartitioner");
        //注册拦截器
        List<String> interceptors = new ArrayList<>();
        //interceptors.add("com.panjw.interceptor.TimeInterceptor");
        interceptors.add("com.panjw.interceptor.CounterInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

//        AdminClient adminClient = AdminClient.create(properties);
//        ArrayList<NewTopic> topicList = new ArrayList<>();
//        topicList.add(new NewTopic("panjw", 1, (short) 1));
//        CreateTopicsResult topicsResult = adminClient.createTopics(topicList);
//        try {
//            topicsResult.all().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    public void sendData(){
        KafkaProducer producer = new KafkaProducer(this.properties);
        for (int i = 800; i < 1000; i++) {
            //ProducerRecord<String, String> producerRecord = new ProducerRecord<>("panjw", 0, Integer.toString(i), Integer.toString(i));
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("panjw", "7", Integer.toString(i));
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (metadata != null){
                        System.out.println(metadata.partition() + "---" + metadata.offset());
                    }
                }
            });
        }
        producer.close();
    }

    public void sendMsg(UserVo userVo){
        KafkaProducer kafkaProducer = new KafkaProducer(this.properties);
        Random random = new Random();
        String key = random.nextInt(100) + "";
        //System.out.println("hashcode = " + key.hashCode() +"  " + key.hashCode() % 3);
        ProducerRecord producerRecord = new ProducerRecord("panjw", JSON.toJSONString(userVo));
        kafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println("分区：" + metadata.partition()
                        + "     "
                        + "offset = " + metadata.offset());
            }


        });
    }
}
