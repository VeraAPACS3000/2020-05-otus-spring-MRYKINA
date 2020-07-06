insert into authors (`name`) values ('Steven King');
insert into authors (`name`) values ('Alexandr Pushkin');
insert into authors (`name`) values ('Charles Dickens');

insert into genres (`name`) values ('thriller');
insert into genres (`name`) values ('lyrics');
insert into genres (`name`) values ('novel');
insert into genres (`name`) values ('letter');

--Alexandr Pushkin
insert into books (`name`, id_author, id_genre) values ('To Chaadaev', 2, 4);
insert into books (`name`, id_author, id_genre) values ('I remember a wonderful moment', 2, 2);
--Steven King
insert into books (`name`, id_author, id_genre) values ('Misery',1, 1);
insert into books (`name`, id_author, id_genre) values ('Desperation', 1, 3);
--Charles Dickens
insert into books (`name`, id_author, id_genre) values ('David Copperfield', 3, 3);