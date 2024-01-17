import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useUser } from './UserContext';
import { useNavigate } from 'react-router-dom';



const FlightForm = () => {
  const { handleBack } = useUser();
  const [allFlights, setAllFlights] = useState([]);
  const [flightNumber, setFlightNumber] = useState('');
  const [selectedFlight, setSelectedFlight] = useState(null);
  const [ seatArray, setSeatArray ] = useState([]);
  const [seatNumber, setSeatNumber] = useState('');
  const [insurancePolicy, setInsurancePolicy] = useState(false);
  const [fetchFlightsError, setFetchFlightsError] = useState('');
  const [checkoutError, setCheckoutError] = useState('');
  const navigate = useNavigate();

  function Seat({ seatNumber, availability }) {
    return (
      <>
      {availability === true ? 
      <button style={{"backgroundColor": "AliceBlue"}} onClick={()=>handleFlight(seatNumber)}>
        {seatNumber}
      </button>:
      <button style={{"backgroundColor": "DarkGray"}} disabled={"disabled"} >
        {seatNumber}
      </button>
      }
      </>
    );
  }

  useEffect(() => {
    const fetchFlights = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/flight/flightList');
        setAllFlights(response.data);
      } catch (error) {
        console.error('Error fetching flights:', error.message);
        setFetchFlightsError('Backend not connected. Please check the Spring Boot application.');
      }
    };

    fetchFlights();
  }, []);

  useEffect(() => {
    const selected = allFlights.find((flight) => flight.flightnumber === Number(flightNumber));
    setSelectedFlight(selected);
  }, [flightNumber, allFlights]);


  const handleFlightSelection = async (e) => {
    const flightNumber = Number(e.target.value);
    setFlightNumber(flightNumber);
    try { 
        const response = await axios.get('http://localhost:8080/api/seat/getSeats', {
        params: { flightNumber }, 
      });
      setSeatArray(response.data);
    }
    catch (error){
      console.error('Error initiating checkout:', error);
    }
  }

  const handleFlight = async (seatNumber) => {
    setSeatNumber(seatNumber);
    try {
      const flightDetailsResponse = await axios.get('http://localhost:8080/api/flight/flightDetails', {
        params: { flightNumber },
      });
  
      const flightDetails = flightDetailsResponse.data;
      console.log(flightNumber);
      console.log(seatNumber);
      console.log(insurancePolicy);
      console.log(flightDetails);
      const checkoutDetails = {
        flightNumber,
        seatNumber,
        insurancePolicy,
        flightDetails,
      };
  
      navigate('/menu/flightform/checkout', { state: { checkoutDetails } });
    } catch (error) {
      console.error('Error initiating checkout:', error.message);
      setCheckoutError('Backend not connected. Please check the Spring Boot application.');
    }
  };

  return (
    <div>
      <h2>Select Flight</h2>
      {fetchFlightsError && <p style={{ color: 'red' }}>{fetchFlightsError}</p>}
      <label>
        Flight Number:
        <select value={flightNumber} onChange={(e) => handleFlightSelection(e)}>
          <option value="">Select a flight</option>
          {allFlights.map((flight) => (
            <option key={flight.flightnumber} value={flight.flightnumber}>
              {`${flight.origin.cityName} to ${flight.destination.cityName}`}
            </option>
          ))}
        </select>
      </label>

      {selectedFlight && (
        <div>
          <h3>Selected Flight Details</h3>
          <p>Departure: {selectedFlight.origin.cityName}</p>
          <p>Destination: {selectedFlight.destination.cityName}</p>
          <p>Aircraft: {selectedFlight.aircraft.name}</p>
          <p>Departure Time: {selectedFlight.departureTime.year}-{selectedFlight.departureTime.month}-{selectedFlight.departureTime.day} {selectedFlight.departureTime.hour}:{selectedFlight.departureTime.minute}</p>
          <label>
            Purchase Insurance:
            <input
              type="checkbox"
              checked={insurancePolicy}
              onChange={() => setInsurancePolicy(!insurancePolicy)}
            />
          </label>
          <p>Select seats below</p>
          {seatArray.map((seat)=> 
            <Seat key={seat.seatNumber} seatNumber={seat.seatNumber} availability={seat.availability} />
          )}
          <br />   
        </div>
      )}
      {checkoutError && <p style={{ color: 'red' }}>{checkoutError}</p>}
      <br />
      <br/>
      <button onClick={handleBack}>Back</button>
    </div>
  );
};

export default FlightForm;
