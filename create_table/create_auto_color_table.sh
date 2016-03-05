#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
create_auto_color_table="use automobiles;create table if not exists auto_color_table (
    auto int not null,
    color int not null
    );"
mysql -u $username -p$password -e "$create_auto_color_table"
echo "create auto_color_table successfully"
