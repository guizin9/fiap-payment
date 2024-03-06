package com.fiapfoodpayment.controller;

import com.fiapfoodpayment.domain.Payment;
import com.fiapfoodpayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<String> processarPagamento(@RequestBody Payment payment) {
        String resultadoPagamento = paymentService.processarPagamento(payment);
        return ResponseEntity.status(HttpStatus.OK).body(resultadoPagamento);
    }

}

