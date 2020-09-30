
    create table brand (
       id integer not null,
        email_domain varchar(20),
        email_user varchar(20),
        name varchar(20),
        primary key (id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table product (
       product_type varchar(31) not null,
        id integer not null,
        color tinyblob,
        gender char(1),
        image longblob,
        name varchar(50),
        brand_id integer,
        primary key (id)
    ) engine=InnoDB

    create table product_size (
       product_id integer not null,
        size char(1) not null
    ) engine=InnoDB

    create table size (
       size char(1) not null,
        primary key (size)
    ) engine=InnoDB

    create table stock (
       product_id integer not null,
        store_id integer not null,
        stock integer,
        primary key (product_id, store_id)
    ) engine=InnoDB

    create table store (
       id integer not null,
        address varchar(50),
        number integer,
        primary key (id)
    ) engine=InnoDB

    create table store_brand (
       store_id integer not null,
        brand_id integer not null,
        primary key (store_id, brand_id)
    ) engine=InnoDB

    alter table product 
       add constraint FKs6cydsualtsrprvlf2bb3lcam 
       foreign key (brand_id) 
       references brand (id)

    alter table product_size 
       add constraint fk_prodsize_size 
       foreign key (size) 
       references size (size)

    alter table product_size 
       add constraint fk_prodsize_prod 
       foreign key (product_id) 
       references product (id)

    alter table store_brand 
       add constraint fk_storebrand_brand 
       foreign key (brand_id) 
       references brand (id)

    alter table store_brand 
       add constraint fk_storebrand_store 
       foreign key (store_id) 
       references store (id)
