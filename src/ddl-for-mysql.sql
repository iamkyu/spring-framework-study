-- create database testdb;
-- use testdb;

-- create table `testdb`.users (
create table users (
    id varchar(10) primary key,
    name varchar(20) not null,
    password varchar(10) not null
);