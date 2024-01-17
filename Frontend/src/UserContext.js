import { createContext, useContext, useState, useEffect } from 'react';

const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [userType, setUserType] = useState(null);
  const [username, setUsername] = useState(null);

  useEffect(() => {
    const storedUserType = localStorage.getItem('userType');
    const storedUsername = localStorage.getItem('username');
    
    if (storedUserType) {
      setUserType(storedUserType);
    } else {
      setUserType(null);
    }

    if (storedUsername) {
      setUsername(storedUsername);
    }
  }, []);

  const login = (type, name) => {
    setUserType(type);
    setUsername(name);
    localStorage.setItem('userType', type);
    localStorage.setItem('username', name);
  };

  const logout = () => {
    setUserType(null);
    setUsername(null);
    localStorage.removeItem('userType');
    localStorage.removeItem('username');
    window.location.href = '/';
  };

  const handleBack = () => {
    window.history.back();
  };

  return (
    <UserContext.Provider value={{ userType, username, login, logout, handleBack, setUsername }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUser = () => {
  return useContext(UserContext);
};
