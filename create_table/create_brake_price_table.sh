create table if not exists brake_price_table (
    id int not null auto_increment,
    brake char(128),
    price int,
    primary key(id)
    );
