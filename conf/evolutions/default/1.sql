# --- First database schema

# --- !Ups

set ignorecase true;

create table company (
  id                        bigint not null,
  name                      varchar(255) not null,
  constraint pk_company primary key (id))
;

create table image (
  id                        bigint not null,
  link                      varchar(255) not null,
  constraint pk_image primary key (id))
;

create table computer (
  id                        bigint not null,
  name                      varchar(255) not null,
  introduced                timestamp,
  discontinued              timestamp,
  company_id                bigint,
  image_id                  bigint,
  constraint pk_computer primary key (id))
;

create sequence company_seq start with 1000;
create sequence image_seq start with 1000;
create sequence computer_seq start with 1000;

alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
alter table computer add constraint fk_computer_image_1 foreign key (image_id) references image (id) on delete restrict on update restrict;

create index ix_computer_company_1 on computer (company_id);
create index ix_computer_image_1 on computer (image_id);

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists company;
drop table if exists image;
drop table if exists computer;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists company_seq;
drop sequence if exists image_seq;
drop sequence if exists computer_seq;

