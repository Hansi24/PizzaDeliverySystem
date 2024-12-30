import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminOrderSummary = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch all orders data from API
  useEffect(() => {
    const fetchAllOrders = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/payments/orders');
        setOrders(response.data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchAllOrders();
  }, []);

  // Handle Next Process button click
  const handleNextProcess = async (orderId, index) => {
    try {
      // Update the order state in the backend
      const response = await axios.put(`http://localhost:8080/api/order-status/${orderId}/update-status`);
      if(response){
        window.alert("Order Status updated with OrderId " + (index + 1))
        window.location.reload(); // Reload the page to reflect updated data

      }
      // After the update, reload the window to fetch fresh data
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div className="order-summary">
      <h2>Admin Order Summary</h2>
      {loading && <p>Loading payments...</p>}
      {error && <p>Error: {error}</p>}
      <ul>
        {orders.length > 0 ? (
          orders.sort((a, b) => b.id - a.id).map((order, index) => (
            <li key={order.id}>
              <h3>Order {index+1}</h3>
              <p><strong>Amount:</strong> ${order.payment.amount}</p>
              <p><strong>Payment Method:</strong> {order.payment.paymentMethod}</p>
              <p><strong>Payment:</strong> {order.payment.paid ? 'Paid' : 'Pending'}</p>
              <p><strong>Order Status:</strong> {order.currentState.type}</p>
              <h4>Customized Pizzas:</h4>
              <ul>
                {order.payment.conformOrder.customPizzas.map((pizza, pizzaIndex) => (
                  <li key={pizzaIndex}>
                    {pizza.crust} Pizza with {pizza.sauce} and {pizza.toppings.join(', ')}
                  </li>
                ))}
              </ul>
              <p><strong>Total Price for Order:</strong> ${order.payment.conformOrder.totalPrice}</p>
              <button 
                onClick={() => handleNextProcess(order.id, index)}
              >
                Next Process
              </button>
            </li>
          ))
        ) : (
          <p>No payments found</p>
        )}
      </ul>
    </div>
  );
};

export default AdminOrderSummary;
