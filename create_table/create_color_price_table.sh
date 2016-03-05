create table if not exists color_price_table (
    id int not null auto_increment,
    color char(128),
    price int,
    primary key(id)
    );
