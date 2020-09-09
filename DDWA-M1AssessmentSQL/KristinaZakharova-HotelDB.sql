DROP DATABASE IF EXISTS HotelReservationDB;
CREATE DATABASE HotelReservationDB;
USE HotelReservationDB;

CREATE TABLE RoomType (

RoomTypeID TINYINT PRIMARY KEY AUTO_INCREMENT, 
RoomType VARCHAR(20) NOT NULL, 
StandardOccupancy TINYINT NOT NULL,
MaxOccupancy TINYINT NOT NULL,
BasePrice DECIMAL(6,2) NOT NULL,
ExtraPerson DECIMAL(5,2) NULL

);

CREATE TABLE Room (

RoomNumber INT PRIMARY KEY, 
ADAAccessible BIT NOT NULL DEFAULT 1,
RoomTypeID TINYINT NOT NULL,
CONSTRAINT fk_RoomType_Room FOREIGN KEY (RoomTypeID)
    REFERENCES RoomType(RoomTypeID)

);

CREATE TABLE Amenity (

AmenityID TINYINT PRIMARY KEY AUTO_INCREMENT,
AmenityName VARCHAR(20) NOT NULL,
AmenityPrice DECIMAL(5,2) NULL

);

CREATE TABLE RoomAmenity (

AmenityID TINYINT NOT NULL,
RoomNumber INT NOT NULL,
PRIMARY KEY pk_RoomAmenity (AmenityID, RoomNumber),
    CONSTRAINT fk_RoomAmenity_Room FOREIGN KEY (RoomNumber)
        REFERENCES Room(RoomNumber),
    CONSTRAINT fk_RoomAmenity_Amenity FOREIGN KEY (AmenityID)
        REFERENCES Amenity(AmenityID)

);

CREATE TABLE Guest (

GuestID INT PRIMARY KEY AUTO_INCREMENT,
GuestName VARCHAR(100) NOT NULL,
Address VARCHAR(100) NOT NULL,
City VARCHAR(50) NOT NULL,
State CHAR(2) NOT NULL,
Zip CHAR(5) NOT NULL,
Phone VARCHAR(20) NOT NULL

);

CREATE TABLE Reservation (

ReservationID INT PRIMARY KEY AUTO_INCREMENT,
StartDate DATE NOT NULL, 
EndDate DATE NOT NULL,
NumberOfAdults TINYINT NOT NULL,
NumberOfKids TINYINT NULL,
TotalCost DECIMAL(6,2) NOT NULL,
GuestID INT NOT NULL, 
CONSTRAINT fk_Reservation_Guest FOREIGN KEY (GuestID)
    REFERENCES Guest(GuestID)

);

CREATE TABLE RoomReservation (

RoomNumber INT NOT NULL,
ReservationID INT NOT NULL,
PRIMARY KEY pk_RoomReservation (ReservationID, RoomNumber),
    CONSTRAINT fk_RoomReservation_Room FOREIGN KEY (RoomNumber)
        REFERENCES Room(RoomNumber),
    CONSTRAINT fk_RoomReservation_Reservation FOREIGN KEY (ReservationID)
        REFERENCES Reservation(ReservationID)

);


