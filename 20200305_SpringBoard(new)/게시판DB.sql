/*
  
 --일련번호 관리 객체

create sequence seq_board_new_idx

  
--삭제
drop table board_new  
        
 --게시판 테이블
 create table board_new
 (
 	idx	    	int,   			--일련번호
 	subject		varchar2(500),	--제목
 	content 	clob,			--내용
 	ip			varchar2(100),	--아이피
 	regdate		date,			--작성일자
	readhit		int,			--조회수 	
 	use_yu		char(1),		--사용유무(삭제)
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
						 
-- 답글쓰기
insert into board_new values(seq_board_new_idx.nextVal,
						'좋겠다 1등해서',
						 '난 2등했다',
						 '192.188.0.76',
						 sysdate,
						 0,
						 'y',
						 1,
						 1,
						 1,
						 3,
						 '김관리');						 									 

--답글쓰기
insert into board_new values(seq_board_new_idx.nextVal,
			 			 '대답좀해봐',
						 '난 2등이라구~',
						 '192.188.0.76',
						 sysdate,
						 0,
						 'y',
						 1,
						 2,
						 2,
						 3,
						 '김관리');

  
update board_new set use_yu='n' where idx=1;
update board_new set use_yu='y' where idx=4; 
  
  
--Paging 처리를 위한 SQL
select * from
(
	select   
	rank() over(order by ref desc,step asc) no, 
    b.*,
    ( select count(*) from comment_tb where b_idx= b.idx ) comment_cnt
	from
	(select * from board) b
)
where no between 6 and 10  
  


select * from comment_tb where b_idx=60


--전체 게시물수
select nvl(count(*),0) from board_new    
  
  

commit

select * from board_new order by ref desc,step asc    
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 */