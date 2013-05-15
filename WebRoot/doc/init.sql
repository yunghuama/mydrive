drop database if exists drivers;
create database drivers;
use  drivers;

drop table if exists questions_car;
create table questions_car(
	id char(32) not null primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(20),
	question_vedio varchar(20),
	category varchar(50),
	createtime datetime
);

drop table if exists questions_bus;
create table questions_bus(
	id char(32) not null primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(20),
	question_vedio varchar(20),
	category varchar(50),
	createtime datetime
);

drop table if exists questions_truck;
create table questions_truck(
	id char(32) not null primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(20),
	question_vedio varchar(20),
	category varchar(50),
	createtime datetime
);

drop table if exists questions_motorcycle;
create table questions_truck(
	id char(32) not null primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(20),
	question_vedio varchar(20),
	category varchar(50),
	createtime datetime
);