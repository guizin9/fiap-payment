package com.fiapfoodpayment.service;

import com.fiapfoodpayment.domain.Payment;
import com.fiapfoodpayment.dto.StatusDTO;
import com.fiapfoodpayment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public String processarPagamento(Payment payment) {
        String status = simularPagamento();
        payment.setStatus(status);
        paymentRepository.save(payment);
        return status;
    }

    private String simularPagamento() {
        Random random = new Random();
        int result = random.nextInt(2);
        return result == 1 ? "aprovado" : "reprovado";
    }

//    public String getStatusById(Long id) {
//        Optional<Payment> paymentOptional = paymentRepository.findById(id);
//        return paymentOptional.map(Payment::getStatus).orElse(null);
//    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public ResponseEntity<StatusDTO> getStatusById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        String status = paymentOptional.map(Payment::getStatus).orElse(null);
        if (status != null) {
            StatusDTO statusDTO = new StatusDTO(status);
            return ResponseEntity.ok(statusDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}