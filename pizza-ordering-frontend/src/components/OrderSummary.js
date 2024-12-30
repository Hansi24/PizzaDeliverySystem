import React, { useEffect, useState } from 'react';
import axios from 'axios';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const OrderSummary = () => {
  const [orders, setOrders] = useState([]); // Orders data
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(null); // Error state
  const userId = localStorage.getItem("userId"); // Get userId from local storage

  // Fetch payments data from API
  useEffect(() => {
    const fetchPayments = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/payments/orders/${userId}`);
        setOrders(response.data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    if (userId) {
      fetchPayments();
    }
  }, [userId]);

  // WebSocket Connection
  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws'); 
    // // WebSocket endpoint
    const stompClient = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 1000, // Reconnect if connection fails
    });

    // Connect to WebSocket
    stompClient.onConnect = () => {
      console.log('Connected to WebSocket!');

      // Subscribe to the topic for the user's order updates
      stompClient.subscribe(`/topic/order-updates/${userId}`, (message) => {
        const updatedOrder = JSON.parse(message.body);
        console.log(message);
        // Update specific order in state
        setOrders((prevOrders) =>
          prevOrders.map(order =>
            order.id === updatedOrder.orderId
              ? { ...order, currentState: { type: updatedOrder.status } }
              : order
          )
        );

        // Show notification to the user
        alert(`Order ${updatedOrder.orderId} status updated to ${updatedOrder.status}`);
      });
    };

    stompClient.activate();

    return () => {
      stompClient.deactivate(); // Disconnect WebSocket on component unmount
    };
  }, [userId]);

  return (
    <div style={{ padding: '20px', backgroundColor: '#f4f4f4', borderRadius: '8px', boxShadow: '0 2px 10px rgba(0, 0, 0, 0.1)', width: '80%', margin: 'auto' }}>
      <h2 style={{ textAlign: 'center', fontSize: '2rem', color: '#333', marginBottom: '20px' }}>Order-Summary</h2>
      {loading && <p style={{ textAlign: 'center', fontSize: '1.2rem', color: '#007BFF' }}>Loading payments...</p>}
      {error && <p style={{ textAlign: 'center', fontSize: '1.2rem', color: 'red' }}>Error: {error}</p>}
      <ul style={{ listStyle: 'none', padding: '0' }}>
        {orders.length > 0 ? (
          orders.map((order, index) => (
            <li key={order.id} style={{ backgroundColor: '#fff', padding: '20px', marginBottom: '15px', borderRadius: '8px', boxShadow: '0 1px 5px rgba(0, 0, 0, 0.1)' }}>
              <h3 style={{ fontSize: '1.5rem', color: '#333', marginBottom: '10px' }}>Order {index + 1}</h3>
              <p><strong>Amount:</strong> ${order.payment.amount}</p>
              <p><strong>Payment Method:</strong> {order.payment.paymentMethod}</p>
              <p style={{ color: order.payment.paid ? 'green' : 'red', fontWeight: 'bold' }}><strong>Payment:</strong> {order.payment.paid ? 'Paid' : 'Pending'}</p>
              <p style={{ color: order.currentState.type === 'Delivered' ? 'green' : 'orange', fontWeight: 'bold' }}>
                <strong>Order Status:</strong> {order.currentState.type}
              </p>
              <h4 style={{ fontSize: '1.2rem', color: '#333', marginTop: '10px' }}>Customized Pizzas:</h4>
              <ul style={{ listStyle: 'none', paddingLeft: '20px' }}>
                {order.payment.conformOrder.customPizzas.map((pizza, pizzaIndex) => (
                  <li key={pizzaIndex} style={{ fontSize: '1rem', color: '#555' }}>
                    {pizza.crust} Pizza with {pizza.sauce} and {pizza.toppings.join(', ')}
                  </li>
                ))}
              </ul>
              <p><strong>Total Price for Order:</strong> ${order.payment.conformOrder.totalPrice}</p>
            </li>
          ))
        ) : (
          <p style={{ textAlign: 'center', fontSize: '1.2rem', color: '#999' }}>No payments found</p>
        )}
      </ul>
    </div>
  );
};

export default OrderSummary;
