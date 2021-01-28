drop table if exists Hotel;
drop table if exists Reservation;
drop table if exists Room;
drop table if exists Stay;
drop table if exists User_tab;
drop table if exists Supply;
drop table if exists Supply_to_stay;
drop table if exists Task;

create table User_tab( id int primary key auto_increment, first_name varchar(50) not null, last_name varchar(50) not null, login varchar(50) not null, password varchar(50) not null);
create table Hotel ( id int primary key auto_increment, "name" varchar(50) not null, adress varchar(250) not null, hotel_standard varchar(50) not null );
create table Reservation( id int primary key auto_increment, user_id int not null, constraint fk_reservation_user_id foreign key (user_id) references User_tab(id) on delete restrict );
create table Room( id int primary key auto_increment, price decimal not null, people_nr int not null, room_nr int not null, description varchar(250) not null, room_standard varchar(50) not null, hotel_id int not null, constraint fk_room_hotel_id foreign key (hotel_id) references Hotel(id) on delete restrict );
create table Stay( id int primary key auto_increment, full_price decimal not null, date_from datetime not null, date_to datetime  not null, status varchar(50) not null, guests varchar(250) not null, reservation_id int, constraint fk_stay_reservation_id foreign key (reservation_id) references Reservation(id) on delete restrict, room_id int not null, constraint fk_stay_room_id foreign key (room_id) references Room(id) on delete restrict  );
create table Supply ( id int primary key auto_increment, "name" varchar(50) not null, price decimal not null, profit decimal not null );
create table Supply_to_stay( id int primary key auto_increment, date_when datetime not null, quantity int not null, supply_id int not null, constraint fk_sts_supply_id foreign key (supply_id) references Supply(id) on delete restrict, stay_id int not null, constraint fk_sts_stay_id foreign key (stay_id) references Stay(id) on delete restrict);
create table Task ( id int primary key auto_increment, "name" varchar(50) not null, date_when datetime not null, cost decimal not null );

insert into Hotel("name", adress, hotel_standard) values ('Warszawa Hotel', 'Warszawa', 'PIEC_GWIAZDEK');
insert into Hotel("name", adress, hotel_standard) values ('Gdansk Hotel', 'Gdansk', 'TRZY_GWIAZDKI');
insert into Hotel("name", adress, hotel_standard) values ('Wroclaw Hotel', 'Wroclaw', 'CZTsERY_GWIAZDEK');

insert into Task(id, "name", date_when, cost) values (1, 'Naprawa kranu', '2021-01-22', 5);
insert into Task(id, "name", date_when, cost) values (2, 'Wymiana klamki', '2021-01-22', 7);

insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (500, 4, 1, 'Pokoj z widokiem na wojne', 'REGULAR',1 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (10000, 5, 200, 'Pokoj z widokiem na morze', 'VIP',1 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (5000, 3, 300, 'Pokoj z widokiem na pustynie', 'PREMIUM',1 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (100, 1, 4, 'Pokoj z widokiem na parking', 'REGULAR', 1);
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (5000, 2, 100, 'Pokoj vip', 'VIP',2 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (5000, 3, 200, 'Pokoj premium', 'PREMIUM',2 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (150, 1, 2, 'Pokoj fajny', 'REGULAR',3 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (200, 1, 22, 'Pokoj rownie fajny', 'REGULAR',3 );

insert into Supply("name", price, profit) values ('masaz', 100, 50);
insert into Supply("name", price, profit) values ('spa', 200, 110);
insert into Supply("name", price, profit) values ('obiad premium', 1000, 700);

insert into User_Tab(first_name, last_name, login, password) values ('Mariusz', 'Kowalski', 'mkowalski', 'haslo');
insert into User_Tab(first_name, last_name, login, password) values ('Adam', 'Malysz', 'amalysz', 'password');
insert into User_Tab(first_name, last_name, login, password) values ('Tomasz', 'Adamek', 'tadamek', '1234');

insert into Reservation(user_id) values (1);
insert into Reservation(user_id) values (2);
insert into Reservation(user_id) values (3);

insert into Stay(full_price, date_from, date_to, status, guests, reservation_id, room_id) values (2000, '2021-01-22','2021-01-25', 'PRZYSZLE', 'Tomek Kowalski, Mariusz Kowalski', 1, 2 );
insert into Stay(full_price, date_from, date_to, status, guests, reservation_id, room_id) values (1000, '2021-01-07','2021-01-15', 'TRWAJACE', 'Adam Malysz', 2, 4 );
insert into Stay(full_price, date_from, date_to, status, guests, reservation_id, room_id) values (200, '2020-02-22','2020-02-25', 'SKONCZONE_ZAPLACONE', 'Adam Pol, Mateusz Borek', null, 5 );
insert into Stay(full_price, date_from, date_to, status, guests, reservation_id, room_id) values (5000, '2021-03-20','2021-04-01', 'PRZYSZLE', 'Tomek Adamek', 3, 1 );

insert into Supply_to_stay(quantity, date_when, supply_id, stay_id) values (1, '2021-01-22', 1, 1);
insert into Supply_to_stay(quantity, date_when, supply_id, stay_id) values (2, '2021-01-22', 1, 2);
insert into Supply_to_stay(quantity, date_when, supply_id, stay_id) values (2, '2021-01-22', 1, 3);
insert into Supply_to_stay(quantity, date_when, supply_id, stay_id) values (3, '2021-01-22', 1, 1);





