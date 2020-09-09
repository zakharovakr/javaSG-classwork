USE HotelReservationDB;
-- 1. Write a query that returns a list of reservations that end in July 2023,
-- including the name of the guest, the room number(s), and the reservation dates.
 SELECT g.GuestName,
        rr.RoomNumber, 
        rs.StartDate, 
        rs.EndDate
FROM Guest g 
INNER JOIN Reservation rs 
ON g.GuestID = rs.GuestID 
INNER JOIN RoomReservation rr
ON rs.ReservationID = rr.ReservationID
WHERE rs.EndDate LIKE '2023-07-__';
-- Result: 
-- # GuestName, RoomNumber, StartDate, EndDate
-- 'Kristina Zakharova', '205', '2023-06-28', '2023-07-02'
-- 'Walter Holaway', '204', '2023-07-13', '2023-07-14'
-- 'Wilfred Vise', '401', '2023-07-18', '2023-07-21'
-- 'Bettyann Seery', '303', '2023-07-28', '2023-07-29'


-- 2. 
-- Write a query that returns a list of all reservations 
-- for rooms with a jacuzzi, displaying the guest's name, 
-- the room number, and the dates of the reservation.
SELECT g.GuestName,
       rr.RoomNumber,
       rs.StartDate, 
       rs.EndDate
FROM Guest g 
INNER JOIN Reservation rs 
ON g.GuestID = rs.GuestID 
INNER JOIN RoomReservation rr
ON rs.ReservationID = rr.ReservationID
INNER JOIN Room r 
ON rr.RoomNumber = r.RoomNumber
INNER JOIN RoomAmenity ra 
ON r.RoomNumber = ra.RoomNumber 
WHERE AmenityID = 4;
-- Result: 
-- # GuestName, RoomNumber, StartDate, EndDate
-- 'Karie Yang', '201', '2023-03-06', '2023-03-07'
-- 'Bettyann Seery', '203', '2023-02-05', '2023-02-10'
-- 'Karie Yang', '203', '2023-09-13', '2023-09-15'
-- 'Kristina Zakharova', '205', '2023-06-28', '2023-07-02'
-- 'Wilfred Vise', '207', '2023-04-23', '2023-04-24'
-- 'Walter Holaway', '301', '2023-04-09', '2023-04-13'
-- 'Mack Simmer', '301', '2023-11-22', '2023-11-25'
-- 'Bettyann Seery', '303', '2023-07-28', '2023-07-29'
-- 'Duane Cullison', '305', '2023-02-22', '2023-02-24'
-- 'Bettyann Seery', '305', '2023-08-30', '2023-09-01'
-- 'Kristina Zakharova', '307', '2023-03-17', '2023-03-20'


-- 3.
-- Write a query that returns all the rooms reserved for a specific guest, 
-- including the guest's name, the room(s) reserved, 
-- the starting date of the reservation, and how many people were included in the reservation. 
-- (Choose a guest's name from the existing data.)
SELECT g.GuestName,
       r.RoomNumber,
       rs.StartDate, 
       rs.NumberOfAdults, 
       IFNULL(rs.NumberOfKids, 0) AS NumberOfKids
FROM Guest g 
INNER JOIN Reservation rs 
ON g.GuestID = rs.GuestID
INNER JOIN RoomReservation rr
ON rs.ReservationID = rr.ReservationID
INNER JOIN Room r 
ON rr.RoomNumber = r.RoomNumber
WHERE GuestName LIKE 'Kristina Zakharova';
-- Result: 
-- # GuestName, RoomNumber, StartDate, NumberOfAdults, NumberOfKids
-- 'Kristina Zakharova', '307', '2023-03-17', '1', '1'
-- 'Kristina Zakharova', '205', '2023-06-28', '2', '0'


-- 4. 
-- Write a query that returns a list of rooms, reservation ID, 
-- and per-room cost for each reservation. 
-- The results should include all rooms, 
-- whether or not there is a reservation associated with the room.
SELECT r.RoomNumber,
       rs.ReservationID,
       rs.TotalCost
FROM Room r 
LEFT OUTER JOIN RoomReservation rr 
ON rr.RoomNumber = r.RoomNumber
LEFT OUTER JOIN Reservation rs
ON rr.ReservationID = rs.ReservationID;

-- Result:
-- # RoomNumber, ReservationID, TotalCost
-- '205', '15', '699.96'
-- '206', '12', '599.96'
-- '206', '23', '449.97'
-- '207', '10', '174.99'
-- '208', '13', '599.96'
-- '208', '20', '149.99'
-- '305', '3', '349.98'
-- '305', '19', '349.98'
-- '306', NULL, NULL
-- '307', '5', '524.97'
-- '308', '1', '299.98'
-- '201', '4', '199.99'
-- '202', '7', '349.98'
-- '203', '2', '999.95'
-- '203', '21', '399.98'
-- '204', '16', '184.99'
-- '301', '9', '799.96'
-- '301', '24', '599.97'
-- '302', '6', '924.95'
-- '302', '25', '699.96'
-- '303', '18', '199.99'
-- '304', '14', '184.99'
-- '401', '11', '1199.97'
-- '401', '17', '1259.97'
-- '401', '22', '1199.97'
-- '402', NULL, NULL


-- 5. 
-- Write a query that returns all the rooms accommodating at least three guests 
-- and that are reserved on any date in April 2023.
 SELECT r.RoomNumber,
        rs.NumberOfAdults, 
        rs.NumberOfKids, 
        rs.StartDate, 
        rs.EndDate
 FROM Room r
 INNER JOIN RoomReservation rr
 ON r.RoomNumber = rr.RoomNumber
 INNER JOIN Reservation rs
 ON rr.ReservationID = rs.ReservationID
 WHERE ((rs.NumberOfAdults + rs.NumberOfKids) >= 3) 
 AND ((rs.StartDate BETWEEN '2023-04-01' AND '2023-04-31') OR (rs.EndDate BETWEEN '2023-04-01' AND '2023-04-31'));
-- Result:
-- # RoomNumber, NumberOfAdults, NumberOfKids, StartDate, EndDate
-- No such reservations/rooms

-- 6. 
-- Write a query that returns a list of all guest names 
-- and the number of reservations per guest, 
-- sorted starting with the guest with the most reservations and then by the guest's last name.

SELECT g.GuestName, 
       COUNT(r.ReservationID) 
FROM Guest g
INNER JOIN Reservation r 
ON r.GuestID = g.GuestID
GROUP BY g.GuestName
ORDER BY COUNT(r.ReservationID) DESC, g.GuestName ASC;

-- I don't have guest last name field, will have to change the whole DB structure, might do that later.
-- Result: 

-- # GuestName, COUNT(r.ReservationID)
-- 'Mack Simmer', '4'
-- 'Bettyann Seery', '3'
-- 'Aurore Lipton', '2'
-- 'Duane Cullison', '2'
-- 'Joleen Tison', '2'
-- 'Karie Yang', '2'
-- 'Kristina Zakharova', '2'
-- 'Maritza Tilton', '2'
-- 'Walter Holaway', '2'
-- 'Wilfred Vise', '2'
-- 'Zachery Luechtefeld', '1'


-- 7. 
-- Write a query that displays the name, address, and phone number of a guest 
-- based on their phone number. 
-- (Choose a phone number from the existing data.)
SELECT GuestName, 
       Address, 
       Phone 
FROM Guest
WHERE Phone = '(347)593-7475';
-- Result: 
-- GuestName, Address, Phone
-- 'Kristina Zakharova', '172 Suydam St.', '(347)593-7475'

