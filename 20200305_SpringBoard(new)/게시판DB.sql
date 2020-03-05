/*
  
 --일련번호 관리 객체

create sequence seq_board_new_idx
  
 --게시판 테이블
 create table board_new
 (
 	idx	    	int,   			--일련번호
 	subject		varchar2(500),	--제목
 	content 	clob,			--내용
 	ip			varchar2(100),	--아이피
 	regdate		date,			--작성일자
	readhit		int,			--조회수 	
 	ues_yu		char(1),		--사용유무(삭제)
 	ref			int,			--참조글번호
 	step		int,			--순서
 	depth		int,			--깊이
 	m_idx		int,			--회원일련번호
 	m_name		varchar2(100)	--회원명
 )   
 
 --기본키
alter table board_new
 	add constraint pk_board_new_idx	primary	key(idx);
 	
 --참조키
 alter table board_new
 	add constraint fk_board_new_m_idx foreign key(m_idx)
 								   references member(idx)
 								   on delete cascade
      
 --sample insert
insert into board_new values(seq_board_new_idx.nextVal,
						 '내가 1등',
						 '일등했다',
						 '192.188.0.76',
						 sysdate,
						 0,
						 'y',
						 seq_board_new_idx.currVal,
						 0,
						 0,
						 2,
						 '일길동');
						 
--삭제게시물
insert into board_new values(seq_board_new_idx.nextVal,
						 '난 몇등이지?',
						 '일등했다',
						 '192.188.0.76',
						 sysdate,
						 0,
						 'n',
						 seq_board_new_idx.currVal,
						 0,
						 0,
						 2,
						 '일길동');						 

  
  
 */