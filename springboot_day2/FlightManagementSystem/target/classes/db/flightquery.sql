DROP DATABASE IF EXISTS flight_managament_db;
CREATE DATABASE IF NOT EXISTS flight_managament_db;

USE flight_managament_db;

select * from flight;
select * from schedule;

INSERT INTO schedule(departure_time, landing_time, stop)
VALUES(NOW(), NOW(), 'Paris');