#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
create_auto_powerMoonroof_table="use automobiles;create table if not exists auto_powerMoonroof_table (
    auto int not null,
    powerMoonroof int not null
    );"
mysql -u $username -p$password -e "$create_auto_powerMoonroof_table"
echo "create auto_powerMoonroof_table successfully"
