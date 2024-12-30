import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Menu.css";

const Menu = () => {
  const [pizzas, setPizzas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchPizzas = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/pizza");
        setPizzas(response.data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchPizzas();
  }, []);

  const handleAddToCart = (pizza) => {
    navigate("/pizza-customization", { state: { pizza } });
  };

  const handleUserProfile = () => {
    navigate("/user-profile");  // Navigate to the User Profile page
  };

  return (
    <div className="menu">
      <header className="menu-header">
        <div className="menu-header-left">
          <h1>Pizza Menu</h1>
        </div>
        <div className="menu-header-right">
          <div className="user-profile" onClick={handleUserProfile}>
            <img src="user-profile-icon.png" alt="User" />
            <span>Welcome, User</span>
          </div>
          <button className="cart-button" onClick={() => navigate("/cart")}>
            Go to Cart
          </button>
          <button className="cart-button" onClick={() => navigate("/orderSummary")}>
            Go to Order Summary
          </button>
        </div>
      </header>

      <div className="seasonal-specials">
        <h3>Seasonal Specials & Promotions</h3>
        <div className="specials">
          <div className="special-item">
            <h4>Buy One, Get One Free!</h4>
            <p>Enjoy our BOGO offer on selected pizzas!</p>
          </div>
          <div className="special-item">
            <h4>20% Off All Pizzas</h4>
            <p>Limited-time offer! Get 20% off on all pizzas.</p>
          </div>
        </div>
      </div>

      {loading ? (
        <p>Loading pizzas...</p>
      ) : error ? (
        <p>Error fetching pizzas: {error}</p>
      ) : (
        <div className="pizza-list">
          {pizzas.map((pizza) => (
            <div className="pizza-card" key={pizza.id}>
              <img src={pizza.imageUrl} alt={pizza.name} className="pizza-image" />
              <div className="pizza-info">
                <p><strong>Price:</strong> LKR {pizza.price.toFixed(2)}</p>
                <h4>{pizza.name}</h4>
                <p>{pizza.description}</p>
                <button className="add-to-cart" onClick={() => handleAddToCart(pizza)}>
                  Customize & Add
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Menu;
