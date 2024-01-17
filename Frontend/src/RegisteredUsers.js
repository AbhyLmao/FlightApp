import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useUser } from './UserContext';

const RegisteredUsers = () => {
  const { handleBack } = useUser();
    const [registeredUsers, setRegisteredUsers] = useState([]);

    useEffect(() => {
        fetchRegisteredUsers();
    }, []);

    const fetchRegisteredUsers = async () => {
      try {
          const response = await axios.get('http://localhost:8080/api/menu/printRegisteredUsers');
          setRegisteredUsers(response.data);
      } catch (error) {
          console.error('Error fetching registered users:', error);
      }
    };

    return (
      <div>
        <h2>Registered Users</h2>
        <ul>
          {registeredUsers.map(user => (
            <li key={user.email}>
              <strong>{user.email}</strong>
              <ul>
                <li>Email: {user.email}</li>
                <li>First Name: {user.firstName}</li>
                <li>Last Name: {user.lastName}</li>
                <li>Phone Number: {user.phoneNumber}</li>
                <li>Points: {user.points}</li>
              </ul>
              <br />
            </li>
          ))}
        </ul>
        <button onClick={handleBack}>Back</button>
      </div>
  );
};

export default RegisteredUsers;
