import React from 'react';
import Login from './Login';
import './App.css';
import { UserProvider } from './UserContext';

function App() {
  return (
    <UserProvider>
      <div className="App">
        <header className="App-header">
          <Login />
        </header>
      </div>
    </UserProvider>
  );
}

export default App;
