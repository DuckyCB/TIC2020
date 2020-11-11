drop database proyecto_tic1;
create database proyecto_tic1;
use  proyecto_tic1;


    create table admin_user (
       id integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table app_user (
       user_role varchar(31) not null,
        id integer not null,
        is_account_non_expired bit not null,
        is_account_non_locked bit not null,
        is_credentials_non_expired bit not null,
        is_enabled bit not null,
        password varchar(255),
        username varchar(20),
        primary key (id)
    ) engine=InnoDB;

    create table brand (
       id integer not null,
        email_domain varchar(20),
        email_user varchar(20),
        name varchar(20),
        primary key (id)
    ) engine=InnoDB;

    create table brand_user (
       id integer not null,
        brand integer,
        primary key (id)
    ) engine=InnoDB;

    create table cart (
       id integer not null,
        accepted_by_store bit,
        date date,
        time time,
        to_deliver bit,
        client integer,
        store integer,
        primary key (id)
    ) engine=InnoDB;

    create table cart_item (
       id integer not null,
        price double precision,
        quantity integer,
        product integer not null,
        size_and_color integer not null,
        cart integer,
        primary key (id)
    ) engine=InnoDB;

    create table client (
       door_number varchar(10),
        optional varchar(50),
        street varchar(50),
        zip_code integer,
        email_domain varchar(20),
        email_user varchar(20),
        first_name varchar(20),
        last_name varchar(20),
        number integer,
        id integer not null,
        current_cart integer,
        primary key (id)
    ) engine=InnoDB;

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB;

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

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
        door_number varchar(10),
        optional varchar(50),
        street varchar(50),
        zip_code integer,
        email_domain varchar(20),
        email_user varchar(20),
        number integer,
        primary key (id)
    ) engine=InnoDB;

    create table store_brand (
       store_id integer not null,
        brand_id integer not null,
        primary key (store_id, brand_id)
    ) engine=InnoDB;

    create table store_user (
       id integer not null,
        store integer,
        primary key (id)
    ) engine=InnoDB;

    create table trousers (
       id integer not null,
        primary key (id)
    ) engine=InnoDB;

    alter table app_user 
       add constraint uc_username unique (username);

    alter table size_and_color 
       add constraint uc_size_color unique (size, color);

    alter table admin_user 
       add constraint FKpmmu04eg0ikp1hhfvk4dj0djx 
       foreign key (id) 
       references app_user (id);

    alter table brand_user 
       add constraint fk_brand_user_brand 
       foreign key (brand) 
       references brand (id);

    alter table brand_user 
       add constraint FKboyhc0oyxi0exgmmbft0g9lp3 
       foreign key (id) 
       references app_user (id);

    alter table cart 
       add constraint fk_cart_client 
       foreign key (client) 
       references client (id);

    alter table cart 
       add constraint fk_cart_store 
       foreign key (store) 
       references store (id);

    alter table cart_item 
       add constraint fk_cart_item_product 
       foreign key (product) 
       references product (id);

    alter table cart_item 
       add constraint fk_cart_item_size_color 
       foreign key (size_and_color) 
       references size_and_color (id);

    alter table cart_item 
       add constraint fk_cart_item_cart 
       foreign key (cart) 
       references cart (id);

    alter table client 
       add constraint fk_client_cart 
       foreign key (current_cart) 
       references cart (id);

    alter table client 
       add constraint FK2re8ltj5jewc835q5d1id08i9 
       foreign key (id) 
       references app_user (id);

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

    alter table store_user 
       add constraint fk_store_user_store 
       foreign key (store) 
       references store (id);

    alter table store_user 
       add constraint FKoaif9gy7od3ffxlu2lhw1d4l6 
       foreign key (id) 
       references app_user (id);

    alter table trousers 
       add constraint FK5gn723d35k44lbib1ucham9ta 
       foreign key (id) 
       references product (id);


    alter table product
    add column(subcategory integer);