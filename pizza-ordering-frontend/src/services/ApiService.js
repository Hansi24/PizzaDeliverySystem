// services/ApiService.js
import axios from 'axios';

const ApiService = {
  getPizzas: async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/pizzas');
      return response.data;
    } catch (error) {
      console.error('Error fetching pizzas:', error);
      return [];
    }
  },
  // Other API methods can go here
};

export default ApiService;
