USE HotelReservationDB;

INSERT INTO Guest (GuestName, Address, City, State, Zip, Phone)
VALUES ('Kristina Zakharova', '172 Suydam st, apt 1L', 'Brooklyn', 'NY', '11221', '3475937475');

INSERT INTO Guest (GuestName, Address, City, State, Zip, Phone)
VALUES ('Mack Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', '51501', '(291)553-0508'), 
       ('Bettyann Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', '99654', '(478)277-9632'),
       ('Duane Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '(308)494-0198'),
       ('Karie Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '(214)730-0298'),
       ('Aurore Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '(377)507-0974'),
       ('Zachery Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '(814)485-2615'),
       ('Jeremiah Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '(279)491-0960'),
       ('Walter Holaway', '7556 Arrowhead St', 'Cumberland', 'RI', '02864', '(446)396-6785'),
       ('Wilfred Vise', '77 West Surrey Street', 'Oswego', 'NY', '13126', '(834)727-1001'),
       ('Maritza Tilton', '939 Linda Rd.', 'Burke', 'WA', '22015', '(446)351-6860'),
       ('Joleen Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '(231)893-2755');
       
UPDATE Guest SET 
	Address = '172 Suydam St.',
    Phone = '(347)593-7475'
WHERE GuestID = 1;

INSERT INTO RoomType (RoomType, StandardOccupancy, MaxOccupancy, BasePrice, ExtraPerson)
VALUES ('Single', 2, 2, '174.99', NULL),
       ('Double', 2, 4, '199.99', '10.00'),
       ('Suite', 3, 8, '399.99', '20.00');
       
INSERT INTO Amenity (AmenityName, AmenityPrice) 
VALUES ('Microwave', NULL),
       ('Refrigerator', NULL),
       ('Oven', NULL),
       ('Jacuzzi', '25.00');
       
INSERT INTO Room (RoomNumber, ADAAccessible, RoomTypeID)
VALUES (201, 0, 2),
       (202, 1, 2), 
       (203, 0, 2),
       (204, 1, 2),
       (205, 0, 1),
       (206, 1, 1),
       (207, 0, 1),
       (208, 1, 1),
       (301, 0, 2),
       (302, 1, 2),
       (303, 0, 2),
       (304, 1, 2),
       (305, 0, 1),
       (306, 1, 1),
       (307, 0, 1),
       (308, 1, 1),
       (401, 1, 3),
       (402, 1, 3);

INSERT INTO RoomAmenity (RoomNumber, AmenityID) 
VALUES (201, 1),
	   (201, 4),
       (202, 2),
       (203, 1),
       (203, 4),
       (205, 1),
       (205, 2),
       (205, 4),
       (206, 1),
       (206, 2),
       (207, 1),
       (207, 2),
       (207, 4),
       (208, 1),
       (208, 2),
       (301, 1),
       (301, 4),
       (302, 2),
       (303, 1),
       (303, 4),
       (304, 2),
       (305, 1),
       (305, 2),
       (305, 4),
       (306, 1),
       (306, 2),
       (307, 1),
       (307, 2),
       (307, 4),
       (308, 1),
       (308, 2),
       (401, 1),
       (401, 2),
       (401, 3),
       (402, 1),
       (402, 2),
       (402, 3);
       
INSERT INTO Reservation (GuestID, StartDate, EndDate, NumberOfAdults, NumberOfKids, TotalCost)
VALUES (2, '2023-02-02', '2023-02-04', 1, NULL, '299.98'),  
       (3, '2023-02-05', '2023-02-10', 2, 1, '999.95'), 
	   (4, '2023-02-22', '2023-02-24', 2, NULL, '349.98'),
       (5, '2023-03-06', '2023-03-07', 2, 2, '199.99'), 
       (1, '2023-03-17', '2023-03-20', 1, 1, '524.97'), 
       (6, '2023-03-18', '2023-03-23', 3, NULL, '924.95'),
       (7, '2023-03-29', '2023-03-31', 2, 2, '349.98'),
       (8, '2023-03-31', '2023-04-05', 2, NULL, '874.95'),
       (9, '2023-04-09', '2023-04-13', 1, NULL, '799.96'),
       (10, '2023-04-23', '2023-04-24', 1, 1, '174.99'),
       (11, '2023-05-30', '2023-06-02', 2, 4, '1199.97'),
       (12, '2023-06-10', '2023-06-14', 2, NULL, '599.96'),
       (12, '2023-06-10', '2023-06-14', 1, NULL, '599.96'),
       (6, '2023-06-17', '2023-06-18', 3, NULL, '184.99'),
       (1, '2023-06-28', '2023-07-02', 2, NULL, '699.96'),
       (9, '2023-07-13', '2023-07-14', 3, 1, '184.99'),
       (10, '2023-07-18', '2023-07-21', 4, 2, '1259.97'),
       (3, '2023-07-28', '2023-07-29', 2, 1, '199.99'),
       (3, '2023-08-30', '2023-09-01', 1, NULL, '349.98'),
       (2, '2023-09-16', '2023-09-17', 2, NULL, '149.99'),
       (5, '2023-09-13', '2023-09-15', 2, 2, '399.98'),
       (4, '2023-11-22', '2023-11-25', 2, 2, '1199.97'),
       (2, '2023-11-22', '2023-11-25', 2, NULL, '449.97'),
       (2, '2023-11-22', '2023-11-25', 2, 2, '599.97'),
       (11, '2023-12-24', '2023-12-28', 2, NULL, '699.96');
       
INSERT INTO RoomReservation (ReservationID, RoomNumber)
VALUES (1, 308), 
       (2, 203), 
       (3, 305), 
       (4, 201), 
       (5, 307), 
       (6, 302), 
       (7, 202), 
       (8, 304), 
       (9, 301), 
       (10, 207), 
       (11, 401), 
       (12, 206), 
       (13, 208), 
       (14, 304), 
       (15, 205), 
       (16, 204), 
       (17, 401), 
       (18, 303),
       (19, 305), 
       (20, 208), 
       (21, 203), 
       (22, 401),
       (23, 206), 
       (24, 301), 
       (25, 302);
       
SELECT * FROM Guest;

-- deleting the reservations for Jeremiah Pendergrass first
DELETE FROM RoomReservation 
WHERE ReservationID = 8;

DELETE FROM Reservation 
WHERE GuestID = 8;

-- deleting Jeremiah Pendergrass 
 DELETE FROM Guest
 WHERE GuestID = 8;
 
 -- checking that he's been deleted 
 SELECT * FROM Guest;
       
       