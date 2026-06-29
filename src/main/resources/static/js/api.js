// API Configuration and Helper Class
const API_BASE_URL = 'http://localhost:5454';

const api = {
    getToken() {
        return localStorage.getItem('jwt');
    },
    
    setToken(token) {
        localStorage.setItem('jwt', token);
    },
    
    getHeaders() {
        const token = this.getToken();
        return {
            'Content-Type': 'application/json',
            ...(token && { 'Authorization': `Bearer ${token}` })
        };
    },
    
    async get(endpoint) {
        try {
            const response = await axios.get(`${API_BASE_URL}${endpoint}`, {
                headers: this.getHeaders()
            });
            return response.data;
        } catch (error) {
            console.error('GET Error:', error);
            throw error;
        }
    },
    
    async post(endpoint, data) {
        try {
            const response = await axios.post(`${API_BASE_URL}${endpoint}`, data, {
                headers: this.getHeaders()
            });
            return response.data;
        } catch (error) {
            console.error('POST Error:', error);
            throw error;
        }
    },
    
    async put(endpoint, data = {}) {
        try {
            const response = await axios.put(`${API_BASE_URL}${endpoint}`, data, {
                headers: this.getHeaders()
            });
            return response.data;
        } catch (error) {
            console.error('PUT Error:', error);
            throw error;
        }
    },
    
    async delete(endpoint) {
        try {
            const response = await axios.delete(`${API_BASE_URL}${endpoint}`, {
                headers: this.getHeaders()
            });
            return response.data;
        } catch (error) {
            console.error('DELETE Error:', error);
            throw error;
        }
    }
};

// Check authentication on page load
window.addEventListener('load', () => {
    const token = localStorage.getItem('jwt');
    const currentPage = window.location.pathname;
    
    // If no token and not on login/signup pages, redirect to login
    if (!token && !currentPage.includes('/login') && !currentPage.includes('/signup') && !currentPage === '/') {
        window.location.href = '/login';
    }
});
