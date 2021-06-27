package com.panjw.msgVo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

/**
 * @author panjw
 * @date 2021/6/6 14:52
 */
public class UserVoDeserializer implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        UserVo userVo = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userVo = objectMapper.readValue(data, UserVo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userVo;
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    @Override
    public void close() {

    }
}
