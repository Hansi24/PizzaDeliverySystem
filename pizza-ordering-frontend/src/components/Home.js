import React from 'react';
import { Link } from 'react-router-dom';
import backgroundImage from '../asssets/images/background.jpg';

import '../styles/Home.css'; 

const Home = () => (
  <div className="home" style={{ backgroundImage: `url(${backgroundImage})` }}>
    <div className="home-content">
      <h2 className="animated-title">Welcome to the Pizza Shop</h2>
      <p className="animated-description">Build your perfect pizza or choose from our menu.</p>
      {/* <div className='btn-i'>
      <Link to="/menu" className="btn">View Menu</Link>
      </div> */}
      <div className="auth-buttons">
        <Link to="/login" className="btn">Login</Link>
        <Link to="/register" className="btn">Register</Link>
      </div>
    </div>
    
  </div>
);

export default Home;
