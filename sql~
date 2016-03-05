create_db
create database if not exists automobiles;

create_auto_table
create table if not exists auto_table (id int not null auto_increment, name char(128), make char(128), baseprice int, primary key(id));

create_mapping_table
create table if not exists mapping_table (auto_id int not null, optionSet_id int not null, option_id int not null);

create_optionSet_table
create table if not exists optionSet_table (id int not null auto_increment, optionSet char(128), primary key(id));

create_option_table
create table if not exists option_table (id int not null auto_increment, option char(128), price int, primary key(id));

delete_auto
delete from auto_table where name = ? and make = ? and baseprice = ?

delete_mapping
delete from mapping_table where auto_id = ?

delete_option
delete from option_table where option = ? and price = ?

delete_optionSet
delete from optionSet_table where optionSet = ?

insert_auto
insert into auto_table (name, make, baseprice) values (?, ?, ?)

insert_mapping
insert into mapping_table (auto_id, optionSet_id, option_id) values (?, ?, ?)

insert_option
insert into option_table (option, price) values (?, ?)

insert_optionSet
insert into optionSet_table (optionSet) values (?)

select_auto
select * from auto_table where name = ? and make = ? and baseprice = ?

select_mapping
select * from mapping_table where auto_id = ? and optionSet_id = ? and option_id = ?

select_option
select * from option_table where option = ? and price = ?

select_optionSet
select * from optionSet_table where optionSet = ?

update_auto_baseprice
update auto_table set baseprice = ? where name = ?

update_auto_make
update auto_table set make = ? where name = ?

update_auto_name
update auto_table set name = ? where name = ?

update_option
update option_table set price = ? where option = ? and price = ?

update_optionSet
update optionSet_table set optionSet = ? where optionSet = ?
