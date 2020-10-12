    drop database proyecto_tic1;


    create database proyecto_tic1;
    use proyecto_tic1;


    create table brand (
        id integer not null,
        email_domain varchar(20),
        email_user varchar(20),
        name varchar(20),
        primary key (id)
    ) engine=InnoDB;

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB;

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    create table hoodie (
        id integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table product (
        product_type varchar(31) not null,
        id integer not null,
        color varchar(6),
        gender char(1),
        image longblob,
        name varchar(50),
        price double,
        brand_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table product_size (
        product_id integer not null,
        size varchar(5) not null,
        primary key(product_id, size)
    ) engine=InnoDB;

    create table shirt (
        id integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table size (
        size varchar(5) not null,
        primary key (size)
    ) engine=InnoDB;

    create table stock (
        product_id integer not null,
        store_id integer not null,
        stock integer,
        primary key (product_id, store_id)
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

    alter table hoodie 
       add constraint fk_hoodie_product
       foreign key (id) 
       references product (id);

    alter table product 
       add constraint fk_product_brand
       foreign key (brand_id) 
       references brand (id);

    alter table product_size 
       add constraint fk_prodsize_size 
       foreign key (size) 
       references size (size);

    alter table product_size 
       add constraint fk_prodsize_prod 
       foreign key (product_id) 
       references product (id);

    alter table shirt 
       add constraint fk_shirt_product
       foreign key (id) 
       references product (id);

    alter table store_brand 
       add constraint fk_storebrand_brand 
       foreign key (brand_id) 
       references brand (id);

    alter table store_brand 
       add constraint fk_storebrand_store 
       foreign key (store_id) 
       references store (id);

    alter table trousers 
       add constraint fk_trousers_product
       foreign key (id) 
       references product (id);



-- select p.* from Trousers t, Product p, Stock s where t.id = p.id and p.id = s.product_id group by (t.id) having sum(s.stock) >= 1;
--
--
-- select p.id, p.name from Product p, Stock s where p.id = s.product_id group by (p.id) having sum(s.stock) >= 1;