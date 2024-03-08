package com.fiapfoodpayment.controller;

import com.fiapfoodpayment.domain.Payment;
import com.fiapfoodpayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> processarPagamento(@RequestBody Payment payment) {
        String resultadoPagamento = paymentService.processarPagamento(payment);
        return ResponseEntity.status(HttpStatus.OK).body(resultadoPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Payment>> getPaymentId(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        return ResponseEntity.status(200).body(payment);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getStatusById(@PathVariable Long id) {
        Optional<Payment> paymentOptional = paymentService.getPaymentById(id);
        String status = paymentOptional.map(Payment::getStatus).orElse(null);
        if (status != null) {
            return ResponseEntity.ok(status);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

