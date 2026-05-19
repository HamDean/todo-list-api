create table users
(
    id         bigint auto_increment primary key,
    name       varchar(255) not null,
    email      varchar(255) not null,
    password   varchar(255) not null,
    created_at timestamp default (CURRENT_DATE) not null
);

create table todos
(
    id          bigint auto_increment primary key,
    title       varchar(255) not null,
    description longtext null,
    updated_at  timestamp default (CURRENT_DATE) null,
    user_id     bigint null,
    constraint todos_users_id_fk
        foreign key (user_id) references users (id)
);

