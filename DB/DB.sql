show databases;
drop database shopdb;

create database shopdb;
use shopdb;
show tables;



-- 상품, 상품 카트 DB ---------------------------------------------------------------------------------------------------------
insert into member_tb(id, password) values('id1', 'password1');
insert into member_tb(id, password) values('id2', 'password2');
insert into member_tb(id, password) values('id3', 'password3');
insert into member_tb(id, password) values('id4', 'password4');
insert into cart(member_1) values('1');
insert into cart(member_1) values('2');
insert into cart(member_1) values('3');
insert into cart(member_1) values('4');
insert into cartItems(item_no, cart_no) values (1, 1), (2, 1), (2, 1), (2, 1),(2, 1),(2, 1),(2, 2),(2, 2),(2, 2),(2, 2),(3, 2),(3, 2),(3, 2),(1, 3),(1, 3),(1, 3),(1, 3),(2, 3),(2, 3),(2, 3),(4, 3),(4, 3),(4, 3),(4, 3);
insert into items (item, member_no) values ('item1', 1),
('item2', 2),
('item3', 1),
('item4', 2)
;

create table shopdb.member_tb(
	member_tb_no int not null auto_increment,
    id varchar(45) unique, 
    password varchar(45) not null,
    primary key (member_tb_no)
);

create table shopdb.cart(
	cart_no int not null auto_increment,
    member_1 int not null unique,
    primary key (cart_no)
);
alter table cart add constraint cart_member foreign key (member_1) references member_tb(member_tb_no);

create table shopdb.cartItems(
	cartItems_no int not null auto_increment,
    item_no int,
    cart_no int,
    primary key(cartItems_no)
);
alter table cartItems add constraint cartItems_cart foreign key (cart_no) references cart(cart_no);
alter table cartItems add constraint cartItems_items foreign key (item_no) references items(item_no);

create table shopdb.items(
	item_no int not null auto_increment,
    item varchar(255) not null,
    member_no int,
    primary key (item_no)
);
alter table items add constraint items_member_tb_1 foreign key (member_no) references member_tb(member_tb_no);

-- 상품, 상품 카트 DB 간의 join

-- 1. 멤버별 담은 상품 보여주기
select member_tb.id '멤버명', cart.cart_no '카트번호', items.item from member_tb inner join cart on member_tb.member_tb_no=cart.member_1 inner join cartItems on cart.cart_no=cartItems.cart_no inner join items on cartItems.item_no = items.item_no;

-- 게시글 DB ------------------------------------------------------------------------------------------------------

create table board_tb(
	board_no int auto_increment,
    board_title varchar(200),
    board_text varchar(600),
	board_time timestamp   NOT NULL	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    board_writer varchar(200) not null,
    youtubeLink varchar(600),
    likes int,
    views int,
    primary key (board_no)
);
drop table board_tb;
desc board_tb;
select * from board_tb;

insert into board_tb (board_title, board_text, board_writer, youtubeLink) values('b1', 'text1', 'writer1', 'Link1');
insert into board_tb (board_title, board_text, board_writer, youtubeLink) values('b2', 'text2', 'writer2', 'Link2');
insert into board_tb (board_title, board_text, board_writer, youtubeLink) values('b3', 'text3', 'writer3', 'Link3');
insert into board_tb (board_title, board_text, board_writer, youtubeLink) values('b4', 'text4', 'writer4', 'Link4');
insert into board_tb (board_title, board_text, board_writer, youtubeLink) values('b5', 'text5', 'writer5', 'Link5');
insert into board_tb (board_title, board_text, board_writer, youtubeLink) values('b6', 'text6', 'writer6', 'Link6');
insert into board_tb (board_title, board_text, board_writer, youtubeLink) values('b7', 'text7', 'writer7', 'Link7');

create table comment_tb(
	comment_no int auto_increment,
    comment_text varchar(600) not null,
    comment_time timestamp not null default current_timestamp,
    comment_writer varchar(200) not null,
    board_no int not null,
    primary key(comment_no)
);
desc comment_tb;
drop table comment_tb;
select * from comment_tb;
alter table comment_tb add constraint commentToboard foreign key(board_no) references board_tb(board_no);

insert into comment_tb(comment_text, comment_writer, board_no) values('text1', 'writer1', 1);
insert into comment_tb(comment_text, comment_writer, board_no) values('text2', 'writer2', 1);
insert into comment_tb(comment_text, comment_writer, board_no) values('text3', 'writer3', 2);
insert into comment_tb(comment_text, comment_writer, board_no) values('text4', 'writer4', 3);
insert into comment_tb(comment_text, comment_writer, board_no) values('text5', 'writer5', 2);
insert into comment_tb(comment_text, comment_writer, board_no) values('text6', 'writer6', 2);
insert into comment_tb(comment_text, comment_writer, board_no) values('text7', 'writer7', 2);
insert into comment_tb(comment_text, comment_writer, board_no) values('text8', 'writer8', 2);
insert into comment_tb(comment_text, comment_writer, board_no) values('text9', 'writer9', 6);
insert into comment_tb(comment_text, comment_writer, board_no) values('text10', 'writer10', 7);
insert into comment_tb(comment_text, comment_writer, board_no) values('text11', 'writer11', 4);
insert into comment_tb(comment_text, comment_writer, board_no) values('text12', 'writer12', 4);
insert into comment_tb(comment_text, comment_writer, board_no) values('text13', 'writer13', 4);
insert into comment_tb(comment_text, comment_writer, board_no) values('text14', 'writer14', 4);
insert into comment_tb(comment_text, comment_writer, board_no) values('text15', 'writer15', 5);

-- board_tb 과 comment_tb join





















