#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
create_auto_table="use automobiles;create table if not exists auto_table (
    id int not null auto_increment,
    name char(128),
    make char(128),
    baseprice int,
    primary key(id)
    );"
mysql -u $username -p$password -e "$create_auto_table"
echo "create auto_table successfully"
