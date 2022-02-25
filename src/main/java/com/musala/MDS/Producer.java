package com.musala.MDS;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Producer {

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

}
