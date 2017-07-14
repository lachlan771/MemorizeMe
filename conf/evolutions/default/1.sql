# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table abstract_card (
  dtype                         varchar(31) not null,
  id                            uuid not null,
  response_quality              integer,
  rep_num                       decimal(38),
  e_factor                      decimal(38),
  creation_date                 timestamp,
  next_rep                      timestamp,
  deck_id                       varchar(255),
  question                      varchar(255),
  answer                        varchar(255),
  constraint pk_abstract_card primary key (id)
);

create table deck (
  id                            varchar(255) not null,
  name                          varchar(255),
  total_cards                   integer,
  total_review_cards            integer,
  profile_email                 varchar(255),
  constraint pk_deck primary key (id)
);

create table profile (
  email                         varchar(255) not null,
  user_name                     varchar(255),
  password                      varchar(255),
  full_name                     varchar(255),
  constraint pk_profile primary key (email)
);

alter table abstract_card add constraint fk_abstract_card_deck_id foreign key (deck_id) references deck (id) on delete restrict on update restrict;
create index ix_abstract_card_deck_id on abstract_card (deck_id);

alter table deck add constraint fk_deck_profile_email foreign key (profile_email) references profile (email) on delete restrict on update restrict;
create index ix_deck_profile_email on deck (profile_email);


# --- !Downs

alter table abstract_card drop constraint if exists fk_abstract_card_deck_id;
drop index if exists ix_abstract_card_deck_id;

alter table deck drop constraint if exists fk_deck_profile_email;
drop index if exists ix_deck_profile_email;

drop table if exists abstract_card;

drop table if exists deck;

drop table if exists profile;

