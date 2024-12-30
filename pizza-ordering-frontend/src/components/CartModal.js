import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import '../styles/CartModal.css'

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [selectedItems, setSelectedItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/custom-pizzas");
        setCartItems(response.data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchCartItems();
  }, []);

  const handleSelectionChange = (item) => {
    setSelectedItems((prev) =>
      prev.includes(item)
        ? prev.filter((selectedItem) => selectedItem !== item)
        : [...prev, item]
    );
  };

  const handleQuantityChange = (item, operation) => {
    setCartItems((prev) =>
      prev.map((pizza) =>
        pizza.selectedPizza.name === item.selectedPizza.name
          ? {
              ...pizza,
              quantity:
                operation === "increase"
                  ? pizza.quantity + 1
                  : pizza.quantity > 1
                  ? pizza.quantity - 1
                  : 1,
            }
          : pizza
      )
    );
  };

  const handleOrderNow = async () => {
    if (selectedItems.length === 0) {
      alert("Please select at least one pizza to order.");
      return;
    }

    // Assuming selectedItems is an array of pizza objects:
    const pizzasToSend = selectedItems.map(item => ({
      name: item.selectedPizza.name,
      size: item.selectedPizza.size,
      crust: item.crust,
      sauce: item.sauce,
      cheese: item.cheese,
      toppings: item.toppings,
      additionalNotes: item.additionalNotes,
      pizzaPrice: item.pizzaPrice,
      quantity: item.quantity, // Added quantity to the data sent
    }));

    try {
      // Send the selected pizzas to the backend to save in the database
    //   await axios.post("http://localhost:8080/api/custom-pizzas-list", pizzasToSend);

      // Navigate to delivery method selection page
      navigate("/delivery-method", { state: { selectedPizzas: pizzasToSend } });
    } catch (error) {
      console.error("Error saving pizzas:", error);
      alert("Failed to save pizzas. Please try again.");
    }
  };

  return (
    <div className="cart">
      <header className="cart-header">
        <h1>Your Cart</h1>
      </header>
      {loading ? (
        <p>Loading cart...</p>
      ) : error ? (
        <p>Error loading cart: {error}</p>
      ) : cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <div>
          <ul className="cart-list">
            {cartItems.map((item, index) => (
              <li key={index} className="cart-item">
                <div className="cart-item-checkbox">
                  <input
                    type="checkbox"
                    checked={selectedItems.includes(item)}
                    onChange={() => handleSelectionChange(item)}
                  />
                </div>
                <div className="cart-item-details">
                  <h4>{item.selectedPizza.name}</h4>
                  <p>Size: {item.selectedPizza.size}</p>
                  <p>Crust: {item.crust}</p>
                  <p>Sauce: {item.sauce}</p>
                  <p>Cheese: {item.cheese}</p>
                  <p>Toppings: {item.toppings.join(", ")}</p>
                  <p><strong>Pizza Price:</strong> LKR {item.pizzaPrice}</p>
                  <p><strong>Additional Notes:</strong> {item.additionalNotes}</p>

                  <div className="quantity-control">
                    <button
                      onClick={() => handleQuantityChange(item, "decrease")}
                      className="quantity-button"
                    >
                      -
                    </button>
                    <span className="quantity">{item.quantity}</span>
                    <button
                      onClick={() => handleQuantityChange(item, "increase")}
                      className="quantity-button"
                    >
                      +
                    </button>
                  </div>
                </div>
              </li>
            ))}
          </ul>
          <button onClick={handleOrderNow}>Order Now</button>
        </div>
      )}
    </div>
  );
};

export default Cart;
