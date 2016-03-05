create table if not exists transmission_price_table (
    id int not null auto_increment,
    transmission char(128),
    price int,
    primary key(id)
    );
