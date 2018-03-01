CREATE TABLE userinfo
(
    id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 0 NOT NULL,
    name TEXT DEFAULT '',
    password TEXT NOT NULL
);
CREATE UNIQUE INDEX user_id_uindex ON userinfo (id);

insert into userinfo(id, name, password) values(1,	'Won Ji',	'Nu Ri');