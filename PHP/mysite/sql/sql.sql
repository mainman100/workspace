/*drop database my_db;*/
create database my_db;
use my_db;

create table User(

id int not null auto_increment primary key,
username char(50) not null,
password char(50) not null,
email char(50) not null,
age int

);
create table Permission(
id int not null auto_increment primary key,
title char(50) not null
);

create table User_Permission(
id int not null auto_increment primary key,
user_id int not null,
permission_id int not null,
foreign key(user_id) references User(id) on delete cascade on update cascade,
foreign key(permission_id) references Course(id) on delete cascade on update cascade
);

create table Course (
id int not null auto_increment primary key,
title char(50) not null,
lecturer_id int ,
foreign key(lecturer_id) references User(id) on delete set null on update cascade
);

create table User_Course(
id int not null auto_increment primary key,
user_id int not null ,
course_id int not null ,
foreign key(user_id) references User(id) on update cascade on delete cascade,
foreign key(course_id) references Course(id) on update cascade on delete cascade

);

create table MaterialType(
id int not null auto_increment primary key,
title char(50) not null
);

create table MaterialItem(
id int not null auto_increment primary key,
title char(50) not null,
materialtype_id int not null,
course_id int not null,
foreign key(materialtype_id) references MaterialType(id) on update cascade on delete cascade,
foreign key(course_id) references Course(id) on update cascade on delete cascade
);



