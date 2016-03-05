#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
create_auto_airbag_table="use automobiles;create table if not exists auto_airbag_table (
    auto int not null,
    airbag int not null
    );"
mysql -u $username -p$password -e "$create_auto_airbag_table"
echo "create auto_airbag_table successfully"
