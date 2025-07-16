import React, { useState } from 'react';

const RegisterForm = () => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    displayName: ''
  });

  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');

    const { username, email, password, displayName } = formData;

    try {
      const response = await fetch("http://localhost:8081/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, email, password, displayName })
      });

      if (response.ok) {
        setSuccess("Registration successful!");
        setFormData({ username: '', email: '', password: '', displayName: '' });
      } else {
        const data = await response.text();
        setError(`Error: ${data}`);
      }
    } catch (err) {
      setError(`Network error: ${err.message}`);
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: 'auto', paddingTop: '2rem' }}>
      <h2 style={{ textAlign: 'center' }}>Register</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="displayName"
          placeholder="Display Name"
          value={formData.displayName}
          onChange={handleChange}
          required
          style={inputStyle}
        />
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={formData.username}
          onChange={handleChange}
          required
          style={inputStyle}
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
          style={inputStyle}
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          required
          style={inputStyle}
        />
        <button type="submit" style={buttonStyle}>Register</button>
      </form>
      {error && <p style={{ color: 'red', marginTop: '1rem' }}>{error}</p>}
      {success && <p style={{ color: 'green', marginTop: '1rem' }}>{success}</p>}
    </div>
  );
};

const inputStyle = {
  display: 'block',
  width: '100%',
  padding: '10px',
  marginBottom: '1rem',
  borderRadius: '4px',
  border: '1px solid #ccc'
};

const buttonStyle = {
  width: '100%',
  padding: '10px',
  backgroundColor: '#007bff',
  color: 'white',
  border: 'none',
  borderRadius: '4px',
  cursor: 'pointer'
};

export default RegisterForm;
