import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import "../styles/PizzaCustomization.css";

const PizzaCustomization = () => {
  const { state } = useLocation();
  const pizza = state ? state.pizza : {}; // Get the selected pizza from location state

  const [crust, setCrust] = useState("");
  const [sauce, setSauce] = useState("");
  const [toppings, setToppings] = useState([]);
  const [cheese, setCheese] = useState("");
  const [additionalNotes, setAdditionalNotes] = useState("");
  const [totalPrice, setTotalPrice] = useState(pizza.price || 0);
  const userId = localStorage.getItem("userId"); 

  const crustOptions = [
    { name: "Thin Crust", price: 100 },
    { name: "Thick Crust", price: 150 },
    { name: "Gluten Free", price: 200 },
  ];

  const sauceOptions = [
    { name: "Tomato Basil", price: 50 },
    { name: "BBQ", price: 70 },
    { name: "White Garlic", price: 60 },
  ];

  const toppingOptions = [
    { name: "Pepperoni", price: 120 },
    { name: "Olives", price: 80 },
    { name: "Mushrooms", price: 100 },
    { name: "Basil", price: 60 },
  ];

  const cheeseOptions = [
    { name: "Mozzarella", price: 100 },
    { name: "Cheddar", price: 120 },
    { name: "Vegan", price: 150 },
  ];

  const updateTotalPrice = () => {
    const crustPrice = crustOptions.find((c) => c.name === crust)?.price || 0;
    const saucePrice = sauceOptions.find((s) => s.name === sauce)?.price || 0;
    const cheesePrice = cheeseOptions.find((c) => c.name === cheese)?.price || 0;
    const toppingsPrice = toppings.reduce(
      (acc, topping) => acc + (toppingOptions.find((t) => t.name === topping)?.price || 0),
      0
    );

    setTotalPrice((pizza.price || 0) + crustPrice + saucePrice + cheesePrice + toppingsPrice);
  };

  const handleToppingToggle = (topping) => {
    setToppings((prev) =>
      prev.includes(topping) ? prev.filter((t) => t !== topping) : [...prev, topping]
    );
  };

  useEffect(() => {
    updateTotalPrice();
  }, [crust, sauce, cheese, toppings]);

  const handleSubmit = async () => {
    const customPizza = {
      crust, // Selected crust like "Thin Crust"
      sauce, // Selected sauce like "Tomato"
      cheese, // Selected cheese like "Mozzarella"
      toppings, // Array of selected toppings like ["Pepperoni", "Mushrooms", "Olives"]
      selectedPizza: {
        id: pizza.id, // Selected pizza ID
      },
      pizzaName: pizza.name || "Custom Pizza", // Default to "Custom Pizza" if pizza name is undefined
      pizzaPrice: totalPrice, // Total price of the custom pizza
      additionalNotes, // Additional notes provided by the user
      user: {
        id: userId, // User ID (this could come from your state, context, or localStorage)
      },
    };
  
    try {
      const response = await axios.post("http://localhost:8080/api/custom-pizzas/create", customPizza);
      console.log("Pizza customization saved:", response.data);
      alert("Pizza customization saved successfully!");
    } catch (error) {
      console.error("Error saving pizza customization:", error);
      alert("Failed to save pizza customization. Please try again.");
    }
  };
  
  const renderOptions = (options, selected, setSelected, isMultiSelect = false) => {
    return options.map((option) => (
      <button
        key={option.name}
        className={`option-button ${
          selected === option.name || (isMultiSelect && toppings.includes(option.name))
            ? "selected"
            : ""
        }`}
        onClick={() => (isMultiSelect ? handleToppingToggle(option.name) : setSelected(option.name))}
      >
        {option.name} (Rs. {option.price})
      </button>
    ));
  };

  return (
    <div className="pizza-customization-container">
      <h2 className="title">Customize your Pizza: {pizza.name || "Custom Pizza"}</h2>

      <div className="pizza-details">
        <img src={pizza.image} alt={pizza.name} className="pizza-image" />
        <div className="pizza-info">
          <h3>{pizza.name}</h3>
          <p>{pizza.description}</p>
          <p>Base Price: Rs. {pizza.price || "N/A"}</p>
        </div>
      </div>

      <div className="customization-options">
        <div>
          <h3>Crust</h3>
          {renderOptions(crustOptions, crust, setCrust)}
        </div>

        <div>
          <h3>Sauce</h3>
          {renderOptions(sauceOptions, sauce, setSauce)}
        </div>

        <div>
          <h3>Toppings</h3>
          {renderOptions(toppingOptions, null, null, true)}
        </div>

        <div>
          <h3>Cheese</h3>
          {renderOptions(cheeseOptions, cheese, setCheese)}
        </div>

        <div>
          <h3>Additional Notes</h3>
          <textarea
            className="notes-textarea"
            value={additionalNotes}
            onChange={(e) => setAdditionalNotes(e.target.value)}
            placeholder="Add any special instructions..."
          />
        </div>
      </div>

      <div className="total-price">
        <h3>Total Price: Rs. {totalPrice.toFixed(2)}</h3>
      </div>

      <button className="submit-button" onClick={handleSubmit}>
        Save Customization
      </button>
    </div>
  );
};

export default PizzaCustomization;
