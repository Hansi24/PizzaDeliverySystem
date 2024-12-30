import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

const PaymentMethod = () => {
  const { state } = useLocation();
  console.log(state);
  const { user, customPizzas, deliveryMethod, totalPrice, id } = state.data || {};
  const [paymentMethod, setPaymentMethod] = useState('');
  const [paymentDetails, setPaymentDetails] = useState({});
  const navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  const handlePaymentDetailChange = (e) => {
    const { name, value } = e.target;
    setPaymentDetails((prevDetails) => ({
      ...prevDetails,
      [name]: value,
    }));
  };

  const handleConfirmPayment = () => {
    if (!paymentMethod) {
      alert('Please select a payment method.');
      return;
    }
  
  
    // Create the payment payload with userId and orderDetails.id (customOrderId)
    const paymentPayload = {
      user: {
        id: user.id, // User ID (retrieved from localStorage)
      }, // Include userId in the payment payload   
      conformOrder: {
        id: id
      }, // Include the full order details
      amount: totalPrice, // Include the total price
      paymentMethod: paymentMethod.toLowerCase().trim(), // Include selected payment method
    };
  
    // Send paymentPayload to backend via API call
    axios
      .post('http://localhost:8080/api/payments/make-payment', paymentPayload)
      .then(response => {
        console.log('Pizza customization saved successfully!', response);
        const { orderStatus:orderStatus, payment: payment } = response.data;

        if (payment.paid) {
          window.alert('Your Paid Order Placed Successfully');
        } else if (!payment.paid && payment.paymentMethod === 'cash on delivery') {
          window.alert('Your Order Placed Successfully. Make Your Payment once you receive the order.');
        } else {
          window.alert('Your Order Placement Was Not Successful.');
        }
        navigate('/OrderSummary');
        // Navigate or handle the response if needed
      })
      .catch(error => {
        console.error('Error saving pizza customization:', error);
        if (error.response) {
          console.error('Response data:', error.response.data);
          console.error('Response status:', error.response.status);
          console.error('Response headers:', error.response.headers);
        } else if (error.request) {
          console.error('Request made but no response received:', error.request);
        } else {
          console.error('Error setting up the request:', error.message);
        }
      });
  };
  
  return (
    <div className="payment-method">
      <h1>Select Payment Method</h1>
      <p>User: {user?.id}</p>
      <p>Delivery Method: {deliveryMethod || 'N/A'}</p>
      <p>Total Price: {totalPrice || 'N/A'}</p>
      <p>Pizza {customPizzas.length || 0} selected</p>

      <div>
        <label>
          <input
            type="radio"
            name="paymentMethod"
            value="Credit Card"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />
          Credit Card
        </label>
        <label>
          <input
            type="radio"
            name="paymentMethod"
            value="Bank Transfer"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />
          Bank Transfer
        </label>
        <label>
          <input
            type="radio"
            name="paymentMethod"
            value="Cash on Delivery"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />
          Cash on Delivery
        </label>
      </div>

      {paymentMethod === 'Credit Card' && (
        <div>
          <h3>Enter Credit Card Details</h3>
          <input
            type="text"
            name="cardNumber"
            placeholder="Card Number"
            onChange={handlePaymentDetailChange}
          />
          <input
            type="text"
            name="expiryDate"
            placeholder="Expiry Date"
            onChange={handlePaymentDetailChange}
          />
          <input
            type="text"
            name="cvv"
            placeholder="CVV"
            onChange={handlePaymentDetailChange}
          />
        </div>
      )}

      <button onClick={handleConfirmPayment}>Confirm Payment</button>
    </div>
  );
};

export default PaymentMethod;
