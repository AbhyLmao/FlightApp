import React, { useState, useEffect } from 'react';
import { useUser } from './UserContext';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const FindTicket = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [ tickets, setTickets ] = useState([]);
    const [ ticketIDToRemove, setTicketIDToRemove] = useState('');
    const [ ticketsFound, setTicketsFound ] = useState(false);
    const [ seatNumber, setSeatNumber ] = useState('');
    const [ flightNumber, setFlightNumber ] = useState('');
    //mike@monsterinc.com
    function TicketItem({ ticket }) {
        return (
            <div style={{"borderStyle": 'solid'} }>
                <p>TicketID: {ticket.ticketID}</p>
                <p>Ticket Owner: {ticket.firstName} {ticket.lastName}</p>
                <p>Ticket Price: ${ticket.ticketPrice}</p>
                <p>{ticket.flight.origin.cityName} to {ticket.flight.destination.cityName}</p>
                <span>Time: {ticket.flight.departureTime.year}-{ticket.flight.departureTime.month}-{ticket.flight.departureTime.day} {ticket.flight.departureTime.hour}:{ticket.flight.departureTime.minute}</span>
                <span> to {ticket.flight.arrivalTime.year}-{ticket.flight.arrivalTime.month}-{ticket.flight.arrivalTime.day} {ticket.flight.arrivalTime.hour}:{ticket.flight.arrivalTime.minute}</span>
                <p>Seat Number: {ticket.seatNumber}</p>
            </div>
        );
    }
    const validateID = () => {
        let obj = tickets.find(item => item.ticketID === Number(ticketIDToRemove));
        if (obj === undefined) { // ensures that only the tickets associated with the email can be deleted
            return false;
        }
        else {
            return true;
        }
    }

    useEffect(() => { // Updates seatnumber and flightnumber whenever the selected ticket ID changes
        try {
            let obj = tickets.find(item => item.ticketID === Number(ticketIDToRemove));
            if (obj !== undefined) {
                setSeatNumber(obj.seatNumber);
                setFlightNumber(obj.flight.flightnumber);
            }
        }
        catch(error) {
            console.error(error);
        }
      }, [ticketIDToRemove, tickets]);


    const handleRemoveTicket = async () => {
        if (validateID() === false) {
            return;
        }
        try {
          await axios.delete(`http://localhost:8080/api/ticket/delete-ticket/${ticketIDToRemove}`);

        } catch (error) {
          console.error('Error removing ticket:', error);
        }

        try {
            await axios.post("http://localhost:8080/api/seat/vacate-seat", null, {
                params: {flightNumber,
                        seatNumber}
            });
        }
        catch (error) {

        } 

        handleFindTicket();
      };


    const handleFindTicket = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/ticket/get-tickets', {
                params: {
                    email
                    },
            });
            setTickets(response.data);
            if (response.data.length !== 0) {
                setTicketsFound(true);
            }
            else {
                setTicketsFound(false);
            }
            
        } catch (error) {
            console.error('Error fetching menu options:', error);
        }
        console.log(tickets);
    }


    return (
        <>
            <label>
                Email:
                <input type="text" value={email} onChange={(e)=>setEmail(e.target.value)}>
                </input>
                <button onClick={handleFindTicket}>Find my ticket</button>
            </label>
            <br></br>

            {ticketsFound === false? <></>: 
            <>
            <label>
                Enter a Ticket ID to cancel the flight:
                <input type="text" value={ticketIDToRemove} onChange={(e)=>setTicketIDToRemove(e.target.value)}>
                </input>
                <button onClick={handleRemoveTicket}>Cancel Flight</button>
            </label>
            </>
            }
            <button onClick={()=> navigate('/menu')}>Back</button>
            <div>
                {tickets.map((ticket)=> 
                <TicketItem key={ticket.ticketID} ticket = {ticket} />
            )}
            </div>

        </>
    );
};

export default FindTicket;
