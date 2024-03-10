package com.fiapfoodpayment.controller;

import com.fiapfoodpayment.domain.Payment;
import com.fiapfoodpayment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Process payment",
            description = "process payment and return aproved or recused")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping
    public ResponseEntity<String> processarPagamento(@RequestBody Payment payment) {
        String resultadoPagamento = paymentService.processarPagamento(payment);
        return ResponseEntity.status(HttpStatus.OK).body(resultadoPagamento);
    }

    @Operation(
            summary = "Find Payment by Id",
            description = "return payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Payment>> getPaymentId(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        return ResponseEntity.status(200).body(payment);
    }

    @Operation(
            summary = "Find status payment byId",
            description = "return status payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
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

