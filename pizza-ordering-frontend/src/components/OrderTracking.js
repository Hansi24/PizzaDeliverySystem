import React, { useState } from 'react';
import ApiService from '../services/ApiService';

const OrderTracking = () => {
  const [orderId, setOrderId] = useState('');
  const [status, setStatus] = useState('');

  const trackOrder = () => {
    ApiService.trackOrder(orderId).then((response) => {
      setStatus(response.data.status);
    });
  };

  return (
    <div>
      <h2>Track Your Order</h2>
      <input
        type="text"
        placeholder="Enter Order ID"
        value={orderId}
        onChange={(e) => setOrderId(e.target.value)}
      />
      <button onClick={trackOrder}>Track</button>
      {status && <p>Order Status: {status}</p>}
    </div>
  );
};

export default OrderTracking;
