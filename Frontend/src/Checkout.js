import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useUser } from './UserContext';
import { useLocation, useNavigate } from 'react-router-dom';

const Checkout = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { checkoutDetails } = location.state || {};
  const { userType, username, handleBack, setUsername } = useUser();
  const storedUsername = localStorage.getItem('username');
  
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [ cardNumber, setCardNumber ] = useState('');
  const [ cvv, setCvv ] = useState('');
  const [ monthYear, setMonthYear ] = useState('');
  const [ isValid, setIsValid] = useState(true);

  useEffect(() => {
    if (userType === 'registered' && username) {
      fetchUserDetails();
    }
  }, [userType, username]);

  useEffect(() => {
    if (storedUsername) {
      setUsername(storedUsername);
    }
  }, []);

  const validateForm = () => {
    const nameRegex = /^[a-zA-Z]+$/;
    const cardNumberRegex = /^[0-9]{4}\s[0-9]{4}\s[0-9]{4}\s[0-9]{4}$/;
    const cvvRegex = /^[0-9]{3}$/;
    const monthYearRegex = /^[0-9]{2}\/[0-9]{2}$/;
    const emailRegex = /^[^@\s]+@[^@\s]+$/;
    
    return (emailRegex.test(email) && 
            nameRegex.test(firstName) &&
            nameRegex.test(lastName) &&
            cardNumberRegex.test(cardNumber) &&
            cvvRegex.test(cvv) &&
            monthYearRegex.test(monthYear)
    );
}


  const fetchUserDetails = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/user/details', {
        params: { username: storedUsername },
        headers: { 'Content-Type': 'application/json' },
      });
      console.log(response.data);

      const { firstName, lastName, email } = response.data;
      setFirstName(firstName);
      setLastName(lastName);
      setEmail(email);
    } catch (error) {
      console.error('Error fetching user details:', error.message);
    }
  };

  const handleCheckout = async () => {
    if (validateForm() === false) {
      setIsValid(false);
      return;
  }
    try {
      const response = await axios.post('http://localhost:8080/api/payment/start', null, {
        params: {
          flightNumber: checkoutDetails.flightNumber,
          seatNumber: checkoutDetails.seatNumber,
          firstName,
          lastName,
          email,
        },
      });

    } catch (error) {
      console.error('Error during checkout:', error.message);
    }
    try {
      await axios.post("http://localhost:8080/api/seat/occupy-seat", null, {
          params: {flightNumber: checkoutDetails.flightNumber,
                  seatNumber: checkoutDetails.seatNumber}
      });
    }
    catch (error) {

    } 
  navigate('/menu/success');
  };

  return (
    <div>
      <h2>Checkout</h2>
      <label>
        First Name:
        <input
          type="text"
          value={firstName}
          onClick={() => setIsValid(true)}
          onChange={(e) => setFirstName(e.target.value)}
          disabled={userType === 'registered'}
        />
      </label>
      <br />
      <label>
        Last Name:
        <input
          type="text"
          value={lastName}
          onClick={() => setIsValid(true)}
          onChange={(e) => setLastName(e.target.value)}
          disabled={userType === 'registered'}
        />
      </label>
      <br />
      <label>
        Email:
        <input
          type="email"
          value={email}
          onClick={() => setIsValid(true)}
          onChange={(e) => setEmail(e.target.value)}
          disabled={userType === 'registered'}
        />
      </label>
      <br />
      <label>
        Card Number
        <input
          type="text"
          value={cardNumber}
          onClick={() => setIsValid(true)}
          onChange={(e) => setCardNumber(e.target.value)}
        />
      </label>
      <br />
      <label>
        CVV
        <input
          type="text"
          value={cvv}
          onClick={() => setIsValid(true)}
          onChange={(e) => setCvv(e.target.value)}
        />
      </label>
      <br /><label>
        MM/YY
        <input
          type="text"
          value={monthYear}
          onClick={() => setIsValid(true)}
          onChange={(e) => setMonthYear(e.target.value)}
        />
      </label>
      <br />
      {isValid? <></>:
      <div>
        <p>Please make sure that the email address contains '@' symbol in the middle</p>
        <p>Please make sure that your first and last name only contain letters</p>
        <p>Please make sure that your phone number is in the form of xxx-xxx-xxxx</p>
        <p>Please make sure that your card number is 16 digits in the form of xxxx xxxx xxxx xxxx</p>
        <p>Please make sure that your CVV is 3 digits long</p>
        <p>Please make sure that your Month/Year is in the form of MM/YY</p>
      </div>
      }
      <button onClick={handleCheckout}>Complete Checkout</button>
      <br/>
      <button onClick={handleBack}>Back</button>
    </div>
  );
};

export default Checkout;
