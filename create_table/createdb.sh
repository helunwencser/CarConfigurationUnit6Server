#!/bin/bash
echo -n "Enter the mysql username: "
read username
echo -n "Enter the mysql password: "
read -s password
createDB="create database if not exists automobiles;"
mysql -u $username -p$password -e "$createDB"
echo "create database successfully"
