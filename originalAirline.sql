/*
 Created this sql file because I will be making changes to the airline.sql file using "Insert Into", "Update", and "Delete" queries
*/

DROP DATABASE IF EXISTS AIRLINE;
CREATE DATABASE AIRLINE; 
USE AIRLINE;

DROP TABLE IF EXISTS LOCATIONS;
CREATE TABLE LOCATIONS (
	CityName		varchar(50) not null,
	Latitude		float not null, -- positive value denotes North and negative value denotes South
    Longitude		float not null, -- positive value denotes East and negative value denotes West
    primary key (CityName)
);

INSERT INTO LOCATIONS (CityName, Latitude, Longitude) VALUES
('Los Angeles', 34.052235, -118.243683),
('Bangkok', 13.736717, 100.523186),
('Toronto', 43.651070, -79.347015),
('Tokyo', 35.652832, 139.839478),
('Calgary', 51.0447, -114.0719),
('New York City', 40.730610, -73.935242),
('Seoul', 37.5519, 126.9918),
('Beijing', 39.9042,  116.4074),
('Moscow', 55.7558, 37.6173),
('London', 51.509865, -0.118092),
('Mexico City', 19.432608, -99.133209),
('Dubai', 25.276987, 55.296249),
('Istanbul', 41.015137, 28.979530),
('Sydney', -33.865143, 151.209900),
('Paris', 48.864716, 2.349014),
('Amsterdam', 52.377956, 4.897070),
('Rome', 41.902782, 12.496366),
('Cairo', 30.033333, 31.233334),
('Wellington', -41.276825, 174.777969),
('Jerusalem', 31.7683, 35.2137),
('Beirut', 33.888630, 35.495480),
('Nairobi', -1.286389, 36.817223),
('Cape Town', -33.918861, 18.423300),
('Antananarivo', -18.8792, 47.5079);

DROP TABLE IF EXISTS AIRCRAFTS;
CREATE TABLE AIRCRAFTS (
	AircraftID		int not null AUTO_INCREMENT,
	ModelName		varchar(50) not null,
	primary key (AircraftID)
);

/*
	AIRCRAFTS is added when system admin adds an aircraft
    AIRCRAFTS is removed when system admin removes a aircraft
*/
INSERT INTO AIRCRAFTS (ModelName) VALUES
('Boeing 747-400'),
('Boeing 747-400'),
('Boeing 767'),
('Airbus A380'),
('Airbus A380'),
('Airbus A380'),
('CRJ700'),
('McDonnell Douglass DC-9 Super 80/MD-80'),
('Airbus A350 XWB'),
('Airbus A340'),
('Airbus A340'),
('Boeing 777'),
('Boeing 777'),
('Boeing 777'),
('Embraer ERJ'),
('Bombardier CRJ440');


/*
	FLIGHTS is added when system admin adds a flight
    FLIGHTS is removed when system admin removes a flight
    FLIGHTS is updated when system admin updates a flight
*/
DROP TABLE IF EXISTS FLIGHTS;
CREATE TABLE FLIGHTS (
	FlightNumber		int not null AUTO_INCREMENT,
	Origin				varchar(50) not null,
	Destination			varchar(50) not null,
    DepartureTime		datetime not null,
    ArrivalTime			datetime not null,
    AircraftID			int not null,
    -- Need to add more data
	primary key (FlightNumber),
    foreign key (Origin) references LOCATIONS(CityName),
	foreign key (Destination) references LOCATIONS(CityName),
    foreign key (AircraftID) references AIRCRAFTS(AircraftID)
);

INSERT INTO FLIGHTS (FlightNumber, Origin, Destination, DepartureTime, ArrivalTime, AircraftID) VALUES
(1, 'Los Angeles', 'Toronto', '2023-11-29 08:30:00', '2023-11-29 10:45:00', 3),
(2, 'Bangkok', 'Tokyo', '2023-11-29 13:30:00', '2023-11-29 17:00:00', 8),
(3, 'Sydney', 'Mexico City', '2023-12-30 19:00:00', '2023-12-31 5:00:00', 3);


/*
	SEATS is added when system admin adds a flight
    SEATS is removed when system admin removes a flight
    SEATS is updated (specifically the isAvailable) when user books or cancels a flight
*/
DROP TABLE IF EXISTS SEATS; 
CREATE TABLE SEATS(
	SeatID			int not null AUTO_INCREMENT,
	SeatNumber		int not null,
    FlightNumber	int not null,
    isAvailable		bit not null, -- 0 for occupied and 1 for vacant
    primary key (SeatID),
    foreign key (FlightNumber) references FLIGHTS(FlightNumber)
);

INSERT INTO SEATS (SeatID, SeatNumber, FlightNumber, isAvailable) VALUES
(1, 1, 1, 1),
(2, 2, 1, 1),
(3, 3, 1, 1),
(4, 4, 1, 1),
(5, 5, 1, 1),
(6, 6, 1, 1),
(7, 7, 1, 1),
(8, 8, 1, 1),
(9, 9, 1, 1),
(10, 10, 1, 1),
(11, 11, 1, 1),
(12, 12, 1, 1),
(13, 13, 1, 1),
(14, 14, 1, 1),
(15, 15, 1, 1),
(16, 16, 1, 1),
(17, 17, 1, 1),
(18, 18, 1, 1),
(19, 19, 1, 1),
(20, 20, 1, 0),
(21, 21, 1, 1),
(22, 22, 1, 1),
(23, 23, 1, 1),
(24, 24, 1, 1),
(25, 25, 1, 1),
(26, 26, 1, 1),
(27, 27, 1, 1),
(28, 28, 1, 1),
(29, 29, 1, 1),
(30, 30, 1, 0),
(31, 1, 2, 1),
(32, 2, 2, 1),
(33, 3, 2, 1),
(34, 4, 2, 1),
(35, 5, 2, 1),
(36, 6, 2, 1),
(37, 7, 2, 1),
(38, 8, 2, 1),
(39, 9, 2, 1),
(40, 10, 2, 1),
(41, 11, 2, 1),
(42, 12, 2, 1),
(43, 13, 2, 1),
(44, 14, 2, 1),
(45, 15, 2, 1),
(46, 16, 2, 1),
(47, 17, 2, 1),
(48, 18, 2, 1),
(49, 19, 2, 1),
(50, 20, 2, 1),
(51, 21, 2, 1),
(52, 22, 2, 1),
(53, 23, 2, 1),
(54, 24, 2, 1),
(55, 25, 2, 1),
(56, 26, 2, 0),
(57, 27, 2, 1),
(58, 28, 2, 1),
(59, 29, 2, 1),
(60, 30, 2, 1),
(61, 1, 3, 0),
(62, 2, 3, 1),
(63, 3, 3, 1),
(64, 4, 3, 1),
(65, 5, 3, 1),
(66, 6, 3, 1),
(67, 7, 3, 1),
(68, 8, 3, 1),
(69, 9, 3, 1),
(70, 10, 3, 0),
(71, 11, 3, 1),
(72, 12, 3, 1),
(73, 13, 3, 1),
(74, 14, 3, 1),
(75, 15, 3, 1),
(76, 16, 3, 1),
(77, 17, 3, 1),
(78, 18, 3, 1),
(79, 19, 3, 1),
(80, 20, 3, 1),
(81, 21, 3, 1),
(82, 22, 3, 1),
(83, 23, 3, 1),
(84, 24, 3, 1),
(85, 25, 3, 1),
(86, 26, 3, 1),
(87, 27, 3, 1),
(88, 28, 3, 1),
(89, 29, 3, 1),
(90, 30, 3, 1);

/*
	REGISTEREDUSERS is added when user registers an account
    REGISTEREDUSERS is removed when user deletes an account
    REGISTEREDUSERS is updated when user collects points or changes personal information
*/
DROP TABLE IF EXISTS REGISTEREDUSERS;
CREATE TABLE REGISTEREDUSERS (
	UserId			int not null AUTO_INCREMENT,
	EmailAddress	varchar(100) not null,
	ThePassWord		varchar(50) not null,
    FirstName		varchar(50) not null,
	LastName		varchar(50) not null,
    PhoneNumber		varchar(50) not null,
    Points			int not null,
    -- Because REGISTEREDUSERS and TICKETS is a 1 to many relationship, TicketID is not defined as foreign ID in this table
    -- Instead the registered user is referenced in the TICKETS table
    primary key (UserId)
);

INSERT INTO REGISTEREDUSERS (UserId, EmailAddress, ThePassWord, FirstName, LastName, PhoneNumber, Points) VALUES
(1, 'exampleName@gmail.com', 'SamplePasswoRRD', 'Edward', 'An', '587-123-4567', 333),
(2, 'mike@monsterinc.com', 'Le3TCODeGOD', "Mike", "Wazowski", '123-456-0000', 0);

/*
	TICKETS is added when user pays for a flight
    TICKETS is removed when user cancels a flight
*/
DROP TABLE IF EXISTS TICKETS;
CREATE TABLE TICKETS (
	TicketID		int not null AUTO_INCREMENT,
    Price			int not null,
    FirstName		varchar(50) not null,
	LastName		varchar(50) not null,
    FlightNumber	int not null,
    SeatNumber		int not null,
	EmailAddress	varchar(100),
    UserId			int,
    -- UserId can be null if the ticket belongs to a non registered user
	primary key (TicketID),
    foreign key (UserId) references REGISTEREDUSERS(UserId)
);

INSERT INTO TICKETS (TicketID, Price, FirstName, LastName, FlightNumber, SeatNumber, EmailAddress, UserId) VALUES
(1, 1000, "Edward", "An", 2, 26, 'exampleName@gmail.com', 1),
(2, 450, "Jamie", "Smith", 1, 20, 'coolGirl@yahoo.com', null),
(3, 2000, "Josh", "Tan", 3, 10, 'heheheh@yahoo.com', null),
(4, 2000, "Mike", "Wazowski", 1, 30, 'mike@monsterinc.com', 2),
(5, 2000, "Mike", "Wazowski", 3, 1, 'mike@monsterinc.com', 2);

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    UserID      int not null AUTO_INCREMENT,
    Username    varchar(50) not null,
    Password    varchar(50) not null,
    UserType    varchar(50) not null,
    primary key (UserID)
);

INSERT INTO Users (Username, Password, UserType) VALUES
('admin1', 'password', 'admin'),
('attendant', 'password', 'attendant'),
('agent','password','agent');