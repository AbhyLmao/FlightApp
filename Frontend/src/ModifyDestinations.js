import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useUser } from './UserContext';

const ModifyDestinations = () => {
  const { handleBack } = useUser();
  const [destinations, setDestinations] = useState([]);
  const [newCityName, setNewCityName] = useState('');
  const [newLatitude, setNewLatitude] = useState('');
  const [newLongitude, setNewLongitude] = useState('');
  const [removeCityName, setRemoveCityName] = useState('');

  useEffect(() => {
    fetchDestinations();
  }, []);

  const fetchDestinations = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/menu/browseFlightDestinations');
      setDestinations(response.data);
    } catch (error) {
      console.error('Error fetching destinations:', error);
    }
  };

  const handleAddDestination = async () => {
    try {
      await axios.post('http://localhost:8080/api/destinations/addDestination', {
        cityName: newCityName,
        latitude: newLatitude,
        longitude: newLongitude,
      });
      fetchDestinations();
      setNewCityName('');
      setNewLatitude('');
      setNewLongitude('');
    } catch (error) {
      console.error('Error adding destination:', error);
    }
  };

  const handleRemoveDestination = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/destinations/removeDestination/${removeCityName}`);
      fetchDestinations();
      setRemoveCityName('');
    } catch (error) {
      console.error('Error removing destination:', error);
    }
  };

  return (
    <div>
      <h2>Modify Destinations</h2>
      <ul>
        {destinations.map((location, index) => (
          <li key={index}>
            <strong>City Name:</strong> {location.cityName} -&nbsp;
            <strong>Coordinates:</strong> ({location.latitude}, {location.longitude})
          </li>
        ))}
      </ul>
      <div>
        <input
          type="text"
          placeholder="Enter city name"
          value={newCityName}
          onChange={(e) => setNewCityName(e.target.value)}
        />
        <input
          type="text"
          placeholder="Enter latitude"
          value={newLatitude}
          onChange={(e) => setNewLatitude(e.target.value)}
        />
        <input
          type="text"
          placeholder="Enter longitude"
          value={newLongitude}
          onChange={(e) => setNewLongitude(e.target.value)}
        />
        <button onClick={handleAddDestination}>Add Destination</button>
      </div>
      <br/>
      <div>
        <input
          type="text"
          placeholder="Enter city name to remove"
          value={removeCityName}
          onChange={(e) => setRemoveCityName(e.target.value)}
        />
        <button onClick={handleRemoveDestination}>Remove Destination</button>
      </div>
      <br/>
      <button onClick={handleBack}>Back</button>
    </div>
  );
};

export default ModifyDestinations;