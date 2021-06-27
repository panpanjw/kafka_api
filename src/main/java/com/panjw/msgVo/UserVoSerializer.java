package com.panjw.msgVo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author panjw
 * @date 2021/6/6 14:42
 */
public class UserVoSerializer implements Serializer<UserVo> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, UserVo data) {
        byte[] value = null;
        value = JSON.toJSONBytes(data);
        return value;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, UserVo data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
