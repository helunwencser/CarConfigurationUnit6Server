create table if not exists airbag_price_table (
    id int not null auto_increment,
    airbag char(128),
    price int,
    primary key(id)
    );
