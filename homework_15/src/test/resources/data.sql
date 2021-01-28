insert into authors (`name`) values ('Steven King');
insert into authors (`name`) values ('Alexandr Pushkin');
insert into authors (`name`) values ('Charles Dickens');

insert into genres (`name`) values ('thriller');
insert into genres (`name`) values ('lyrics');
insert into genres (`name`) values ('novel');
insert into genres (`name`) values ('letter');

--Steven King
insert into books (`name`, id_author, id_genre) values ('Misery',1, 1);
insert into books (`name`, id_author, id_genre) values ('Desperation', 1, 3);
--Alexandr Pushkin
insert into books (`name`, id_author, id_genre) values ('To Chaadaev', 2, 4);
insert into books (`name`, id_author, id_genre) values ('I remember a wonderful moment', 2, 2);
--Charles Dickens
insert into books (`name`, id_author, id_genre) values ('David Copperfield', 3, 3);
--Comment
insert into comments (id_books, text_comment) values (1, 'super book!');
insert into comments (id_books, text_comment) values (1, 'yes, super book!');

insert into comments (id_books, text_comment) values (2, 'Dont read book, but want!');

insert into comments (id_books, text_comment) values (3, 'The teacher made me read(((!');
insert into comments (id_books, text_comment) values (3, 'King is better');
insert into comments (id_books, text_comment) values (3, 'mm, good');

insert into comments (id_books, text_comment) values (4, 'everyone needs to read it!');
insert into comments (id_books, text_comment) values (4, 'I agree with the previous user');

insert into comments (id_books, text_comment) values (5, 'The coolest author, the most interesting book');
insert into comments (id_books, text_comment) values (5, 'I love dickens and this book too');
insert into comments (id_books, text_comment) values (5, 'Very sad book');
insert into comments (id_books, text_comment) values (5, 'I want to read it again');