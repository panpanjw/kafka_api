package com.panjw.controllor;

import com.panjw.service.KafkaOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panjw
 * @date 2021/6/5 20:41
 */
@RestController
public class KafkaControllor {

    @Autowired
    private KafkaOperate kafkaOperate;

    @PostMapping(value = "/sendMsg")
    public boolean sendMsg(){
        kafkaOperate.sendMsg();
        return true;
    }
}
