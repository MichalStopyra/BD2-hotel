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


insert into Hotel("name", adress, hotel_standard) values ('Warszawa Hotel', 'Warszawa', 'PIEC_GWIAZDEK');
insert into Hotel("name", adress, hotel_standard) values ('Gdansk Hotel', 'Gdansk', 'TRZY_GWIAZDKI');
insert into Hotel("name", adress, hotel_standard) values ('Wroclaw Hotel', 'Wroclaw', 'CZTERY_GWIAZDEK');

insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (500, 4, 1, 'Pokoj z widokiem na wojne', 'REGULAR',1 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (10000, 5, 200, 'Pokoj z widokiem na morze', 'VIP',1 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (5000, 3, 300, 'Pokoj z widokiem na pustynie', 'PREMIUM',1 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (100, 1, 4, 'Pokoj z widokiem na parking', 'REGULAR', 1);
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (5000, 2, 100, 'Pokoj vip', 'VIP',2 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (5000, 3, 200, 'Pokoj premium', 'PREMIUM',2 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (150, 1, 2, 'Pokoj fajny', 'REGULAR',3 );
insert into Room(price, people_nr, room_nr, description, room_standard, hotel_id) values (200, 1, 22, 'Pokoj rownie fajny', 'REGULAR',3 );




