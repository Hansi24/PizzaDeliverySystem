import React, { useState } from 'react';

const Checkout = ({ customizedPizzas }) => {
  const [paymentMethod, setPaymentMethod] = useState('Credit Card');
  const totalPrice = customizedPizzas.reduce((acc, pizza) => acc + pizza.price, 0);

  const handleCheckout = () => {
    alert(`Payment of $${totalPrice} successful using ${paymentMethod}`);
  };

  return (
    <div className="checkout">
      <h2>Checkout</h2>
      <div>
        <label>Payment Method:</label>
        <select value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}>
          <option value="Credit Card">Credit Card</option>
          <option value="PayPal">PayPal</option>
        </select>
      </div>
      <div>
        <h3>Total: ${totalPrice}</h3>
      </div>
      <button onClick={handleCheckout}>Place Order</button>
    </div>
  );
};

export default Checkout;
