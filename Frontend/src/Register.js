import React, { useState, useEffect } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import { useUser } from './UserContext';

const Register = () => {
    const navigate = useNavigate();
    const [ email, setEmail ] = useState('');
    const [ password, setPassword ] = useState('');
    const [ firstName, setFirstName ] = useState('');
    const [ lastName, setLastName ] = useState(''); 
    const [ phoneNumber, setPhoneNumber ] = useState('');
    const [ isValid, setIsValid] = useState(true);
    const { handleBack } = useUser();
    
    const validateForm = () => {
        const nameRegex = /^[a-zA-Z]+$/;
        const phoneNumberRegex = /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/;
        const emailRegex = /^[^@\s]+@[^@\s]+$/;
        
        return (emailRegex.test(email) && 
                phoneNumberRegex.test(phoneNumber) &&
                nameRegex.test(firstName) &&
                nameRegex.test(lastName) &&
                password.length >= 6
        );
    }

    const handleRegister = async () => {    
        console.log(validateForm());   
        if (validateForm() === false) {
            setIsValid(false);
            return;
        }
        setIsValid(true);
        console.log({
            "email" : email,
            "password": password,
            "firstName": firstName,
            "lastName":lastName,
            "phoneNumber":phoneNumber
        });
        try {
            const response = await axios.post('http://localhost:8080/api/register', null, {
            params: {
                email,
                password,
                firstName,
                lastName,
                phoneNumber
                },
            });
            navigate("/menu/success")
        } catch (error) {
            console.error('Error fetching menu options:', error);
        }
    }


    return (
        <>
            <label>Email</label>
            <input type="text" onClick={()=>setIsValid(true)} value={email} onChange={(e) => setEmail(e.target.value)} />
            <label>Password</label>
            <input type="text" onClick={()=>setIsValid(true)} value={password} onChange={(e) => setPassword(e.target.value)} />
            <br></br>
            <label>First Name</label>
            <input type="text" onClick={()=>setIsValid(true)} value={firstName} onChange={(e) => setFirstName(e.target.value)} />
            <label>Last Name</label>
            <input type="text" onClick={()=>setIsValid(true)} value={lastName} onChange={(e) => setLastName(e.target.value)} />
            <label>Phone Number</label>
            <input type="text" onClick={()=>setIsValid(true)} value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />
            <br></br>
            {isValid ? <></>: 
            <div>
                <p>Please make sure that the email address contains '@' symbol in the middle</p>
                <p>Please make sure that your first and last name only contain letters</p>
                <p>Please make sure that your phone number is in the form of xxx-xxx-xxxx</p>
                <p>Please make sure that your password is at least 6 characters long</p>
            </div>
            }
            <button onClick={handleRegister}>Register</button>
            <button onClick={handleBack}>Back</button>
        </>
    );

}

export default Register;