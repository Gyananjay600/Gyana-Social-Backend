// Common JavaScript Utilities
document.addEventListener('DOMContentLoaded', () => {
    // Add sidebar nav
    addNavigationBar();
});

function addNavigationBar() {
    // Navigation is handled by Thymeleaf templates
}

// Utility function to format dates
function formatDate(date) {
    const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
    return new Date(date).toLocaleDateString('en-US', options);
}

// Utility function to format numbers
function formatNumber(num) {
    if (num >= 1000000) {
        return (num / 1000000).toFixed(1) + 'M';
    } else if (num >= 1000) {
        return (num / 1000).toFixed(1) + 'K';
    }
    return num;
}

// Local storage utilities
const storage = {
    set(key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    },
    get(key) {
        const value = localStorage.getItem(key);
        return value ? JSON.parse(value) : null;
    },
    remove(key) {
        localStorage.removeItem(key);
    },
    clear() {
        localStorage.clear();
    }
};

// Check if user is authenticated
function isAuthenticated() {
    return !!localStorage.getItem('jwt');
}

// Get current user token
function getAuthToken() {
    return localStorage.getItem('jwt');
}
