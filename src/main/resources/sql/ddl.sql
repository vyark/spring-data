drop table event;
drop table ticket;
drop table user;
drop table userAccount;

create table event (
    eventId int,
    title varchar(255),
    eventDate date,
    ticketPrice numeric,
);

create table ticket (
    ticketId int,
    eventId int,
    userId int,
    category varchar(255),
    place int
);

create table user(
    userId int,
    firstname varchar(255),
    lastname varchar(255),
    email varchar(255),
)

create table userAccount(
    userAccountId int,
    prepaidAmount numeric
)