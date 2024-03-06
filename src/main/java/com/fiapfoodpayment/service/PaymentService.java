package com.fiapfoodpayment.service;

import com.fiapfoodpayment.domain.Payment;
import com.fiapfoodpayment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public String processarPagamento(Payment payment) {
        String status = simularPagamento();
        payment.setStatus(status);
        paymentRepository.save(payment);
        return "Pagamento " + status;
    }

    private String simularPagamento() {
        Random random = new Random();
        int result = random.nextInt(2);
        return result == 1 ? "aprovado" : "reprovado";
    }
}