package aforo255.ms.test.pay.controller;

import aforo255.ms.test.pay.domain.Operation;
import aforo255.ms.test.pay.domain.OperationRedis;
import aforo255.ms.test.pay.producer.PaymentEventProducer;
import aforo255.ms.test.pay.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentEventProducer paymentEventProducer;

    public PaymentController(PaymentService paymentService, PaymentEventProducer paymentEventProducer) {
        this.paymentService = paymentService;
        this.paymentEventProducer = paymentEventProducer;
    }

    @GetMapping("/payments/all")
    public Map<String, OperationRedis> getAllPayments(){
        return paymentService.findAll();
    }

    @PostMapping("/payments")
    public ResponseEntity<Operation> savePayment(@RequestBody Operation newRecord) throws JsonProcessingException {
        Operation operation = paymentService.save(newRecord);
        paymentEventProducer.sendPaymentEvent(operation);
        return ResponseEntity.status(HttpStatus.CREATED).body(operation);
    }
}
