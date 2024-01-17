import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { UserProvider } from './UserContext';
import Login from './Login';
import './App.css';
import Menu from './Menu';
import FlightForm from './FlightForm';
import Checkout from './Checkout';
import Success from './Success';
import RegisteredUsers from './RegisteredUsers';
import BrowseAircraft from './BrowseAircraft';
import BrowseFlights from './BrowseFlights';
import ModifyDestinations from './ModifyDestinations';
import Register from './Register';
import PassengerList from './PassengerList';
import FindTicket from './FindTicket';

const rootElement = document.getElementById('root');

ReactDOM.createRoot(rootElement).render(
  <React.StrictMode>
    <UserProvider>
      <Router>
        <Routes>
          <Route path="/menu" element={<Menu />} />
          <Route path="/menu/flightform" element={<FlightForm />} />
          <Route path="/menu/flightform/checkout" element={<Checkout />} />
          <Route path="/menu/success" element={<Success />} />
          <Route path="/menu/browse-flights" element={<BrowseFlights />} />
          <Route path="/menu/browse-aircraft" element={<BrowseAircraft />} />
          <Route path="/menu/modify-destinations" element={<ModifyDestinations />} />
          <Route path="/menu/registered-users" element={<RegisteredUsers />} />
          <Route path="/menu/register" element={<Register />} />
          <Route path="/menu/passengerlist" element={<PassengerList />} />
          <Route path="/menu/find-ticket" element={<FindTicket />} />
          <Route path="/" element={<Login />} />
        </Routes>
      </Router>
    </UserProvider>
  </React.StrictMode>,
);
