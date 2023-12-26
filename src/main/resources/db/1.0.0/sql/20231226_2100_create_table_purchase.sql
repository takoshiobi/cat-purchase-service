create schema if not exists straycats;

create table if not exists straycats.purchase
(
    id                serial,
    create_ts         timestamp not null,
    update_ts         timestamp,
    status            varchar(255) not null,
    description       varchar(1500),

    constraint pk_purchase primary key (id)
);