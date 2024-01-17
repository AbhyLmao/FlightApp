import React, { useState } from 'react';
import axios from 'axios';
import { useUser } from './UserContext';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loginError, setLoginError] = useState('');
  const { login, userType, logout } = useUser();
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/login', {
        username,
        password,
      });
  
      if (response && response.status === 200) {
        const { userType, username, email } = response.data;
        console.log(username);
        let loginIdentifier;
        
        if (userType === 'registered') {
          loginIdentifier = email;
        } else {
          loginIdentifier = username;
        }
  
        login(userType, loginIdentifier);
        navigate('/menu');
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setLoginError("Invalid username or password.");
      } else {
        setLoginError('Backend not connected. Please check the Spring Boot application.');
      }
    }
  };
  
  const handleContinueAsGuest = () => {
    login('guest');
    navigate('/menu');
  };

  if (userType) {
    return (
      <div>
        <h2>User Details</h2>
        <p>Welcome, {userType}</p>
        <button onClick={() => navigate('/menu')}>Menu</button>
        <br />
        <button onClick={logout}>Logout</button>
      </div>
    );
  }

  return (
    <div>
      <h2>Login</h2>
      {loginError && <p style={{ color: 'red' }}>{loginError}</p>}
      <label>
        Username:
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </label>
      <br />
      <label>
        Password:
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </label>
      <br />
      <button onClick={handleLogin}>Login</button>
      <button onClick={handleContinueAsGuest}>Continue as Guest</button>
    </div>
  );
};

export default Login;
