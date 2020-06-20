CREATE DATABASE workshop2;
USE workshop2;
CREATE TABLE users (
    id int primary key auto_increment not null,
    email varchar(255) unique not null,
    username varchar(255) not null,
    password varchar(60) not null
)