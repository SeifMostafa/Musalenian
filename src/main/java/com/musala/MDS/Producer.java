package com.musala.MDS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.MDS.entity.Musalenian;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class Producer {

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void register(Musalenian musalenian) throws JsonProcessingException {
        Integer key = Integer.valueOf(musalenian.getId().intValue());
        String value = objectMapper.writeValueAsString(musalenian);
        ListenableFuture<SendResult<Integer, String>> sendResultListenableFuture = kafkaTemplate.sendDefault(key,
                value);
        sendResultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key, value, result);
            }
        });
    }

    public void registerSync(Musalenian musalenian) throws JsonProcessingException, ExecutionException, InterruptedException {
      kafkaTemplate.sendDefault(Integer.valueOf(musalenian.getId().intValue()),
                objectMapper.writeValueAsString(musalenian)).get();

    }


    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }
}
