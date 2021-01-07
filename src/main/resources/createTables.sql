drop table if exists Hotel;
drop table if exists Reservation;
drop table if exists Room;
drop table if exists Stay;
drop table if exists UserTab;

create table UserTab( id int primary key auto_increment, first_name varchar(50) not null, last_name varchar(50) not null, login varchar(50) not null, password varchar(50) not null);
create table Hotel ( id int primary key auto_increment, "name" varchar(50) not null, adress varchar(250) not null, hotel_standard varchar(50) not null );
create table Reservation( id int primary key auto_increment, user_id int not null, constraint fk_reservation_user_id foreign key (user_id) references UserTab(id) on delete restrict );
create table Room( id int primary key auto_increment, price decimal not null, people_nr int not null, room_nr int not null, description varchar(250) not null, room_standard varchar(50) not null, hotel_id int not null, constraint fk_room_hotel_id foreign key (hotel_id) references Hotel(id) on delete restrict );
create table Stay( id int primary key auto_increment, full_price decimal not null, date_from datetime not null, date_to datetime  not null, status varchar(50) not null, guests varchar(250) not null, reservation_id int not null, constraint fk_stay_reservation_id foreign key (reservation_id) references Reservation(id) on delete restrict, room_id int not null, constraint fk_stay_room_id foreign key (room_id) references Room(id) on delete restrict  );
