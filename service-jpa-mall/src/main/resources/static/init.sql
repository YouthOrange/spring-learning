create table vip
(
  id         bigint auto_increment primary key,
  name       varchar(10) not null,
  tel        varchar(11) not null,
  integral   bigint      not null,
  totalMoney double      not null,
  create_at   timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
  update_at  timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create index name_index on vip (name);


create table record
(
  id        bigint auto_increment primary key,
  vip_id    bigint       not null,
  money     double       not null,
  goods     varchar(500) not null,
  create_at  timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
  index vip_id_create_at (vip_id, create_at)
) ;


create index vip_id on vip(vip_id);


create table goods(
  id bigint not null  auto_increment primary key ,
  name varchar(50) not null ,
  price double not null ,
  count int not null default 0,
  origin varchar(50) not null ,
  type varchar(10) not null ,
  create_at  timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
  update_at timestamp    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  index type_update_at(type,update_at)
);