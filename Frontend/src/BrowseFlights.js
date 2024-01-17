import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useUser } from './UserContext';
import ModifyFlightForm from './ModifyFlightForm';

const BrowseFlights = () => {
  const { userType, handleBack } = useUser();
  const [flights, setFlights] = useState([]);
  const [originalFlights, setOriginalFlights] = useState([]);
  const [filters, setFilters] = useState({
    origin: '',
    destination: '',
    departureYearMonth: '',
    departureDate: '',
  });
  const [newFlight, setNewFlight] = useState({
    flightNumber: '',
    origin: '',
    destination: '',
    departureTime: '',
    arrivalTime: '',
    aircraftID: '',
  });
  const [modifiedFlight, setModifiedFlight] = useState(null);

  useEffect(() => {
    const fetchFlights = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/flight/flightList');
        setFlights(response.data);
        setOriginalFlights(response.data);
      } catch (error) {
        console.error('Error fetching flights:', error);
      }
    };

    fetchFlights();
  }, []);

  const applyFilters = () => {
    let filteredFlights = originalFlights.filter((flight) => {
      const departureTime = new Date(
        flight.departureTime.year,
        flight.departureTime.month - 1,
        flight.departureTime.day,
        flight.departureTime.hour,
        flight.departureTime.minute,
        flight.departureTime.second
      );
      const departureYearMonth =
        filters.departureYearMonth === '' ||
        `${departureTime.getFullYear()}-${('0' + (departureTime.getMonth() + 1)).slice(-2)}` ===
          filters.departureYearMonth;
      const departureDate =
        filters.departureDate === '' || departureTime.getDate() === parseInt(filters.departureDate, 10);

      return (
        flight.origin.cityName.toLowerCase().includes(filters.origin.toLowerCase()) &&
        flight.destination.cityName.toLowerCase().includes(filters.destination.toLowerCase()) &&
        departureYearMonth &&
        departureDate
      );
    });

    setFlights(filteredFlights);
  };

  const handleFilterChange = (event) => {
    const { name, value } = event.target;
    setFilters((prevFilters) => ({
      ...prevFilters,
      [name]: value,
    }));
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setNewFlight((prevFlight) => ({
      ...prevFlight,
      [name]: value,
    }));
  };

  const addFlight = async () => {
    try {
      if (
        !newFlight.flightNumber ||
        !newFlight.origin ||
        !newFlight.destination ||
        !newFlight.departureTime ||
        !newFlight.arrivalTime ||
        !newFlight.aircraftID
      ) {
        console.error('All fields must be filled in.');
        return;
      }

      const departureTime = new Date(newFlight.departureTime);
      const arrivalTime = new Date(newFlight.arrivalTime);

      const flightData = {
        flightNumber: newFlight.flightNumber,
        origin: newFlight.origin,
        destination: newFlight.destination,
        departureTime: [
          departureTime.getFullYear(),
          departureTime.getMonth() + 1,
          departureTime.getDate(),
          departureTime.getHours(),
          departureTime.getMinutes(),
          departureTime.getSeconds(),
        ],
        arrivalTime: [
          arrivalTime.getFullYear(),
          arrivalTime.getMonth() + 1,
          arrivalTime.getDate(),
          arrivalTime.getHours(),
          arrivalTime.getMinutes(),
          arrivalTime.getSeconds(),
        ],
        aircraft: parseInt(newFlight.aircraftID, 10),
      };
      

      console.log(flightData);

      await axios.post('http://localhost:8080/api/flight/addFlight', flightData);
      const response = await axios.get('http://localhost:8080/api/flight/flightList');
      setFlights(response.data);
      setOriginalFlights(response.data);
      setNewFlight({
        flightNumber: '',
        origin: '',
        destination: '',
        departureTime: '',
        arrivalTime: '',
        aircraftID: '',
      });
    } catch (error) {
      console.error('Error adding flight:', error);
    }
  };

  const removeFlight = async (flightNumber) => {
    try {
      await axios.delete(`http://localhost:8080/api/flight/removeFlight?flightNumber=${flightNumber}`);
      const response = await axios.get('http://localhost:8080/api/flight/flightList');
      setFlights(response.data);
      setOriginalFlights(response.data);
    } catch (error) {
      console.error('Error removing flight:', error);
    }
  };

  const modifyFlight = async (modifiedFlightData) => {
    try {
      await axios.put('http://localhost:8080/api/flight/modifyFlight', modifiedFlightData);
      const response = await axios.get('http://localhost:8080/api/flight/flightList');
      setFlights(response.data);
      setOriginalFlights(response.data);
      setModifiedFlight(null);
    } catch (error) {
      console.error('Error modifying flight:', error);
    }
  };

  return (
    <div>
      <h2>Browse Flights</h2>
      <div>
        <label>Origin: </label>
        <input type="text" name="origin" value={filters.origin} onChange={handleFilterChange} />
        <br/>
        <label>Destination: </label>
        <input type="text" name="destination" value={filters.destination} onChange={handleFilterChange} />
        <br/>
        <label>Departure Year/Month: </label>
        <input type="text" name="departureYearMonth" placeholder="YYYY-MM" value={filters.departureYearMonth} onChange={handleFilterChange} />
        <br/>
        <label>Departure Date: </label>
        <input type="text" name="departureDate" placeholder="DD" value={filters.departureDate} onChange={handleFilterChange} />
        <br/>
        <button onClick={applyFilters}>Apply Filters</button>
      </div>
      {userType === 'admin' && (
        <div>
          <h3>Add New Flight</h3>
          <label>Flight Number: </label>
          <input type="text" name="flightNumber" value={newFlight.flightNumber} onChange={handleInputChange} />
          <br />
          <label>Origin: </label>
          <input type="text" name="origin" value={newFlight.origin} onChange={handleInputChange} />
          <br />
          <label>Destination: </label>
          <input type="text" name="destination" value={newFlight.destination} onChange={handleInputChange} />
          <br />
          <label>Departure Time: </label>
          <input type="text" name="departureTime" placeholder="YYYY-MM-DD hh:mm:ss" value={newFlight.departureTime} onChange={handleInputChange} />
          <br />
          <label>Arrival Time: </label>
          <input type="text" name="arrivalTime" placeholder="YYYY-MM-DD hh:mm:ss" value={newFlight.arrivalTime} onChange={handleInputChange} />
          <br />
          <label>Aircraft ID: </label>
          <input type="text" name="aircraftID" value={newFlight.aircraftID} onChange={handleInputChange} />
          <br />
          <button onClick={addFlight}>Add Flight</button>
        </div>
      )}
      <ul>
        {flights.map((flight) => (
          <li key={flight.flightnumber}>
            Flight Number: {flight.flightnumber}<br />
            {flight.origin.cityName} → {flight.destination.cityName}<br />
            Departure Time: {new Date(flight.departureTime.year, flight.departureTime.month - 1, flight.departureTime.day, flight.departureTime.hour, flight.departureTime.minute, flight.departureTime.second).toLocaleString()}<br />
            Arrival Time: {new Date(flight.arrivalTime.year, flight.arrivalTime.month - 1, flight.arrivalTime.day, flight.arrivalTime.hour, flight.arrivalTime.minute, flight.arrivalTime.second).toLocaleString()}<br />
            Aircraft ID: {flight.aircraft.id}<br />
            Aircraft Model: {flight.aircraft.name}<br />
            {userType === 'admin' && (
              <div>
                <button onClick={() => removeFlight(flight.flightnumber)}>Remove Flight</button>
                <button onClick={() => setModifiedFlight(flight)}>Modify Flight</button>
              </div>
            )}
            <hr />
          </li>
        ))}
      </ul>
      {modifiedFlight && (
        <ModifyFlightForm flight={modifiedFlight} onSubmit={modifyFlight} onCancel={() => setModifiedFlight(null)} />
      )}
      <br />
      <button onClick={handleBack}>Back</button>
    </div>
  );
};

export default BrowseFlights;
