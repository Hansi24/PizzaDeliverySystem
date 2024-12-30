package com.pizzashop.services;

import com.pizzashop.repositories.PaymentRepository;
import com.pizzashop.response.PaymentOrderStatusResponse;
import com.pizzashop.response.PaymentWithOrderStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentWithOrderStatusResponse> getPaymentOrderStatusesByUserId(Long userId) {
        return paymentRepository.findPaymentsWithOrderStatusByUserId(userId);
    }
}