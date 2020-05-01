SELECT * FROM board;

CREATE TABLE reboard(
	NO NUMBER PRIMARY KEY,
	refno NUMBER,
	title varchar2(100),
	content varchar2(1000),
	writer varchar2(50),
	readcnt NUMBER,
	credte DATE,
	uptdte DATE,
	etc varchar2(200)
);
-- 파일 저장 정보 테이블
CREATE TABLE boardfile(
	NO NUMBER,
	fname varchar2(200),
	credte DATE,
	uptdte DATE,
	etc varchar2(200)
);
-- insertRepo(String fname);
-- insert into boardfile values(reboard_seq.currval, #{fname}, sysdate, sysdate, '')

-- 글의 sequence
CREATE SEQUENCE  reboard_seq
	MINVALUE 1
	MAXVALUE 9999999
	INCREMENT BY 1
	START WITH 1
	cache 20;
-- 데이터 입력..
INSERT INTO REBOARD values(reboard_seq.nextval, 0, 
'첫번째글등록',
	'내용','홍길동',0,sysdate,sysdate,'');
SELECT * FROM reboard;


