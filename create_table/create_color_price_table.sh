#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
create_color_price_table="use automobiles;create table if not exists color_price_table (
    id int not null auto_increment,
    color char(128),
    price int,
    primary key(id)
    );"
mysql -u $username -p$password -e "$create_color_price_table"
echo "create color_price_table successfully"
