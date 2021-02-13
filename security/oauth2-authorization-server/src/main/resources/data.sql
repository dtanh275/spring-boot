drop table if exists roles_privileges;
drop table if exists users_roles;
drop table if exists privileges;
drop table if exists roles;
drop table if exists users;

create table privileges (id bigint not null, name varchar(255), primary key (id));
create table roles (id bigint not null, name varchar(255), primary key (id));
create table roles_privileges (role_id bigint not null, privilege_id bigint not null);
create table users (id bigint not null, email varchar(255), password varchar(255), username varchar(255), primary key (id));
create table users_roles (user_id bigint not null, role_id bigint not null);
alter table privileges drop constraint if exists UK_m2tnonbcaquofx1ccy060ejyc;
alter table privileges add constraint UK_m2tnonbcaquofx1ccy060ejyc unique (name);
alter table roles drop constraint if exists UK_ofx66keruapi6vyqpv6f2or37;
alter table roles add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);
alter table users drop constraint if exists UK_6dotkott2kjsp8vw4d0m25fb7;
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table roles_privileges add constraint FK5duhoc7rwt8h06avv41o41cfy foreign key (privilege_id) references privileges;
alter table roles_privileges add constraint FK629oqwrudgp5u7tewl07ayugj foreign key (role_id) references roles;
alter table users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles;
alter table users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users;

insert into privileges (id, name) values
    (1, 'READ_PRIVILEGE'),
    (2, 'WRITE_PRIVILEGE');

insert into roles (id, name) values
    (1, 'ROLE_USER'),
    (2, 'ROLE_ADMIN');

insert into roles_privileges (role_id, privilege_id) values
    (1, 1),
    (2, 1),
    (2, 2);

insert into users (id, email, password, username) values
    (1, 'geralt@thejavaman.dev', '$2y$10$L53bDadTg5bNqwO8kvXokO0eKSD.P7nUFzeYTmXHCrhFy4tecByUO', 'Geralt'),
    (2, 'ciri@thejavaman.dev', '$2y$10$L53bDadTg5bNqwO8kvXokO0eKSD.P7nUFzeYTmXHCrhFy4tecByUO', 'Ciri');

insert into users_roles(user_id, role_id) values
    (1, 2),
    (2, 1);
