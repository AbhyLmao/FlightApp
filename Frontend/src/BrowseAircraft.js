import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useUser } from './UserContext';

const BrowseAircraft = () => {
  const { handleBack } = useUser();
  const [aircraftOwned, setAircraftOwned] = useState([]);
  const [newAircraftName, setNewAircraftName] = useState('');
  const [removeAircraftId, setRemoveAircraftId] = useState('');

  useEffect(() => {
    fetchAircraftOwned();
  }, []);

  const fetchAircraftOwned = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/menu/browseAircraftOwned');
      setAircraftOwned(response.data);
    } catch (error) {
      console.error('Error fetching aircraft owned:', error);
    }
  };

  const handleAddAircraft = async () => {
    try {
      await axios.post('http://localhost:8080/api/aircraft/addAircraft', { name: newAircraftName });
      fetchAircraftOwned();
      setNewAircraftName('');
    } catch (error) {
      console.error('Error adding aircraft:', error);
    }
  };

  const handleRemoveAircraft = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/aircraft/removeAircraft/${removeAircraftId}`);
      fetchAircraftOwned();
      setRemoveAircraftId('');
    } catch (error) {
      console.error('Error removing aircraft:', error);
    }
  };

  const groupedAircraft = {};
  aircraftOwned.forEach(aircraft => {
    if (groupedAircraft[aircraft.name]) {
      groupedAircraft[aircraft.name].push(aircraft.id);
    } else {
      groupedAircraft[aircraft.name] = [aircraft.id];
    }
  });

  return (
    <div>
      <h2>Aircraft Owned</h2>
      <ul>
        {Object.entries(groupedAircraft).map(([modelName, ids]) => (
          <li key={modelName}>
            <strong>{modelName}:</strong>
            <ul>
              {ids.map(id => (
                <li key={id}>Aircraft ID: {id}</li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
      <div>
        <input
          type="text"
          placeholder="Enter aircraft name to add"
          value={newAircraftName}
          onChange={(e) => setNewAircraftName(e.target.value)}
        />
        <button onClick={handleAddAircraft}>Add Aircraft</button>
      </div>
      <br/>
      <div>
        <input
          type="text"
          placeholder="Enter aircraft ID to remove"
          value={removeAircraftId}
          onChange={(e) => setRemoveAircraftId(e.target.value)}
        />
        <button onClick={handleRemoveAircraft}>Remove Aircraft</button>
      </div>
      <br/>
      <button onClick={handleBack}>Back</button>
    </div>
  );
};

export default BrowseAircraft;
