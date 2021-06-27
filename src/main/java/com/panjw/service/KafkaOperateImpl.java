package com.panjw.service;

import com.panjw.msgVo.UserVo;
import com.panjw.producer.MyKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author panjw
 * @date 2021/6/5 20:49
 */
@Service
public class KafkaOperateImpl implements KafkaOperate{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendMsg() {
        Long count = stringRedisTemplate.opsForValue().increment("count");
        UserVo userVo = new UserVo();
        userVo.setUserName("panjw" + count);
        userVo.setAge(count);
        MyKafkaProducer myKafkaProducer = new MyKafkaProducer();
        myKafkaProducer.sendMsg(userVo);
    }
}
