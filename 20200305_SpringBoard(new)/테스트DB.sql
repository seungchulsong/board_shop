/*
  
  --일련번호 관리 객체

create sequence seq_homework_idx
   
  
--삭제
drop table homework    
  
  
 --게시판 테이블
create table homework
(
   name  varchar2(100),
   sex   char(6),
   age    number(3),
  salary  int
)
  
  
insert into homework values('John','male', 41, 100000);
    

insert into homework values('Mike', 'male',  52, 120000);
insert into homework values('James','male',  28, 80000);
insert into homework values('Jane', 'female',24, 210000);
insert into homework values('Sally','female',32, 75000);
insert into homework values('Marry','female',57, 140000);    
  
  
 
1. 테이블 생성  
2. 데이터 insert
3. 출력


문제1
우리 회사에서 연봉이 가장 높은사람의 이름을 출력해주세요.

select * from homework where salary = (select max(salary) from homework)


문제2
우리 회사 30,40대(age between 30 and 49) 직원의 평균 연봉을 출력해주세요.


select avg(salary) from homework where age between 30 and 39;
select avg(salary) from homework where age between 40 and 49;
 */*/