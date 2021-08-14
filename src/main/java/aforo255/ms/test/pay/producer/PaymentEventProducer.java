package aforo255.ms.test.pay.producer;

import aforo255.ms.test.pay.domain.Operation;
import aforo255.ms.test.pay.domain.OperationRedis;
import aforo255.ms.test.pay.service.PaymentService;
import aforo255.ms.test.pay.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Component
public class PaymentEventProducer {

    private final Logger logger = LoggerFactory.getLogger(PaymentEventProducer.class);

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    public PaymentEventProducer(KafkaTemplate<Integer, String> kafkaTemplate, ObjectMapper objectMapper, PaymentService paymentService) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
    }

    public void sendPaymentEvent(Operation operation) throws JsonProcessingException {
        Integer key = operation.getIdOperation();
        String value = objectMapper.writeValueAsString(operation);

        ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value);
        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                handleFailure(key, value, throwable);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                try {
                    handleSuccess(key, value, result);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value) {
        List<Header> recordHeaders = List.of(new RecordHeader("deposit-event-source", "scanner".getBytes()));
        return new ProducerRecord<>(Constants.TOPIC_PAYMENT, null, key, value, recordHeaders);
    }

    private void handleFailure(Integer key, String value, Throwable throwable) {
        logger.info("Error trying send message, was {} ", throwable.getMessage());
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) throws JsonProcessingException {
        OperationRedis operationRedis = objectMapper.readValue(value, OperationRedis.class);
        paymentService.save(operationRedis);
        logger.info("Message Sent Successfully for the key :{} and the value is {},partition is {}", key, value,
                result.getRecordMetadata().partition());
    }




}
