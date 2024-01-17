import React, { useState, useEffect } from 'react';

const ModifyFlightForm = ({ flight, onSubmit, onCancel }) => {
  const [modifiedFlightData, setModifiedFlightData] = useState({
    flightnumber: (flight.flightnumber || '').toString(),
    origin: flight.origin.cityName || '',
    destination: (flight.destination.cityName || '').toString(),
    departureTime: {
      year: flight.departureTime.year || '',
      month: flight.departureTime.month || '',
      day: flight.departureTime.day || '',
      hour: flight.departureTime.hour || '',
      minute: flight.departureTime.minute || '',
      second: flight.departureTime.second || '',
    },
    arrivalTime: {
      year: flight.arrivalTime.year || '',
      month: flight.arrivalTime.month || '',
      day: flight.arrivalTime.day || '',
      hour: flight.arrivalTime.hour || '',
      minute: flight.arrivalTime.minute || '',
      second: flight.arrivalTime.second || '',
    },
    aircraft: flight.aircraft.id || '',
  });

  const handleModifiedFlightChange = (event) => {
    const { name, value } = event.target;
  
    if (name.startsWith('departureTime') || name.startsWith('arrivalTime')) {
      const [time, field] = name.split('.');
      setModifiedFlightData((prevData) => ({
        ...prevData,
        [time]: {
          ...prevData[time],
          [field]: parseInt(value, 10),
        },
      }));
    } else {
      setModifiedFlightData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    };
  };

  const submitModifiedFlight = async () => {
    try {
      const modifiedDataWithZeroSeconds = {
        ...modifiedFlightData,
        departureTime: [
          modifiedFlightData.departureTime.year,
          modifiedFlightData.departureTime.month,
          modifiedFlightData.departureTime.day,
          modifiedFlightData.departureTime.hour,
          modifiedFlightData.departureTime.minute,
          0,
        ],
        arrivalTime: [
          modifiedFlightData.arrivalTime.year,
          modifiedFlightData.arrivalTime.month,
          modifiedFlightData.arrivalTime.day,
          modifiedFlightData.arrivalTime.hour,
          modifiedFlightData.arrivalTime.minute,
          0,
        ],
      };
  
      console.log(modifiedDataWithZeroSeconds);
      await onSubmit(modifiedDataWithZeroSeconds);
    } catch (error) {
      console.error('Error submitting modified flight:', error);
    }
  };

  return (
    <div>
      <h3>Modify Flight</h3>
      <label>Flight Number: {modifiedFlightData.flightnumber}</label>
      <br />
      <label>Origin: </label>
      <input type="text" name="origin" value={modifiedFlightData.origin} onChange={handleModifiedFlightChange} />
      <br />
      <label>Destination: </label>
      <input
        type="text"
        name="destination"
        value={modifiedFlightData.destination}
        onChange={handleModifiedFlightChange}
      />
      <br />
      <label>Departure Time: (Year/Month/Day/Hour/Minute) </label>
      <input
        type="text"
        name="departureTime.year"
        value={modifiedFlightData.departureTime.year}
        onChange={handleModifiedFlightChange}
        style={{ width: '10%'}}
      />
      <input
        type="text"
        name="departureTime.month"
        value={modifiedFlightData.departureTime.month}
        onChange={handleModifiedFlightChange}
        style={{ width: '5%'}}
      />
      <input
        type="text"
        name="departureTime.day"
        value={modifiedFlightData.departureTime.day}
        onChange={handleModifiedFlightChange}
        style={{ width: '5%' }}
      />
      <input
        type="text"
        name="departureTime.hour"
        value={modifiedFlightData.departureTime.hour}
        onChange={handleModifiedFlightChange}
        style={{ width: '5%' }}
      />
      <input
        type="text"
        name="departureTime.minute"
        value={modifiedFlightData.departureTime.minute}
        onChange={handleModifiedFlightChange}
        style={{ width: '5%' }}
      />
      <br />
      <label>Arrival Time: (Year/Month/Day/Hour/Minute) </label>
      <input
        type="text"
        name="arrivalTime.year"
        value={modifiedFlightData.arrivalTime.year}
        onChange={handleModifiedFlightChange}
        style={{ width: '10%' }}
      />
      <input
        type="text"
        name="arrivalTime.month"
        value={modifiedFlightData.arrivalTime.month}
        onChange={handleModifiedFlightChange}
        style={{ width: '5%' }}
      />
      <input
        type="text"
        name="arrivalTime.day"
        value={modifiedFlightData.arrivalTime.day}
        onChange={handleModifiedFlightChange}
        style={{ width: '5%' }}
      />
      <input
        type="text"
        name="arrivalTime.hour"
        value={modifiedFlightData.arrivalTime.hour}
        onChange={handleModifiedFlightChange}
        style={{ width: '5%' }}
      />
      <input
        type="text"
        name="arrivalTime.minute"
        value={modifiedFlightData.arrivalTime.minute}
        onChange={handleModifiedFlightChange}
        style={{ width: '5%' }}
      />
      <br />
      <label>Aircraft ID: </label>
      <input
        type="text"
        name="aircraft"
        value={modifiedFlightData.aircraft}
        onChange={handleModifiedFlightChange}
        style={{ width: '10%' }}
      />
      <br />
      <button onClick={submitModifiedFlight}>Submit Modifications</button>
      <button onClick={onCancel}>Cancel</button>
    </div>
  );
};

export default ModifyFlightForm;
