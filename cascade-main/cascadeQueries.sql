create database automotivedb3;

use automotivedb3;

create table if not exists cars(
	carId int auto_increment primary key,
    carModel varchar(100) not null,
    yr int not null,
    price decimal(10,2) not null,
    color enum ('Red', 'Blue','Green','Black','White') not null
);

/* Owners table */
create table if not exists owners(
	ownerId int auto_increment primary key,
    ownerName varchar(100) not null,
    ownerAddress varchar(255) not null,
    ownerPhone varchar(20) not null
);

/* Services Table */
create table if not exists services(
	serviceId int auto_increment primary key,
    serviceName varchar(100) not null,
    serviceDescription text,
    serviceCost decimal(10,2) not null
);

/* Ticket -2 *- Relationships and constraints*/
/* Ownership table */
create table if not exists ownerships(
	ownershipId int auto_increment primary key,
    carId int,
    ownerId int,
    purchaseDate date not null,
    foreign key(carId) references cars(carId) on delete cascade on update cascade,
    foreign key(ownerId) references owners(ownerId) on delete cascade on update cascade
);

/*CarServices table*/
create table if not exists carservices(
	carId int,
    serviceId int,
    serviceDate date not null,
    primary key (carId, serviceId),
    foreign key(carId) references cars(carId) on delete cascade on update cascade,
    foreign key(serviceId) references services(serviceId) on delete cascade on update cascade
);

/* Mechanics Table */
create table if not exists mechanics(
	mechanicId int auto_increment primary key,
    mechanicName varchar(100) not null,
    phoneNo varchar(20) not null,
    hireDate timestamp default(current_timestamp())
);

/*Garages table*/
create table if not exists garages(
	garageId int auto_increment primary key,
    garageName varchar(100) not null unique,
    location varchar(255) not null
);

/* Car Mechanics Table*/
create table if not exists carmechanics(
	carId int,
    mechanicId int,
    serviceDate date not null,
    primary key(carId, mechanicId),
    foreign key(carId) references cars(carId) on delete cascade on update cascade,
    foreign key(mechanicId) references mechanics(mechanicId) on delete cascade on update cascade
);

insert into cars (carModel, yr, price, color)VALUES
('Toyota Camry', 2020, 25000.00, 'Red'),
('Honda Accord', 2021, 27000.00, 'Blue'),
('Ford Focus', 2019, 22000.00, 'Green');

INSERT INTO owners (ownerName, ownerAddress, ownerPhone) VALUES
('John Doe', '123 Elm St', '555-1234'),
('Alice Johnson', '456 Oak St', '555-8765');

insert into services(serviceName, serviceDescription, serviceCost) values
('Oil Change', 'Change the engine oil', 50.00),
('Tire Rotation', 'Rotate the tires', 30.00);

insert into ownerships(carId, ownerId, purchaseDate) values
(1, 1, '2020-05-10'),
(2, 2, '2021-08-15');

INSERT INTO carservices (carId, serviceId, serviceDate) VALUES
(1, 1, '2021-01-10'),
(1, 2, '2021-06-15'),
(2, 1, '2021-02-20');

insert into mechanics(mechanicName, phoneNo) values
('Mike Smith', '555-6789'),
('Sarah Brown', '555-9876');

insert into garages(garageName, location) values
('Downtown Garage', '789 Maple St'),
('Uptown Garage', '101 Oak St');

insert into carmechanics(carId, mechanicId, serviceDate) values
(1, 1, '2021-01-10'),
(2, 2, '2021-08-15');

/*Ticket 4*/
/*Task1 - delete car with id - 1*/
select * from cars;
delete from cars where carId = 1;

select * from cars;
select * from ownerships;
select * from carMechanics;
select * from carservices;
select * from owners;

/* Task2 */
insert into cars (carId,carModel, yr, price, color)VALUES
(1,'Toyota Camry', 2020, 25000.00, 'Red');

INSERT INTO carservices (carId, serviceId, serviceDate) VALUES
(1, 1, '2021-01-10');

insert into ownerships(carId, ownerId, purchaseDate) values
(1, 1, '2020-05-10');
update cars set carId = 100 where carId = 1;

/* Delete owner with id = 1*/
delete from owners where ownerId = 1;

/*Update services table*/
select * from services;
select * from carservices;

update services set serviceId = 100 where serviceId = 1;

delete from services where serviceId = 100;
