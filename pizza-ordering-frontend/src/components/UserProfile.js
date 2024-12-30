import React, { useEffect, useState } from 'react';
import axios from 'axios';  // You can also use fetch, but axios simplifies HTTP requests

const UserProfile = () => {
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [points, setPoints] = useState(150);  // Example points, replace with actual points data

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    // Fetch user profile details from the API
    const fetchUserProfile = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/auth/user/${userId}`);
        setUser(response.data.user);  // Assuming response contains the user data
        setLoading(false);
      } catch (err) {
        setError('Failed to fetch user details');
        setLoading(false);
      }
    };

    fetchUserProfile();
  }, [userId]);

  if (loading) {
    return (
      <div style={styles.loaderContainer}>
        <p style={styles.loadingText}>Loading...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div style={styles.errorContainer}>
        <p style={styles.errorText}>{error}</p>
      </div>
    );
  }

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>User Profile</h2>
      {user ? (
        <div style={styles.profileCard}>
          <p style={styles.profileText}><strong>Username:</strong> {user.username}</p>
          <p style={styles.profileText}><strong>Email:</strong> {user.email}</p>
          <p style={styles.profileText}><strong>Address:</strong> {user.address}</p>
          <p style={styles.profileText}><strong>Phone Number:</strong> {user.phone}</p>
          {/* <p style={styles.profileText}><strong>POints:</strong> {user.points}</p> */}

          {/* Fancy Box for Points */}
          <div style={styles.pointsBox}>
            <h3 style={styles.pointsTitle}>Your Points:</h3>
            <p style={styles.pointsText}>{points} Points</p>
          </div>

          {/* Pizza Photos */}
          <div style={styles.pizzaGallery}>
            <h3 style={styles.pizzaGalleryTitle}>Our Pizzas</h3>
            <div style={styles.pizzaImages}>
              <img src="https://example.com/pizza1.jpg" alt="Pizza 1" style={styles.pizzaImage} />
              <img src="https://example.com/pizza2.jpg" alt="Pizza 2" style={styles.pizzaImage} />
              <img src="https://example.com/pizza3.jpg" alt="Pizza 3" style={styles.pizzaImage} />
            </div>
          </div>
        </div>
      ) : (
        <p style={styles.errorText}>User not found</p>
      )}
    </div>
  );
};

// Styles for the component
const styles = {
  container: {
    backgroundColor: '#fff',
    padding: '20px',
    borderRadius: '10px',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
    maxWidth: '600px',
    margin: 'auto',
    marginTop: '40px',
    fontFamily: 'Arial, sans-serif',
  },
  title: {
    textAlign: 'center',
    color: '#FF7043', // Light orange color
    fontSize: '24px',
    marginBottom: '20px',
  },
  profileCard: {
    backgroundColor: '#FFCCBC', // Light orange background
    padding: '20px',
    borderRadius: '8px',
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
  },
  profileText: {
    fontSize: '16px',
    color: '#333',
    marginBottom: '10px',
  },
  pointsBox: {
    backgroundColor: '#FFF3E0', // Light orange with a white background
    padding: '15px',
    borderRadius: '8px',
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
    marginTop: '20px',
    textAlign: 'center',
  },
  pointsTitle: {
    color: '#FF7043',
    fontSize: '20px',
  },
  pointsText: {
    fontSize: '24px',
    color: '#FF5722', // Darker orange for points text
    fontWeight: 'bold',
  },
  pizzaGallery: {
    marginTop: '30px',
    textAlign: 'center',
  },
  pizzaGalleryTitle: {
    fontSize: '20px',
    color: '#FF7043',
    marginBottom: '15px',
  },
  pizzaImages: {
    display: 'flex',
    justifyContent: 'space-around',
  },
  pizzaImage: {
    width: '150px',
    height: '150px',
    borderRadius: '8px',
    objectFit: 'cover',
    margin: '5px',
  },
  loadingText: {
    fontSize: '18px',
    color: '#FF7043', // Light orange color for loading text
  },
  errorContainer: {
    backgroundColor: '#FFEBEE', // Light red background for error
    padding: '20px',
    borderRadius: '8px',
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
    textAlign: 'center',
    marginTop: '40px',
  },
  errorText: {
    color: '#D32F2F', // Red color for error message
    fontSize: '18px',
  },
  loaderContainer: {
    textAlign: 'center',
    marginTop: '50px',
  },
};

export default UserProfile;
