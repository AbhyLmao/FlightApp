import React, { useState, useEffect } from 'react';
import { useUser } from './UserContext';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Menu = () => {
  const { userType, logout, handleBack } = useUser();
  const [menuOptions, setMenuOptions] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMenuOptions = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/menu/show?userType=${userType}`);
        setMenuOptions(response.data);
      } catch (error) {
        console.error('Error fetching menu options:', error);
      }
    };

    fetchMenuOptions();
  }, [userType]);

  const handleOptionClick = (option) => {
    console.log(`Clicked option: ${option}`);
    switch (option.toLowerCase()) {
      case 'browse/filter list of flights':
        navigate('/menu/browse-flights')
        break;
      case 'select flight':
        navigate('/menu/flightform');
      case 'purchase ticket':
        navigate('/menu/flightform');
        break;
      case 'print registered users':
        navigate('/menu/registered-users');
        break;
      case 'browse aircraft owned':
        navigate('/menu/browse-aircraft');
        break;
      case 'modify possible destinations':
        navigate('/menu/modify-destinations');
        break;
      case 'register for membership':
        navigate('/menu/register');
        break;
      case 'find ticket':
        window.location.href = '/menu/find-ticket';
        break;
      case 'show passenger list':
        window.location.href = '/menu/passengerlist';
        break;
      default:
        break;
    }
  };

  return (
    <div>
      <h2>Menu</h2>
      <p>User Type: {userType}</p>
      {menuOptions.map((option, index) => (
        <React.Fragment key={index}>
          <button onClick={() => handleOptionClick(option)}>
            {option}
          </button>
          <br/>
        </React.Fragment>
      ))}
      <br/>
      <button onClick={handleBack}>Back</button>
      <br/>
      <button onClick={logout}>Logout</button>
    </div>
  );
};

export default Menu;
