#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
create_auto_transmission_table="use automobiles;create table if not exists auto_transmission_table (
    auto int not null,
    transmission int not null
    );"
mysql -u $username -p$password -e "$create_auto_transmission_table"
echo "create auto_transmission_table successfully"
