CREATE USER 'grupo10_tic_2020'@'localhost' IDENTIFIED BY 'grupo10_tic_2020';
GRANT ALL PRIVILEGES ON proyecto_tic1v2.* TO 'grupo10_tic_2020'@'localhost';

drop database proyecto_tic1v2;
create database proyecto_tic1v2;
use  proyecto_tic1v2;




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
        date date,
        purchased bit,
        time time,
        to_deliver bit,
        client integer,
        primary key (id)
    ) engine=InnoDB;

    create table cart_item (
       id integer not null,
        price double precision,
        quantity integer,
        product integer not null,
        size_and_color integer not null,
        store integer,
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
        subcategory integer,
        brand_id integer,
        description varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table product_size_color (
       product_id integer not null,
        size_color integer not null,
        primary key (product_id, size_color)
    ) engine=InnoDB;

    create table purchase (
       id integer not null,
        delivered bit,
        delivery_date date,
        delivery_time time,
        purchase_date date,
        purchase_time time,
        client integer,
        store integer,
        primary key (id)
    ) engine=InnoDB;

    create table purchase_item (
       id integer not null,
        price double precision,
        quantity integer,
        product integer not null,
        size_and_color integer not null,
        item integer,
        primary key (id)
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
       add constraint Uc_username unique (username);

    alter table size_and_color
       add constraint uc_size_color unique (size, color);

    alter table admin_user
       add constraint fk_adminuser_appuser
       foreign key (id)
       references app_user (id);

    alter table brand_user
       add constraint fk_brand_user_brand
       foreign key (brand)
       references brand (id);

    alter table brand_user
       add constraint fk_branduser_appuser
       foreign key (id)
       references app_user (id);

    alter table cart
       add constraint fk_cart_client
       foreign key (client)
       references client (id);

    alter table cart_item
       add constraint fk_cart_item_product
       foreign key (product)
       references product (id);

    alter table cart_item
       add constraint fk_cart_item_size_color
       foreign key (size_and_color)
       references size_and_color (id);

    alter table cart_item
       add constraint fk_cart_store
       foreign key (store)
       references store (id);

    alter table cart_item
       add constraint fk_cart_item_cart
       foreign key (cart)
       references cart (id);

    alter table client
       add constraint fk_client_cart
       foreign key (current_cart)
       references cart (id);

    alter table client
       add constraint fk_client_appuser
       foreign key (id)
       references app_user (id);

    alter table hoodie
       add constraint fk_hoodie_product
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

    alter table purchase
       add constraint fk_purchase_client
       foreign key (client)
       references client (id);

    alter table purchase
       add constraint fk_purchase_store
       foreign key (store)
       references store (id);

    alter table purchase_item
       add constraint fk_purchase_item_product
       foreign key (product)
       references product (id);

    alter table purchase_item
       add constraint fk_purchase_item_size_color
       foreign key (size_and_color)
       references size_and_color (id);

    alter table purchase_item
       add constraint fk_purchase_purchaseitem
       foreign key (item)
       references purchase (id);

    alter table shirt
       add constraint fk_shirt_product
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
       add constraint fk_storeuser_appuser
       foreign key (id)
       references app_user (id);

    alter table trousers
       add constraint fk_trousers_product
       foreign key (id)
       references product (id);






insert into size_and_color (id, size, color) values(1, "XS", "FDFEFE");
insert into size_and_color (id, size, color) values(2, "XS", "17202A");
insert into size_and_color (id, size, color) values(3, "XS", "6E2C00");
insert into size_and_color (id, size, color) values(4, "XS", "6C3483");
insert into size_and_color (id, size, color) values(5, "XS", "154360");
insert into size_and_color (id, size, color) values(6, "XS", "3498DB");
insert into size_and_color (id, size, color) values(7, "XS", "145A32");
insert into size_and_color (id, size, color) values(8, "XS", "58D68D");
insert into size_and_color (id, size, color) values(9, "XS", "F4D03F");
insert into size_and_color (id, size, color) values(10, "XS", "E67E22");
insert into size_and_color (id, size, color) values(11, "XS", "E74C3C");
insert into size_and_color (id, size, color) values(12, "XS", "F5B7B1");
insert into size_and_color (id, size, color) values(13, "XS", "5D6D7E");
insert into size_and_color (id, size, color) values(14, "S", "FDFEFE");
insert into size_and_color (id, size, color) values(15, "S", "17202A");
insert into size_and_color (id, size, color) values(16, "S", "6E2C00");
insert into size_and_color (id, size, color) values(17, "S", "6C3483");
insert into size_and_color (id, size, color) values(18, "S", "154360");
insert into size_and_color (id, size, color) values(19, "S", "3498DB");
insert into size_and_color (id, size, color) values(20, "S", "145A32");
insert into size_and_color (id, size, color) values(21, "S", "58D68D");
insert into size_and_color (id, size, color) values(22, "S", "F4D03F");
insert into size_and_color (id, size, color) values(23, "S", "E67E22");
insert into size_and_color (id, size, color) values(24, "S", "E74C3C");
insert into size_and_color (id, size, color) values(25, "S", "F5B7B1");
insert into size_and_color (id, size, color) values(26, "S", "5D6D7E");
insert into size_and_color (id, size, color) values(27, "M", "FDFEFE");
insert into size_and_color (id, size, color) values(28, "M", "17202A");
insert into size_and_color (id, size, color) values(29, "M", "6E2C00");
insert into size_and_color (id, size, color) values(30, "M", "6C3483");
insert into size_and_color (id, size, color) values(31, "M", "154360");
insert into size_and_color (id, size, color) values(32, "M", "3498DB");
insert into size_and_color (id, size, color) values(33, "M", "145A32");
insert into size_and_color (id, size, color) values(34, "M", "58D68D");
insert into size_and_color (id, size, color) values(35, "M", "F4D03F");
insert into size_and_color (id, size, color) values(36, "M", "E67E22");
insert into size_and_color (id, size, color) values(37, "M", "E74C3C");
insert into size_and_color (id, size, color) values(38, "M", "F5B7B1");
insert into size_and_color (id, size, color) values(39, "M", "5D6D7E");
insert into size_and_color (id, size, color) values(40, "L", "FDFEFE");
insert into size_and_color (id, size, color) values(41, "L", "17202A");
insert into size_and_color (id, size, color) values(42, "L", "6E2C00");
insert into size_and_color (id, size, color) values(43, "L", "6C3483");
insert into size_and_color (id, size, color) values(44, "L", "154360");
insert into size_and_color (id, size, color) values(45, "L", "3498DB");
insert into size_and_color (id, size, color) values(46, "L", "145A32");
insert into size_and_color (id, size, color) values(47, "L", "58D68D");
insert into size_and_color (id, size, color) values(48, "L", "F4D03F");
insert into size_and_color (id, size, color) values(49, "L", "E67E22");
insert into size_and_color (id, size, color) values(50, "L", "E74C3C");
insert into size_and_color (id, size, color) values(51, "L", "F5B7B1");
insert into size_and_color (id, size, color) values(52, "L", "5D6D7E");
insert into size_and_color (id, size, color) values(53, "XL", "FDFEFE");
insert into size_and_color (id, size, color) values(54, "XL", "17202A");
insert into size_and_color (id, size, color) values(55, "XL", "6E2C00");
insert into size_and_color (id, size, color) values(56, "XL", "6C3483");
insert into size_and_color (id, size, color) values(57, "XL", "154360");
insert into size_and_color (id, size, color) values(58, "XL", "3498DB");
insert into size_and_color (id, size, color) values(59, "XL", "145A32");
insert into size_and_color (id, size, color) values(60, "XL", "58D68D");
insert into size_and_color (id, size, color) values(61, "XL", "F4D03F");
insert into size_and_color (id, size, color) values(62, "XL", "E67E22");
insert into size_and_color (id, size, color) values(63, "XL", "E74C3C");
insert into size_and_color (id, size, color) values(64, "XL", "F5B7B1");
insert into size_and_color (id, size, color) values(65, "XL", "5D6D7E");
insert into size_and_color (id, size, color) values(66, "XXL", "FDFEFE");
insert into size_and_color (id, size, color) values(67, "XXL", "17202A");
insert into size_and_color (id, size, color) values(68, "XXL", "6E2C00");
insert into size_and_color (id, size, color) values(69, "XXL", "6C3483");
insert into size_and_color (id, size, color) values(70, "XXL", "154360");
insert into size_and_color (id, size, color) values(71, "XXL", "3498DB");
insert into size_and_color (id, size, color) values(72, "XXL", "145A32");
insert into size_and_color (id, size, color) values(73, "XXL", "58D68D");
insert into size_and_color (id, size, color) values(74, "XXL", "F4D03F");
insert into size_and_color (id, size, color) values(75, "XXL", "E67E22");
insert into size_and_color (id, size, color) values(76, "XXL", "E74C3C");
insert into size_and_color (id, size, color) values(77, "XXL", "F5B7B1");
insert into size_and_color (id, size, color) values(78, "XXL", "5D6D7E");
insert into size_and_color (id, size, color) values(79, "30", "FDFEFE");
insert into size_and_color (id, size, color) values(80, "30", "17202A");
insert into size_and_color (id, size, color) values(81, "30", "6E2C00");
insert into size_and_color (id, size, color) values(82, "30", "6C3483");
insert into size_and_color (id, size, color) values(83, "30", "154360");
insert into size_and_color (id, size, color) values(84, "30", "3498DB");
insert into size_and_color (id, size, color) values(85, "30", "145A32");
insert into size_and_color (id, size, color) values(86, "30", "58D68D");
insert into size_and_color (id, size, color) values(87, "30", "F4D03F");
insert into size_and_color (id, size, color) values(88, "30", "E67E22");
insert into size_and_color (id, size, color) values(89, "30", "E74C3C");
insert into size_and_color (id, size, color) values(90, "30", "F5B7B1");
insert into size_and_color (id, size, color) values(91, "30", "5D6D7E");
insert into size_and_color (id, size, color) values(92, "32", "FDFEFE");
insert into size_and_color (id, size, color) values(93, "32", "17202A");
insert into size_and_color (id, size, color) values(94, "32", "6E2C00");
insert into size_and_color (id, size, color) values(95, "32", "6C3483");
insert into size_and_color (id, size, color) values(96, "32", "154360");
insert into size_and_color (id, size, color) values(97, "32", "3498DB");
insert into size_and_color (id, size, color) values(98, "32", "145A32");
insert into size_and_color (id, size, color) values(99, "32", "58D68D");
insert into size_and_color (id, size, color) values(100, "32", "F4D03F");
insert into size_and_color (id, size, color) values(101, "32", "E67E22");
insert into size_and_color (id, size, color) values(102, "32", "E74C3C");
insert into size_and_color (id, size, color) values(103, "32", "F5B7B1");
insert into size_and_color (id, size, color) values(104, "32", "5D6D7E");
insert into size_and_color (id, size, color) values(105, "34", "FDFEFE");
insert into size_and_color (id, size, color) values(106, "34", "17202A");
insert into size_and_color (id, size, color) values(107, "34", "6E2C00");
insert into size_and_color (id, size, color) values(108, "34", "6C3483");
insert into size_and_color (id, size, color) values(109, "34", "154360");
insert into size_and_color (id, size, color) values(110, "34", "3498DB");
insert into size_and_color (id, size, color) values(111, "34", "145A32");
insert into size_and_color (id, size, color) values(112, "34", "58D68D");
insert into size_and_color (id, size, color) values(113, "34", "F4D03F");
insert into size_and_color (id, size, color) values(114, "34", "E67E22");
insert into size_and_color (id, size, color) values(115, "34", "E74C3C");
insert into size_and_color (id, size, color) values(116, "34", "F5B7B1");
insert into size_and_color (id, size, color) values(117, "34", "5D6D7E");
insert into size_and_color (id, size, color) values(118, "36", "FDFEFE");
insert into size_and_color (id, size, color) values(119, "36", "17202A");
insert into size_and_color (id, size, color) values(120, "36", "6E2C00");
insert into size_and_color (id, size, color) values(121, "36", "6C3483");
insert into size_and_color (id, size, color) values(122, "36", "154360");
insert into size_and_color (id, size, color) values(123, "36", "3498DB");
insert into size_and_color (id, size, color) values(124, "36", "145A32");
insert into size_and_color (id, size, color) values(125, "36", "58D68D");
insert into size_and_color (id, size, color) values(126, "36", "F4D03F");
insert into size_and_color (id, size, color) values(127, "36", "E67E22");
insert into size_and_color (id, size, color) values(128, "36", "E74C3C");
insert into size_and_color (id, size, color) values(129, "36", "F5B7B1");
insert into size_and_color (id, size, color) values(130, "36", "5D6D7E");
insert into size_and_color (id, size, color) values(131, "38", "FDFEFE");
insert into size_and_color (id, size, color) values(132, "38", "17202A");
insert into size_and_color (id, size, color) values(133, "38", "6E2C00");
insert into size_and_color (id, size, color) values(134, "38", "6C3483");
insert into size_and_color (id, size, color) values(135, "38", "154360");
insert into size_and_color (id, size, color) values(136, "38", "3498DB");
insert into size_and_color (id, size, color) values(137, "38", "145A32");
insert into size_and_color (id, size, color) values(138, "38", "58D68D");
insert into size_and_color (id, size, color) values(139, "38", "F4D03F");
insert into size_and_color (id, size, color) values(140, "38", "E67E22");
insert into size_and_color (id, size, color) values(141, "38", "E74C3C");
insert into size_and_color (id, size, color) values(142, "38", "F5B7B1");
insert into size_and_color (id, size, color) values(143, "38", "5D6D7E");
insert into size_and_color (id, size, color) values(144, "40", "FDFEFE");
insert into size_and_color (id, size, color) values(145, "40", "17202A");
insert into size_and_color (id, size, color) values(146, "40", "6E2C00");
insert into size_and_color (id, size, color) values(147, "40", "6C3483");
insert into size_and_color (id, size, color) values(148, "40", "154360");
insert into size_and_color (id, size, color) values(149, "40", "3498DB");
insert into size_and_color (id, size, color) values(150, "40", "145A32");
insert into size_and_color (id, size, color) values(151, "40", "58D68D");
insert into size_and_color (id, size, color) values(152, "40", "F4D03F");
insert into size_and_color (id, size, color) values(153, "40", "E67E22");
insert into size_and_color (id, size, color) values(154, "40", "E74C3C");
insert into size_and_color (id, size, color) values(155, "40", "F5B7B1");
insert into size_and_color (id, size, color) values(156, "40", "5D6D7E");
insert into size_and_color (id, size, color) values(157, "42", "FDFEFE");
insert into size_and_color (id, size, color) values(158, "42", "17202A");
insert into size_and_color (id, size, color) values(159, "42", "6E2C00");
insert into size_and_color (id, size, color) values(160, "42", "6C3483");
insert into size_and_color (id, size, color) values(161, "42", "154360");
insert into size_and_color (id, size, color) values(162, "42", "3498DB");
insert into size_and_color (id, size, color) values(163, "42", "145A32");
insert into size_and_color (id, size, color) values(164, "42", "58D68D");
insert into size_and_color (id, size, color) values(165, "42", "F4D03F");
insert into size_and_color (id, size, color) values(166, "42", "E67E22");
insert into size_and_color (id, size, color) values(167, "42", "E74C3C");
insert into size_and_color (id, size, color) values(168, "42", "F5B7B1");
insert into size_and_color (id, size, color) values(169, "42", "5D6D7E");
insert into size_and_color (id, size, color) values(170, "44", "FDFEFE");
insert into size_and_color (id, size, color) values(171, "44", "17202A");
insert into size_and_color (id, size, color) values(172, "44", "6E2C00");
insert into size_and_color (id, size, color) values(173, "44", "6C3483");
insert into size_and_color (id, size, color) values(174, "44", "154360");
insert into size_and_color (id, size, color) values(175, "44", "3498DB");
insert into size_and_color (id, size, color) values(176, "44", "145A32");
insert into size_and_color (id, size, color) values(177, "44", "58D68D");
insert into size_and_color (id, size, color) values(178, "44", "F4D03F");
insert into size_and_color (id, size, color) values(179, "44", "E67E22");
insert into size_and_color (id, size, color) values(180, "44", "E74C3C");
insert into size_and_color (id, size, color) values(181, "44", "F5B7B1");
insert into size_and_color (id, size, color) values(182, "44", "5D6D7E");
insert into size_and_color (id, size, color) values(183, "46", "FDFEFE");
insert into size_and_color (id, size, color) values(184, "46", "17202A");
insert into size_and_color (id, size, color) values(185, "46", "6E2C00");
insert into size_and_color (id, size, color) values(186, "46", "6C3483");
insert into size_and_color (id, size, color) values(187, "46", "154360");
insert into size_and_color (id, size, color) values(188, "46", "3498DB");
insert into size_and_color (id, size, color) values(189, "46", "145A32");
insert into size_and_color (id, size, color) values(190, "46", "58D68D");
insert into size_and_color (id, size, color) values(191, "46", "F4D03F");
insert into size_and_color (id, size, color) values(192, "46", "E67E22");
insert into size_and_color (id, size, color) values(193, "46", "E74C3C");
insert into size_and_color (id, size, color) values(194, "46", "F5B7B1");
insert into size_and_color (id, size, color) values(195, "46", "5D6D7E");








insert into app_user (is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, username, user_role, id)
    values (1, 1, 1, 1, "$2a$10$Dxt/A1E6rwmzL7lBzWLmyuplznabAKjhi5Z5V03d9aQDWaqoCi36O", "admin1", "admin", 1);

insert into admin_user (id) values(1);



insert into brand (email_domain, email_user, name, id)
    values ("manager", "levis", "Levi's", 1);
insert into app_user (is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, username, user_role, id)
    values (1, 1, 1, 1, "$2a$10$20pDGKniGzu6nmHQ0FP1jO0F2E2gHLYVx0YWMVkYxSACBg3Kc7mhW", "levis", "brand", 2);
insert into brand_user (brand, id)
    values (1, 2);

insert into brand (email_domain, email_user, name, id)
    values ("manager", "tommy", "Tommy Hilfiger", 2);
insert into app_user (is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, username, user_role, id)
    values (1, 1, 1, 1, "$2a$10$qP.pEr.apRmLlT/rCO0NMeNVZc6EJ/RVTb0JgmlTracvYFH7m0i66", "tommy", "brand", 3);
insert into brand_user (brand, id)
    values (2, 3);

insert into brand (email_domain, email_user, name, id)
    values ("manager", "polo", "Polo Ralph Lauren", 3);
insert into app_user (is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, username, user_role, id)
    values (1, 1, 1, 1, "$2a$10$P4ydne7gLLFxc2ObL0Im6uhE8WQQ91Hgvv40Xa7wnvG22eXpeiid2", "polo", "brand", 4);
insert into brand_user (brand, id)
    values (3, 4);



insert into store (door_number, optional, street, zip_code, email_domain, email_user, number, id)
    values (2205, "", "8 de Octubre", 50000, "tienda", "tienda", 25047630, 1);
insert into store_brand(store_id, brand_id)
    values (1, 1);
insert into store_brand(store_id, brand_id)
    values (1, 3);

insert into app_user (is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, username, user_role, id)
    values (1, 1, 1, 1, "$2a$10$Xx6Bo5m1j/nlPFPu9LHm1uNp2aRQwmzfbH.uH9Y9eyWq5Ieo7CJgC", "tienda1", "store", 5);
insert into store_user (store, id)
    values (1, 5);

insert into store (door_number, optional, street, zip_code, email_domain, email_user, number, id)
    values (2210, "", "8 de Octubre", 50000, "tienda2", "tienda2", 25047631, 2);
insert into store_brand(store_id, brand_id)
    values (2, 2);
    insert into store_brand(store_id, brand_id)
    values (2, 1);

insert into app_user (is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, username, user_role, id)
    values (1, 1, 1, 1, "$2a$10$QigSTVpVsB2dLrRDSsKxD.qkf3rVmUOKR30re5sf/TpFNcd1zLC2K", "tienda2", "store", 6);
insert into store_user (store, id)
    values (2, 6);


update hibernate_sequence set next_val= 10 where next_val=1;

select next_val as id_val from hibernate_sequence;



