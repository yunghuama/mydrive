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
	question_video varchar(20),
	tips varchar(80),
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
	question_video varchar(20),
	tips varchar(80),
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
	question_video varchar(20),
	tips varchar(80),
	category varchar(50),
	createtime datetime
);

drop table if exists questions_motorcycle;
create table questions_motorcycle(
	id char(32) not null primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(20),
	question_video varchar(20),
	tips varchar(80),
	category varchar(50),
	createtime datetime
);

drop table if exists studentcard;
create table studentcard (
    id char(32) not null primary key,
    schoolId char(32) not null ,
    number varchar(11) not null,
    password varchar(16) not null,
    begindate datetime not null,
    remindtimes int not null,
    reminddays int not null,
    identity varchar(20),
    name varchar(10),
    nickname varchar(20),
    cartype char(2),
    phonenumber varchar(12),
    age int,
    sex char(1),
    createtime datetime
);

drop table if exists schoolcard;
create table schoolcard (
    id char(32) not null primary key,
    number varchar(11) not null,
    password varchar(16) not null,
    createtime datetime
);

drop table if exists examscore;
create table examscore (
    id char(32) not null primary key,
    studentId char(32) not null,
    score int,
    time varchar(5),
    cartype char(2),
    createtime datetime
);

drop table if exists question_wrong;
create table question_wrong (
    id char(32) not null primary key,
    questionId  int not null,
    studentId char(32) not null,
    questiontype char(1),
    count int,
    updatetime datetime
);

drop table if exists section;
create table section (
    id char(32) not null primary key,
    name  varchar(60) not null,
    category char(1) not null,
    updatetime datetime
);

drop table if exists markQuestion;
create table markQuestion (
    id char(32) not null primary key,
    questionId int not null,
    studentId char(32) not null,
    questiontype char(1) not null,
    createtime datetime
);

alter table questions_car drop column question_vedio;
alter table questions_car add column question_video varchar(20);
alter table questions_bus drop column question_vedio;
alter table questions_bus add column question_video varchar(20);
alter table questions_truck drop column question_vedio;
alter table questions_truck add column question_video varchar(20);
alter table questions_motorcycle drop column question_vedio;
alter table questions_motorcycle add column question_video varchar(20);
alter table studentcard drop column remidtimes;
alter table studentcard add column remindtimes int;

insert into studentcard(id,number,password,schoolid,begindate,remindtimes,reminddays) values('4028813518f35feb0118f392eee50043','123','123','4028813518f35feb0118f392eee50046','2013-05-18',50,365);

insert into section(id,name,category) values('4028813518f35feb0118f392eee50045','道路交通安全法律、法规和规章',1);
insert into section(id,name,category) values('4028813518f35feb0118f392eee50046','交通信号',1);
insert into section(id,name,category) values('4028813518f35feb0118f392eee50047','安全行车、文明驾驶基础知识',1);
insert into section(id,name,category) values('4028813518f35feb0118f392eee50048','机动车驾驶操作相关基础知识',1);

update questions_car set category = '4028813518f35feb0118f392eee50045' where category = '道路交通安全法律、法规和规章';
update questions_car set category = '4028813518f35feb0118f392eee50046' where category = '交通信号';
update questions_car set category = '4028813518f35feb0118f392eee50047' where category = '安全行车、文明驾驶基础知识';
update questions_car set category = '4028813518f35feb0118f392eee50048' where category = '机动车驾驶操作相关基础知识';

select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_car where id in (select questionid from markquestion  order by createtime desc)  limit 0,10;