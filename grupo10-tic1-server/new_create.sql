drop database proyecto_tic1;
create database proyecto_tic1;
use  proyecto_tic1;

    create table brand (
       id integer not null,
        email_domain varchar(20),
        email_user varchar(20),
        name varchar(20),
        primary key (id)
    ) engine=InnoDB;

    create table hoodie (
       id integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table product (
       product_type varchar(31) not null,
        id integer not null,
        gender char(1),
        image longblob,
        name varchar(50),
        price double precision,
        brand_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table product_size_color (
       product_id integer not null,
        size_color integer not null,
        primary key (product_id, size_color)
    ) engine=InnoDB;

    create table shirt (
       id integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table size_and_color (
       id integer not null,
        color varchar(10),
        size varchar(10),
        primary key (id)
    ) engine=InnoDB;

    create table stock (
       id integer not null,
        stock integer,
        product_id integer,
        sizecolor integer,
        store integer,
        primary key (id)
    ) engine=InnoDB;

    create table store (
       id integer not null,
        address varchar(50),
        number integer,
        primary key (id)
    ) engine=InnoDB;

    create table store_brand (
       store_id integer not null,
        brand_id integer not null,
        primary key (store_id, brand_id)
    ) engine=InnoDB;

    create table trousers (
       id integer not null,
        primary key (id)
    ) engine=InnoDB;

    alter table size_and_color 
       add constraint uc_size_color unique (size, color);

    alter table hoodie 
       add constraint FK4d9eklcgnkxic6sdqmgwfgnpu 
       foreign key (id) 
       references product (id);

    alter table product 
       add constraint fk_product_brand 
       foreign key (brand_id) 
       references brand (id);

    alter table product_size_color 
       add constraint fk_productsizecolor_sizecolor 
       foreign key (size_color) 
       references size_and_color (id);

    alter table product_size_color 
       add constraint fk_productsize_product 
       foreign key (product_id) 
       references product (id);

    alter table shirt 
       add constraint FKboh98e7x5vowawamp4729idy6 
       foreign key (id) 
       references product (id);

    alter table stock 
       add constraint fk_stock_product 
       foreign key (product_id) 
       references product (id);

    alter table stock 
       add constraint fk_stock_sizecolor 
       foreign key (sizecolor) 
       references size_and_color (id);

    alter table stock 
       add constraint fk_stock_store 
       foreign key (store) 
       references store (id);

    alter table store_brand 
       add constraint fk_storebrand_brand 
       foreign key (brand_id) 
       references brand (id);

    alter table store_brand 
       add constraint fk_storebrand_store 
       foreign key (store_id) 
       references store (id);

    alter table trousers 
       add constraint FK5gn723d35k44lbib1ucham9ta 
       foreign key (id) 
       references product (id);


select * from product p, product_size_color s, size_and_color t where p.id = s.product_id and s.size_color = t.id;

select * from product p, stock s where p.id = s.product_id;