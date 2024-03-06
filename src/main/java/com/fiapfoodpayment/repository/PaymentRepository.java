package com.fiapfoodpayment.repository;

import com.fiapfoodpayment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> { }
