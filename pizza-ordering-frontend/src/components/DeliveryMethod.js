import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";

const DeliveryMethod = () => {
  const [selectedDelivery, setSelectedDelivery] = useState("");
  const [deliveryMessage, setDeliveryMessage] = useState("");
  const navigate = useNavigate();
  const location = useLocation();

  const userId = localStorage.getItem("userId");

  // Get selected pizzas passed from the previous page
  const { selectedPizzas } = location.state || {};

  // Handle the delivery method change
  const handleDeliveryChange = (e) => {
    const value = e.target.value;
    setSelectedDelivery(value);

    // Set the delivery message based on the selected method
    if (value === "Home Delivery") {
      setDeliveryMessage("Additional 80 Rs will be added for home delivery.");
    } else if (value === "Pickup") {
      setDeliveryMessage("Come to the nearest branch and pick your pizza.");
    } else {
      setDeliveryMessage("");
    }
  };

  // Calculate the total price including pizza total and delivery charge
  const calculateTotalPrice = () => {
    const pizzaTotal = selectedPizzas.reduce((total, pizza) => total + pizza.pizzaPrice, 0);
    const deliveryCharge = selectedDelivery === "Home Delivery" ? 80 : 0;
    return pizzaTotal + deliveryCharge;
  };

  // Proceed to payment after validating delivery method selection
  const handleProceedToPayment = () => {
    if (!selectedDelivery) {
      alert("Please select a delivery method.");
      return;
    }
  
    // Assuming you have a user object stored in the localStorage
    const user = {
      id: userId, // Get the userId from localStorage
      email: localStorage.getItem("userEmail"), // Example: fetch the user email from localStorage
      phone: localStorage.getItem("userPhone"), // Example: fetch the user phone from localStorage
      address: localStorage.getItem("userAddress"), // Example: fetch user address if needed
      points: 0, // Assuming you have a points system, initialize as needed
    };
  
    // Combine user, selected pizzas, and delivery method into one object
    const orderData = {
      deliveryMethod: selectedDelivery,
      user: user,
      customPizzas: selectedPizzas.map((pizza) => ({
        // Ensure that pizza object has the necessary details
        pizzaName: pizza.pizzaName,
        size: pizza.size,
        crust: pizza.crust,
        sauce: pizza.sauce,
        cheese: pizza.cheese,
        toppings: pizza.toppings,
        user: { ...user }, // You can pass user details if necessary in each pizza as well
      })),
      totalPrice: calculateTotalPrice(),
    };
  
    // Make an API call to save the order
    fetch("http://localhost:8080/api/conformorder", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(orderData),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        // Navigate to the payment page with order details
        navigate("/payment-method", { state: { data } });
      })
      .catch((error) => {
        console.error("Error creating order:", error);
      });
  };
  

  return (
    <div className="delivery-method">
      <h1>Select Delivery Method</h1>
      <label>
        <input
          type="radio"
          name="delivery"
          value="Home Delivery"
          onChange={handleDeliveryChange}
        />
        Home Delivery
      </label>
      <label>
        <input
          type="radio"
          name="delivery"
          value="Pickup"
          onChange={handleDeliveryChange}
        />
        Pickup
      </label>

      {/* Display additional message based on delivery method */}
      {deliveryMessage && <p>{deliveryMessage}</p>}

      <button onClick={handleProceedToPayment}>Proceed to Payment</button>
    </div>
  );
};

export default DeliveryMethod;
