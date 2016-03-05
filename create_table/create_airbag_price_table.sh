#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
create_airbag_price_table="use automobiles;create table if not exists airbag_price_table (
    id int not null auto_increment,
    airbag char(128),
    price int,
    primary key(id)
    );"
mysql -u $username -p$password -e "$create_airbag_price_table"
echo "create airbag_price_table successfully"
