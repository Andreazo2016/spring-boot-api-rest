create table users (
  id serial NOT NULL primary key,
  user_name varchar(255) not NULL UNIQUE,
  full_name varchar(255) not NULL,
  password varchar(255) not NULL,
  account_non_expired boolean not NULL,
  account_non_locked boolean not NULL,
  credentials_non_expired boolean not NULL,
  enabled boolean not NULL
);

create table permission (
  id serial not null primary key,
  description varchar(255) not NULL
);

create table users_permission (
  id_user integer NOT NULL primary key,
  id_permission integer NOT NULL,
  FOREIGN KEY (id_user) REFERENCES users (id),
  FOREIGN KEY (id_permission) REFERENCES permission (id)
);

insert into permission (description) VALUES
	('ADMIN'),
	('MANAGER'),
	('COMMON_USER');

insert into users (user_name, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
	('leandro', 'Leandro Costa', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', true, true, true, true),
	('flavio', 'Flavio Costa', '$2a$16$h4yDQCYTy62R6xrtFDWONeMH3Lim4WQuU/aj8hxW.dJJoeyvtEkhK', true, true, true, true);