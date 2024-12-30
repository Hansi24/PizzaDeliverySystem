import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import Menu from './components/PizzaMenu';
import PizzaCustomization from './components/PizzaCustomization';
import Cart from './components/CartModal';
import DeliveryMethod from './components/DeliveryMethod';
import PaymentMethod from './components/PaymentMethod';
import UserProfile from './components/UserProfile';
import OrderSummary from './components/OrderSummary';
import AdminOrderSummary from './components/AdminOrderSummery';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/pizza-customization" element={<PizzaCustomization />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/delivery-method" element={<DeliveryMethod />} />
        <Route path="/payment-method" element={<PaymentMethod />} />
        <Route path="/user-profile" element={<UserProfile />} />
        <Route path="/orderSummary" element={<OrderSummary />} />
        <Route path="/admin-orderSummary" element={<AdminOrderSummary />} />
   
      <Route
        path="/order-confirmation"
        element={<h1>Thank you for your order!</h1>}
      />
    </Routes>
    </Router>
  );
};

export default App;
