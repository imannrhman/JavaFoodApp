create or replace table bahan_baku
(
    id              int auto_increment
    constraint `PRIMARY`
    primary key,
    kode_bahan      varchar(20)  null,
    nama_bahan      varchar(256) not null,
    harga           double       not null,
    kategori_barang varchar(256) not null,
    stok            int(20)      not null,
    constraint bahan_baku_kode_bahan_uindex
    unique (kode_bahan)
);

create or replace table menu_makanan
(
    id           int auto_increment
    constraint `PRIMARY`
    primary key,
    kode_menu    varchar(20)  null,
    menu_makanan varchar(256) null,
    harga        double       null
);

create or replace table menu_bahan
(
    id         int auto_increment
    constraint `PRIMARY`
    primary key,
    id_bahan   int null,
    id_makanan int null,
    constraint menu_bahan_bahan_baku_id_fk
    foreign key (id_bahan) references bahan_baku (id)
    on delete set null,
    constraint menu_bahan_menu_makanan_id_fk
    foreign key (id_makanan) references menu_makanan (id)
    on delete cascade
);

create or replace table role
(
    id   int auto_increment
    constraint `PRIMARY`
    primary key,
    name varchar(256) null
);

create or replace table transaksi
(
    id             int auto_increment
    constraint `PRIMARY`
    primary key,
    kode_transaksi varchar(20) null,
    tanggal        date        null,
    status         varchar(50) null
);

create or replace table transaksi_menu
(
    id           int auto_increment
    constraint `PRIMARY`
    primary key,
    id_transaksi int null,
    id_menu      int null,
    constraint transaksi_menu_menu_makanan_id_fk
    foreign key (id_menu) references menu_makanan (id)
    on delete set null,
    constraint transaksi_menu_transaksi_id_fk
    foreign key (id_transaksi) references transaksi (id)
);

create or replace table users
(
    id        int auto_increment
    constraint `PRIMARY`
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
    id      int auto_increment
    constraint `PRIMARY`
    primary key,
    id_user int         null,
    tanggal datetime    null,
    status  varchar(50) null,
    constraint presensi_users_id_fk
    foreign key (id_user) references users (id)
);

create or replace procedure inputMenuBahan(IN id_menuIn int, IN namaBahan varchar(255))
begin
    declare id_bahan_var int(11);
select id into id_bahan_var from bahan_baku where nama_bahan = namaBahan;
insert into menu_bahan (id_bahan, id_makanan)  values (id_bahan_var, id_menuIn);
end;

create or replace procedure registerUser(IN fullNameIn varchar(255), IN usernameIn varchar(255),
                                         IN passwordIn varchar(255), IN phoneIn varchar(20), IN roleName varchar(255))
BEGIN
    declare roleId int(8);

select id into roleId from role where role.name = roleName;

insert into users (full_name, username, password, phone, role_id)
values (fullNameIn, usernameIn, passwordIn, phoneIn, roleId);
end;

