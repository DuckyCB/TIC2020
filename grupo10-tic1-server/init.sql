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