create table if not exists auto_table (
    id int not null auto_increment,
    name char(128),
    make char(128),
    baseprice int,
    primary key(id)
    );
