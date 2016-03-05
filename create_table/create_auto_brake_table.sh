#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
create_auto_brake_table="use automobiles;create table if not exists auto_brake_table (
    auto int not null,
    brake int not null
    );"
mysql -u $username -p$password -e "$create_auto_brake_table"
echo "create auto_brake_table successfully"
