//package com.pizzashop.config;
//
//
//import com.pizzashop.strategy.CashOnDeliveryPayment;
//import com.pizzashop.strategy.CreditCardPayment;
//import com.pizzashop.strategy.PayPalPayment;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PaymentConfig {
//
//    @Bean
//    public CreditCardPayment creditCardPayment() {
//        return new CreditCardPayment();
//    }
//
//    @Bean
//    public PayPalPayment payPalPayment() {
//        return new PayPalPayment();
//    }
//
//    @Bean
//    public CashOnDeliveryPayment cashOnDeliveryPayment() {
//        return new CashOnDeliveryPayment();
//    }
//}
