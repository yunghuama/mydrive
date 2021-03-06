drop database if exists drivers;
create database drivers;
use  drivers;

drop table if exists shandong_questions_car;
create table shandong_questions_car(
	id int not null auto_increment primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(40),
	question_video varchar(40),
	tips varchar(80),
	category varchar(50),
	createtime datetime
);

drop table if exists shandong_questions_bus;
create table shandong_questions_bus(
	id int not null auto_increment primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(40),
	question_video varchar(40),
	tips varchar(80),
	category varchar(50),
	createtime datetime
);

drop table if exists shandong_questions_truck;
create table shandong_questions_truck(
	id int not null auto_increment primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(40),
	question_video varchar(40),
	tips varchar(80),
	category varchar(50),
	createtime datetime
);

drop table if exists shandong_questions_motorcycle;
create table shandong_questions_motorcycle(
	id int not null auto_increment primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(40),
	question_video varchar(40),
	tips varchar(80),
	category varchar(50),
	createtime datetime
);

drop table if exists shandong_questions3;
create table shandong_questions3(
	id int not null auto_increment primary key,
	code varchar(10) not null,
	question varchar(150) not null,
	answer_a varchar(80) not null,
	answer_b varchar(80) not null,
	answer_c varchar(80),
	answer_d varchar(80),
	answer varchar(4) not null,
	question_img varchar(40),
	question_video varchar(40),
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
    activetime datetime,
    activedate date,
    createtime datetime
);

drop table if exists schoolcard;
create table schoolcard (
    id char(32) not null primary key,
    number varchar(11) not null,
    password varchar(16) not null,
    city char(32),
    name varchar(50) ,
    address varchar(80),
    tel varchar(15),
    logo varchar(20),
    questiontype varchar(20),
    createtime datetime
);

drop table if exists driveradmin;
create table driveradmin (
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
    cartype int,
    createtime datetime
);

drop table if exists examscore3;
create table examscore3 (
    id char(32) not null primary key,
    studentId char(32) not null,
    score int,
    time varchar(5),
    createtime datetime
);

drop table if exists question_wrong;
create table question_wrong (
    id char(32) not null primary key,
    questionId  int not null,
    studentId char(32) not null,
    questiontype char(1),
    updatetime datetime
);

drop table if exists question_wrong3;
create table question_wrong3 (
    id char(32) not null primary key,
    questionId  int not null,
    studentId char(32) not null,
    updatetime datetime
);

drop table if exists section;
create table section (
    id char(32) not null primary key,
    name  varchar(60) not null,
    carType char(1) not null,
    sortIndex char(1) not null,
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

drop table if exists markQuestion3;
create table markQuestion3 (
    id char(32) not null primary key,
    questionId int not null,
    studentId char(32) not null,
    createtime datetime
);

drop table if exists announcement;
create table announcement (
    id char(32) not null primary key,
    title varchar(100),
    content varchar(8000) not null,
    schoolcard char(32) not null,
    createtime datetime
);

drop table if exists message;
create table message (
    id char(32) not null primary key,
    title varchar(100),
    content varchar(8000) not null,
    schoolcard char(32) not null,
    studentcard char(32) not null,
    type char(1),
    createtime datetime
);

drop table if exists advertisement;
create table advertisement (
    id char(32) not null primary key,
    url varchar(300),
    page int,
    position int,
    stime int,
    image varchar(50),
    pathName varchar(50),
    name varchar(100),
    createtime datetime
);


drop table if exists loginlogs;
create table loginlogs (
    id char(32) not null primary key,
    userId char(32),
    createtime datetime,
    createdate date
);

drop table if exists questiontable;
create table questiontable (
    tablename varchar(50) not null primary key,
    tabletitle varchar(50),
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

insert into studentcard(id,number,password,schoolid,begindate,remindtimes,reminddays) values('4028813518f35feb0118f392eee50043','test1','123','4028813518f35feb0118f392eee50046','2013-05-18',50,365);
insert into studentcard(id,number,password,schoolid,begindate,remindtimes,reminddays) values('4028813518f35feb0118f392eee50022','333','333','4028813518f35feb0118f392eee50046','2013-05-18',50,365);
insert into studentcard(id,number,password,schoolid,begindate,remindtimes,reminddays) values('4028813518f35feb0118f392eee50041','test2','123','4028813518f35feb0118f392eee50046','2013-05-18',50,365);

insert into schoolcard(id,number,password,createtime) values('4028813518f35feb0118f392eee50046','222','222','2013-05-18');
insert into driveradmin(id,number,password,createtime) values('4028813518f35feb0118f392eee50040','admin','admin','2013-05-18');


insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50001','道路交通安全法律、法规和规章',0,0);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50002','交通信号',0,1);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50003','安全行车、文明驾驶基础知识',0,2);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50004','机动车驾驶操作相关基础知识',0,3);

insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50005','道路交通安全法律、法规和规章',1,0);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50006','交通信号',1,1);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50007','安全行车、文明驾驶基础知识',1,2);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50008','机动车驾驶操作相关基础知识',1,3);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50009','大型客车、城市公交车、中型客车',1,4);

insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50010','道路交通安全法律、法规和规章',2,0);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50011','交通信号',2,1);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50012','安全行车、文明驾驶基础知识',2,2);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50013','机动车驾驶操作相关基础知识',2,3);
insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50014','牵引车、大型货车',2,4);


insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50015','摩托车',3,0);

insert into section(id,name,carType,sortIndex) values('4028813518f35feb0118f392eee50016','安全文明驾驶常识',4,0);

update shandong_questions_car set category = '4028813518f35feb0118f392eee50001' where category = '道路交通安全法律、法规和规章';
update shandong_questions_car set category = '4028813518f35feb0118f392eee50002' where category = '交通信号';
update shandong_questions_car set category = '4028813518f35feb0118f392eee50003' where category = '安全行车、文明驾驶基础知识';
update shandong_questions_car set category = '4028813518f35feb0118f392eee50004' where category = '机动车驾驶操作相关基础知识';

update shandong_questions_bus set category = '4028813518f35feb0118f392eee50005' where category = '道路交通安全法律、法规和规章';
update shandong_questions_bus set category = '4028813518f35feb0118f392eee50006' where category = '交通信号';
update shandong_questions_bus set category = '4028813518f35feb0118f392eee50007' where category = '安全行车、文明驾驶基础知识';
update shandong_questions_bus set category = '4028813518f35feb0118f392eee50008' where category = '机动车驾驶操作相关基础知识';
update shandong_questions_bus set category = '4028813518f35feb0118f392eee50009' where category = '大型客车、城市公交车、中型客车';

update shandong_questions_truck set category = '4028813518f35feb0118f392eee50010' where category = '道路交通安全法律、法规和规章';
update shandong_questions_truck set category = '4028813518f35feb0118f392eee50011' where category = '交通信号';
update shandong_questions_truck set category = '4028813518f35feb0118f392eee50012' where category = '安全行车、文明驾驶基础知识';
update shandong_questions_truck set category = '4028813518f35feb0118f392eee50013' where category = '机动车驾驶操作相关基础知识';
update shandong_questions_truck set category = '4028813518f35feb0118f392eee50014' where category = '牵引车、大型货车';

update shandong_questions_motorcycle set category = '4028813518f35feb0118f392eee50015' where category = '摩托车';

update shandong_questions3 set category = '4028813518f35feb0118f392eee50016' where category = '安全文明驾驶常识';

insert into  questiontable values('shandong','山东省题库','2013-06-22');
update schoolcard set questiontype = 'shandong';