create or replace table role
(
    id   int auto_increment
    primary key,
    name varchar(256) null
);

create or replace table users
(
    id        int auto_increment
    primary key,
    full_name varchar(256) null,
    username  varchar(256) null,
    password  varchar(256) null,
    phone     varchar(20)  null,
    role_id   int          null,
    constraint users_telephone_uindex
    unique (phone),
    constraint users_role_id_fk
    foreign key (role_id) references role (id)
    on delete set null
);

create or replace table presensi
(
  id_presensi int auto_increment
  primary key,
  id int(8) null
  nama varchar(256) null,
  tanggal date null
);

insert into db_restaurant.role (id, name) values (1, 'Owner');
insert into db_restaurant.role (id, name) values (2, 'Admin');
insert into db_restaurant.role (id, name) values (3, 'Karyawan');
insert into db_restaurant.role (id, name) values (4, 'Customer');
