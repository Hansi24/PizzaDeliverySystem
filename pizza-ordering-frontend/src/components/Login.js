import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';  // Import useNavigate
import '../styles/Login.css';

const Login = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  const [loading, setLoading] = useState(false);  // Added loading state
  const [error, setError] = useState('');  // For displaying error messages
  const navigate = useNavigate();  // Initialize the navigate function

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);  // Set loading to true while the request is being made
    setError('');  // Clear any previous errors
    axios
      .post('http://localhost:8080/api/auth/login', formData)
      .then((response) => {
        const userId = response.data.userId;  // Assuming the userId is in response.data.userId
        if (userId) {
          localStorage.setItem('userId', userId);  // Store the userId in localStorage
          console.log(localStorage.getItem('userId'));  // Log the userId to confirm it's stored correctly
          alert('Login successful!');
          // Navigate to the menu page after successful login
          navigate('/menu');
        } else {
          setError('No userId received from server');
        }
      })
      .catch((error) => {
        console.error('There was an error!', error);
        setError('Login failed! Please check your username and password.');  // Show user-friendly error message
      })
      .finally(() => {
        setLoading(false);  // Reset loading state after request
      });
  };
  

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" disabled={loading}>Login</button>
      </form>
      {loading && <p>Loading...</p>}  {/* Show loading text when submitting */}
      {error && <p className="error">{error}</p>}  {/* Show error message if login fails */}
    </div>
  );
};

export default Login;
