import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
  const [formData, setFormData] = useState({ username: '', password: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const response = await fetch("http://localhost:8081/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData)
      });

      if (response.ok) {
        const data = await response.json();
        // localStorage.setItem("user", JSON.stringify({ id, token, ...otherData }));
        console.log(data)
    localStorage.setItem("userId", data.userId);
        navigate("/chat");
      } else {
        const errMsg = await response.text();
        setError(`Login failed: ${errMsg}`);
      }
    } catch (err) {
      setError(`Network error: ${err.message}`);
    }
  };

  return (
    <div style={containerStyle}>
      <h2 style={headerStyle}>Login</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" name="username" placeholder="Username" value={formData.username} onChange={handleChange} required style={inputStyle} />
        <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required style={inputStyle} />
        <button type="submit" style={buttonStyle}>Login</button>
      </form>
      {error && <p style={{ color: 'red', marginTop: '1rem' }}>{error}</p>}
    </div>
  );
};

const containerStyle = {
  maxWidth: '400px',
  margin: 'auto',
  paddingTop: '2rem',
};

const headerStyle = {
  textAlign: 'center',
};

const inputStyle = {
  display: 'block',
  width: '100%',
  padding: '10px',
  marginBottom: '1rem',
  borderRadius: '4px',
  border: '1px solid #ccc',
};

const buttonStyle = {
  width: '100%',
  padding: '10px',
  backgroundColor: '#007bff',
  color: 'white',
  border: 'none',
  borderRadius: '4px',
  cursor: 'pointer',
};

export default LoginForm;
