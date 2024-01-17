import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useUser } from './UserContext';

function PassengerList() {
  const { handleBack } = useUser();
  const [allPassengers, setAllPassengers] = useState([]);
  const [flightNumber, setFlightNumber] = useState(1);
  const [query,setQuery] = useState('')
  
  useEffect(() => {
    // Update the URL when flightNumber changes
    setQuery(`http://localhost:8080/api/attendant/passengerlist?flightNumber=${flightNumber}`);
  }, [flightNumber]);

   const fetchPassengers = async () => {
    try {
        console.log('Data to be sent:', flightNumber);
        const response = await axios.post(query);

      console.log(response.data);
      setAllPassengers(response.data);

    } catch (error) {
    //   console.error('Error fetching passengers:', error);
      console.error(error.response.data);
      
    }
  };


  return (
    <div>
      <input
        type="text"
        value={flightNumber}
        placeholder={"Enter Flight Number"}
        onChange={(e) => setFlightNumber(e.target.value)}
        
      />
      
      <button onClick={fetchPassengers}>Fetch Passengers</button>
      <button onClick={handleBack}>Back</button>
      <h2>Passenger List</h2>

      <table>
        <tbody>
          <tr>
            <th>Flight</th>
            <th>Ticket</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Seat</th>
          </tr>
          {allPassengers.map((item, index) => (
            <tr key={index}>
              <td>{item.flightNumber}</td>
              <td>{item.ticketID}</td>
              <td>{item.firstName}</td>
              <td>{item.lastName}</td>
              <td>{item.email}</td>
              <td>{item.seatNo}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default PassengerList;
