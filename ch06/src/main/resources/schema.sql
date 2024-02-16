create table if not exists courses (
  id          INT NOT NULL,
  name        VARCHAR(255),
  category    VARCHAR(255),
  rating      int NOT NULL,
  description VARCHAR(255),
  PRIMARY KEY (id)
);


create table if not exists users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

create table if not exists authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references
                             users(username)
);

create unique index ix_auth_username on authorities (username,authority);


create table if not exists ct_users(
                         ID    BIGINT    NOT NULL,
                         EMAIL    VARCHAR(255)    NOT NULL,
                         FIRST_NAME    VARCHAR(255) NOT NULL,
                         LAST_NAME    VARCHAR(255) NOT NULL,
                         PASSWORD    VARCHAR(255) NOT NULL,
                         USERNAME    VARCHAR(255) NOT NULL,
                         VERIFIED    BOOLEAN NOT NULL,
                         LOCKED BOOLEAN NOT NULL,
                         ACC_CRED_EXPIRED BOOLEAN NOT NULL,
                         PRIMARY KEY (ID)
);