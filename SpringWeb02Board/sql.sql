select * from member

select * from board
-- drop table board purge;
alter table board rename column name to userid;

create table board(
	num number(5) primary key,
	pass varchar2(30),
	userid varchar2(30),
	email varchar2(30),
	title varchar2(50),
	content varchar2(1000),
	readcount number(4) default 0,
	writedate date default sysdate
);
SELECT board_seq.nextVal FROM DUAL;
create sequence board_seq start with 1 increment by 1;
Insert into board(num, name, email, pass, title, content)
values(board_seq.nextVal, '홍길동', 'abcd@naver.com', '1234', '첫방문','반갑습니다');
Insert into board(num, name, email, pass, title, content)
values(board_seq.nextVal, '홍길남', 'addd@naver.com', '1234', '게시판 개설','축하드립니다');



create table spmember(
	id varchar2(30),
	pw varchar2(30),
	name varchar2(30),
	phone1 varchar2(15),
	phone2 varchar2(15),
	phone3 varchar2(15),
	email varchar2(30)
);
insert into spmember values('scott', '1234', '박지성', '010', '6400','6068', 
'hong@naver.com');
insert into spmember values('hong1', '1234', '홍길동', '010', '1111','2222', 
'hong1@naver.com');
insert into spmember values('hong2', '1234', '홍길남', '010', '2222','3333', 
'hong2@naver.com');
insert into spmember values('hong3', '1234', '홍길서', '010', '3333','4444', 
'hong3@naver.com');
insert into spmember values('hong4', '1234', '홍길북', '010', '4444','5555', 
'hong4@naver.com');

select * from reply
select * from board

alter table board add replycnt number(3);

create table reply(
	num number(7) primary key,
	boardnum number(5),
	userid varchar2(20),
	writedate date default sysdate,
	content varchar2(1000)
);
create sequence reply_seq start with 1 increment by 1;
insert into reply(num, boardnum, userid, content) 
values(reply_seq.nextVal, 100, 'one1', '맛있게 잘 먹었습니다')