package com.example.db_project.Repository;

import com.example.db_project.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
