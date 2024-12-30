package com.pizzashop.repositories;

import com.pizzashop.models.Payment;
import com.pizzashop.response.PaymentWithOrderStatusResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserId(Long userId);

    @Query("SELECT new com.pizzashop.response.PaymentWithOrderStatusResponse(p.id, p.amount, p.isPaid, p.paymentMethod, p.conformOrder.id, p.user.id, o.currentState, p.conformOrder.deliveryMethod, p.conformOrder.totalPrice) " +
            "FROM Payment p " +
            "LEFT JOIN OrderStatus o ON p.id = o.payment.id " +
            "LEFT JOIN ConformOrder c ON p.conformOrder.id = c.id " +
            "WHERE p.user.id = :userId")
    List<PaymentWithOrderStatusResponse> findPaymentsWithOrderStatusByUserId(@Param("userId") Long userId);

}
