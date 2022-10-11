conn sys/java1234 as sysdba
drop user modu cascade;

alter session set "_oracle_script"=true;
create user modu identified by modu;
grant connect, resource, create view, unlimited tablespace to modu;
conn modu/modu;

DROP TABLE BOARD_NESTED_REPLY;
DROP TABLE BOARD_REPLY_PICTURE;
DROP TABLE BOARD_REPLY;
DROP TABLE BOARD_FILE;
DROP TABLE BOARD;
DROP TABLE RECIPE_REPLY_PHOTO;
DROP TABLE RECIPE_NESTED_REPLY;
DROP TABLE RECIPE_REPLY;
DROP TABLE SCRAP;
DROP TABLE RATING;
DROP TABLE RECIPE_TAG;
DROP TABLE DIRECTION;
DROP TABLE INGREDIENT;
DROP TABLE RECIPE;
DROP TABLE FOLLOW;
DROP TABLE MEMBER;
--DROP TABLE MEMBER CASCADE CONSTRAINTS

DROP SEQUENCE BOARD_SEQ;
DROP SEQUENCE BOARD_FILE_SEQ;
DROP SEQUENCE BOARD_REPLY_SEQ;
DROP SEQUENCE B_NESTED_REPLY_SEQ;
DROP SEQUENCE B_REPLY_PICTURE_SEQ;
DROP SEQUENCE RATING_SEQ;
DROP SEQUENCE SCRAP_SEQ;
DROP SEQUENCE RECIPE_REPLY_SEQ;
DROP SEQUENCE R_NESTED_REPLY_SEQ;
DROP SEQUENCE R_REPLY_PHOTO_SEQ;
DROP SEQUENCE RECIPE_SEQ;
DROP SEQUENCE INGR_SEQ;
DROP SEQUENCE DIR_SEQ;
DROP SEQUENCE TAG_SEQ;
DROP SEQUENCE FOLLOW_SEQ;
DROP SEQUENCE LOG_FOLLOW_SEQ;

--DROP TRIGGER
DROP TRIGGER TGR_EMAIL;
DROP TRIGGER TGR_NICKNAME;
DROP TRIGGER TGR_PROFILE_IMG;
DROP TRIGGER TGR_FILE_PRESENCE;
DROP TRIGGER TGR_BOARD_REPLY_PICTURE_PRESENCE;
DROP TRIGGER TGR_RECIPE_REPLY_PHOTO_PRESENCE;
DROP TRIGGER TGR_LOG_FOLLOW;

PURGE RECYCLEBIN;

--CREATE TABLES
CREATE TABLE MEMBER (
	EMAIL VARCHAR2(40) CONSTRAINT PK_MEMBER PRIMARY KEY,
	PWD VARCHAR2(90) NOT NULL,
	NICKNAME VARCHAR2(36) NOT NULL,
	PROFILE_IMG VARCHAR2(50) DEFAULT 'default_profile_img.png',
	NAME VARCHAR2(40),
	PHONE VARCHAR2(12),
	MARKETING NUMBER(1) DEFAULT 0,
	API_USING NUMBER(1) DEFAULT 1,
	SIGNUP_DATE DATE DEFAULT SYSDATE,
	UPDATE_DATE DATE DEFAULT SYSDATE,
	POINT NUMBER(7) DEFAULT 0,
	AUTHORITY NUMBER(1) DEFAULT 1,
	CONSTRAINT UK_MEMBER UNIQUE(NICKNAME)
);

CREATE TABLE RECIPE (
	ID NUMBER CONSTRAINT PK_RECIPE PRIMARY KEY,
	M_EMAIL VARCHAR2(40) NOT NULL,
	M_NICKNAME VARCHAR2(36) NOT NULL,
	PROFILE_IMG VARCHAR2(50) DEFAULT 'default_profile_img.png',
	TITLE VARCHAR2(300),
	INFO VARCHAR2(500),
	FOOD VARCHAR2(100),
	FOOD_PHOTO VARCHAR2(50),
	SORT VARCHAR2(40),
	INGREDIENT VARCHAR2(40),
	SERVING NUMBER(1),
	COOK_TIME VARCHAR2(20),
	DIFFICULTY_LEVEL VARCHAR2(30),
	POST_DATE DATE DEFAULT SYSDATE,
	UPDATE_DATE DATE DEFAULT SYSDATE,
	HITS NUMBER DEFAULT 0,
	REPLY NUMBER DEFAULT 0,
	STAR NUMBER(1) DEFAULT 0,
	ACCESSIBILITY NUMBER(1) DEFAULT 2,
	CONSTRAINT FK_RECIPE_EMAIL FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE,
	CONSTRAINT FK_RECIPE_NICKNAME FOREIGN KEY(M_NICKNAME) REFERENCES MEMBER(NICKNAME) ON DELETE CASCADE
  );

CREATE TABLE INGREDIENT (
	ID NUMBER CONSTRAINT PK_INGREDIENT PRIMARY KEY,
	R_ID NUMBER NOT NULL,
	INGREDIENT_TYPE NUMBER(1) NOT NULL,
	INGREDIENT VARCHAR2(40) NOT NULL,
	QUANTITY VARCHAR2(30),
	CONSTRAINT FK_INGREDIENT_RECIPE FOREIGN KEY(R_ID) REFERENCES RECIPE(ID) ON DELETE CASCADE
);
  
CREATE TABLE DIRECTION (
	ID NUMBER CONSTRAINT PK_DIRECTION PRIMARY KEY,
	R_ID NUMBER NOT NULL,
	STEP NUMBER(2) NOT NULL,
	DIRECTION VARCHAR2(1000) NOT NULL,
	ORIGINAL_FILE VARCHAR2(150) NOT NULL,
	SAVE_FILE VARCHAR2(50) NOT NULL,
	CONSTRAINT FK_DIRECTION_RECIPE FOREIGN KEY(R_ID) REFERENCES RECIPE(ID) ON DELETE CASCADE
);
  
CREATE TABLE RECIPE_TAG (
	ID NUMBER CONSTRAINT PK_RECIPE_TAG PRIMARY KEY,
	R_ID NUMBER NOT NULL,
	TAG VARCHAR2(30) NOT NULL,
	CONSTRAINT FK_TAG_RECIPE FOREIGN KEY(R_ID) REFERENCES RECIPE(ID) ON DELETE CASCADE
);

CREATE TABLE RATING(
	ID NUMBER CONSTRAINT PK_RATING PRIMARY KEY,
	R_ID NUMBER NOT NULL,
	M_EMAIL VARCHAR2(40) NOT NULL,
	STAR NUMBER(1) DEFAULT 0, 
	RATING_DATE DATE DEFAULT SYSDATE,
	CONSTRAINT FK_RATING_RECIPE FOREIGN KEY(R_ID) REFERENCES RECIPE(ID) ON DELETE CASCADE,
	CONSTRAINT FK_RATING_MEMBER FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE
);

CREATE TABLE SCRAP(
	ID NUMBER CONSTRAINT PK_SCRAP PRIMARY KEY,
	R_ID NUMBER NOT NULL,
	M_EMAIL VARCHAR2(40) NOT NULL,
	SCRAP_DATE DATE DEFAULT SYSDATE,
	CONSTRAINT FK_SCRAP_RECIPE FOREIGN KEY(R_ID) REFERENCES RECIPE(ID),
	CONSTRAINT FK_SCRAP_MEMBER FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE
);

CREATE TABLE RECIPE_REPLY(
	ID NUMBER CONSTRAINT PK_RECIPE_REPLY PRIMARY KEY,
	R_ID NUMBER NOT NULL,
	M_EMAIL VARCHAR2(40) NOT NULL,
	M_NICKNAME VARCHAR2(36) NOT NULL,
	PROFILE_IMG VARCHAR2(50) DEFAULT 'default_profile_img.png',
	REPLY VARCHAR2(900) NOT NULL,
	REPLY_DATE DATE DEFAULT SYSDATE,
	PHOTO NUMBER(1) DEFAULT 0,
	CONSTRAINT FK_R_REPLY_RECIPE FOREIGN KEY(R_ID) REFERENCES RECIPE(ID) ON DELETE CASCADE,
	CONSTRAINT FK_R_REPLY_MEMBER FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE,
	CONSTRAINT FK_R_REPLY_NICKNAME FOREIGN KEY(M_NICKNAME) REFERENCES MEMBER(NICKNAME) ON DELETE CASCADE
);

CREATE TABLE RECIPE_NESTED_REPLY(  
	ID NUMBER NOT NULL CONSTRAINT PK_R_NESTED_REPLY PRIMARY KEY,
	RR_ID NUMBER NOT NULL,
	M_EMAIL VARCHAR2(40) NOT NULL,
	M_NICKNAME VARCHAR2(36) NOT NULL,
	PROFILE_IMG VARCHAR2(50) DEFAULT 'default_profile_img.png',
	REPLY VARCHAR2(900) NOT NULL,
	REPLY_DATE DATE DEFAULT SYSDATE, 
	CONSTRAINT FK_R_NESTED_REPLY_ID FOREIGN KEY(RR_ID) REFERENCES RECIPE_REPLY(ID) ON DELETE CASCADE,
	CONSTRAINT FK_R_NESTED_REPLY_EMAIL FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE,
	CONSTRAINT FK_R_NESTED_REPLY_NICKNAME FOREIGN KEY(M_NICKNAME) REFERENCES MEMBER(NICKNAME) ON DELETE CASCADE
);

CREATE TABLE RECIPE_REPLY_PHOTO(
	ID NUMBER NOT NULL CONSTRAINT PK_R_REPLY_PHOTO PRIMARY KEY,
	RR_ID NUMBER NOT NULL,
	ORIGINAL_FILE VARCHAR2(150),
	SAVE_FILE VARCHAR2(50),
	CONSTRAINT FK_R_NESTED_REPLY FOREIGN KEY(RR_ID) REFERENCES RECIPE_REPLY(ID) ON DELETE CASCADE
);

CREATE TABLE BOARD(
	ID NUMBER CONSTRAINT PK_BOARD PRIMARY KEY,
	M_EMAIL VARCHAR2(40) NOT NULL,
	M_NICKNAME VARCHAR2(36) NOT NULL,
	PROFILE_IMG VARCHAR2(50) DEFAULT 'default_profile_img.png',
	POST_TYPE NUMBER(1) NOT NULL,
	TITLE VARCHAR2(150) NOT NULL,
	CONTENT NCLOB NOT NULL,
	POST_DATE DATE DEFAULT SYSDATE,
	UPDATE_DATE DATE DEFAULT SYSDATE,
	HITS NUMBER DEFAULT 0,
	REPLY NUMBER DEFAULT 0,
	FILES NUMBER(1) DEFAULT 0,
	CONSTRAINT FK_BOARD_M_EMAIL FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE,
	CONSTRAINT FK_BOARD_M_NICKNAME FOREIGN KEY(M_NICKNAME) REFERENCES MEMBER(NICKNAME) ON DELETE CASCADE
);

CREATE TABLE BOARD_FILE(
	ID NUMBER CONSTRAINT PK_BOARD_FILE PRIMARY KEY,
	B_ID NUMBER NOT NULL,
	ORIGINAL_FILE VARCHAR2(150) NOT NULL,
	SAVE_FILE VARCHAR2(50) NOT NULL,
	EXTENSION VARCHAR2(10) NOT NULL,
	FILE_SIZE NUMBER NOT NULL,
	CONSTRAINT FK_BOARD_FILE_B_ID FOREIGN KEY(B_ID) REFERENCES BOARD(ID) ON DELETE CASCADE
);

CREATE TABLE BOARD_REPLY(
	ID NUMBER CONSTRAINT PK_BOARD_REPLY PRIMARY KEY,
	B_ID NUMBER NOT NULL,
	M_EMAIL VARCHAR2(40) NOT NULL,
	M_NICKNAME VARCHAR2(36) NOT NULL,
	PROFILE_IMG VARCHAR2(50) NOT NULL,
	REPLY  VARCHAR2(900) NOT NULL,
	REPLY_DATE DATE DEFAULT SYSDATE,
	PICTURE NUMBER(1) DEFAULT 0,
	CONSTRAINT FK_BOARD_REPLY_B_ID FOREIGN KEY(B_ID) REFERENCES BOARD (ID) ON DELETE CASCADE,
	CONSTRAINT FK_BOARD_REPLY_M_EMAIL FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE,
	CONSTRAINT FK_BOARD_REPLY_M_NICKNAME FOREIGN KEY(M_NICKNAME) REFERENCES MEMBER(NICKNAME) ON DELETE CASCADE
);

CREATE TABLE BOARD_REPLY_PICTURE(
	ID NUMBER CONSTRAINT PK_B_REPLY_PICTURE PRIMARY KEY,
	BR_ID NUMBER NOT NULL,
	ORIGINAL_FILE VARCHAR2(150) NOT NULL,
	SAVE_FILE VARCHAR2(50) NOT NULL,
	CONSTRAINT FK_B_REPLY_PICTURE_ID FOREIGN KEY(BR_ID) REFERENCES BOARD_REPLY(ID) ON DELETE CASCADE
);

CREATE TABLE BOARD_NESTED_REPLY(
	ID NUMBER CONSTRAINT PK_BOARD_NESTED_REPLY PRIMARY KEY,
	BR_ID NUMBER,
	M_EMAIL VARCHAR2(40),
	M_NICKNAME VARCHAR2(36),
	PROFILE_IMG VARCHAR2(50),
	REPLY VARCHAR2(900),
	REPLY_DATE DATE DEFAULT SYSDATE,
	CONSTRAINT FK_B_NESTED_REPLY_BR_ID FOREIGN KEY(BR_ID) REFERENCES BOARD_REPLY (ID) ON DELETE CASCADE,
	CONSTRAINT FK_B_NESTED_REPLY_M_EMAIL FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE,
	CONSTRAINT FK_B_NESTED_REPLY_M_NICKNAME FOREIGN KEY(M_NICKNAME) REFERENCES MEMBER(NICKNAME) ON DELETE CASCADE
);

CREATE TABLE FOLLOW(
	ID NUMBER CONSTRAINT PK_FOLLOW PRIMARY KEY,
	M_EMAIL VARCHAR(40) NOT NULL, 
	FOLLOWEE VARCHAR(40) NOT NULL,
	FOLLOW_DATE DATE DEFAULT SYSDATE,
	CONSTRAINT FK_FOLLOW_M_EMAIL FOREIGN KEY(M_EMAIL) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE,
	CONSTRAINT FK_FOLLOW_FOLLOWEE FOREIGN KEY(FOLLOWEE) REFERENCES MEMBER(EMAIL) ON DELETE CASCADE
	);

CREATE TABLE LOG_GRADE(
		ID NUMBER CONSTRAINT PK_LOG_GRADE PRIMARY KEY,
		LOG_EMAIL VARCHAR2(40),
		LOG_EVENT VARCHAR2(300) NOT NULL,
		LOG_DATE TIMESTAMP DEFAULT SYSDATE
		);

CREATE TABLE LOG_FOLLOW(
		ID NUMBER CONSTRAINT PK_LOG_FOLLOW PRIMARY KEY,
		LOG_FOLLOWEE VARCHAR2(40),
		LOG_EVENT VARCHAR2(300) NOT NULL,
		LOG_DATE TIMESTAMP DEFAULT SYSDATE
		);

--CREATE SEQUENCES
CREATE SEQUENCE RECIPE_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE INGR_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE DIR_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE TAG_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE SEQUENCE RATING_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SCRAP_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE RECIPE_REPLY_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE R_NESTED_REPLY_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE R_REPLY_PHOTO_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE SEQUENCE BOARD_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE BOARD_FILE_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE BOARD_REPLY_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE B_REPLY_PICTURE_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE B_NESTED_REPLY_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE SEQUENCE FOLLOW_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE LOG_FOLLOW_SEQ INCREMENT BY 1 START WITH 1 NOCACHE;

--CREATE TRIGGER
CREATE OR REPLACE TRIGGER TGR_EMAIL
  AFTER UPDATE OF EMAIL ON MEMBER FOR EACH ROW
  BEGIN
    UPDATE RECIPE SET M_EMAIL=:new.EMAIL WHERE M_EMAIL=:old.EMAIL;
    UPDATE RATING SET M_EMAIL=:new.EMAIL WHERE M_EMAIL=:old.EMAIL;
    UPDATE SCRAP SET M_EMAIL=:new.EMAIL WHERE M_EMAIL=:old.EMAIL;
    UPDATE RECIPE_REPLY SET M_EMAIL=:new.EMAIL WHERE M_EMAIL=:old.EMAIL;
    UPDATE RECIPE_NESTED_REPLY SET M_EMAIL=:new.EMAIL WHERE M_EMAIL=:old.EMAIL;
    UPDATE BOARD SET M_EMAIL=:new.EMAIL WHERE M_EMAIL=:old.EMAIL;
    UPDATE BOARD_REPLY SET M_EMAIL=:new.EMAIL WHERE M_EMAIL=:old.EMAIL;
    UPDATE BOARD_NESTED_REPLY SET M_EMAIL=:new.EMAIL WHERE M_EMAIL=:old.EMAIL;
  END;
 /

 CREATE OR REPLACE TRIGGER TGR_NICKNAME
  AFTER UPDATE OF NICKNAME ON MEMBER FOR EACH ROW
  BEGIN
    UPDATE RECIPE SET M_NICKNAME=:new.NICKNAME WHERE M_NICKNAME=:old.NICKNAME;
    UPDATE RECIPE_REPLY SET M_NICKNAME=:new.NICKNAME WHERE M_NICKNAME=:old.NICKNAME;
    UPDATE RECIPE_NESTED_REPLY SET M_NICKNAME=:new.NICKNAME WHERE M_NICKNAME=:old.NICKNAME;
    UPDATE BOARD SET M_NICKNAME=:new.NICKNAME WHERE M_NICKNAME=:old.NICKNAME;
    UPDATE BOARD_REPLY SET M_NICKNAME=:new.NICKNAME WHERE M_NICKNAME=:old.NICKNAME;
    UPDATE BOARD_NESTED_REPLY SET M_NICKNAME=:new.NICKNAME WHERE M_NICKNAME=:old.NICKNAME;
  END;
 /

  CREATE OR REPLACE TRIGGER TGR_PROFILE_IMG
  AFTER UPDATE OF PROFILE_IMG ON MEMBER FOR EACH ROW
  BEGIN
    UPDATE RECIPE SET PROFILE_IMG=:new.PROFILE_IMG WHERE PROFILE_IMG=:old.PROFILE_IMG;
    UPDATE RECIPE_REPLY SET PROFILE_IMG=:new.PROFILE_IMG WHERE PROFILE_IMG=:old.PROFILE_IMG;
    UPDATE RECIPE_NESTED_REPLY SET PROFILE_IMG=:new.PROFILE_IMG WHERE PROFILE_IMG=:old.PROFILE_IMG;
    UPDATE BOARD SET PROFILE_IMG=:new.PROFILE_IMG WHERE PROFILE_IMG=:old.PROFILE_IMG;
    UPDATE BOARD_REPLY SET PROFILE_IMG=:new.PROFILE_IMG WHERE PROFILE_IMG=:old.PROFILE_IMG;
    UPDATE BOARD_NESTED_REPLY SET PROFILE_IMG=:new.PROFILE_IMG WHERE PROFILE_IMG=:old.PROFILE_IMG;
  END;
 /

  CREATE OR REPLACE TRIGGER TGR_FILE_PRESENCE
  AFTER INSERT OR UPDATE ON BOARD_FILE FOR EACH ROW
  BEGIN
    UPDATE BOARD SET FILES = 1 WHERE ID =:new.B_ID;
  END;
 /

  CREATE OR REPLACE TRIGGER TGR_BOARD_REPLY_PICTURE_PRESENCE
  AFTER INSERT ON BOARD_REPLY_PICTURE FOR EACH ROW
  BEGIN
    UPDATE BOARD_REPLY SET PICTURE=1 WHERE ID =:new.BR_ID;
  END;
 /

  CREATE OR REPLACE TRIGGER TGR_RECIPE_REPLY_PHOTO_PRESENCE
  AFTER INSERT ON RECIPE_REPLY_PHOTO FOR EACH ROW
  BEGIN
    UPDATE RECIPE_REPLY SET PHOTO=1 WHERE ID =:new.RR_ID;
  END;
 /

  CREATE OR REPLACE TRIGGER TGR_LOG_FOLLOW
  AFTER INSERT ON FOLLOW
  FOR EACH ROW
  BEGIN
    INSERT INTO LOG_FOLLOW (ID,LOG_FOLLOWEE,LOG_EVENT) VALUES(LOG_FOLLOW_SEQ.nextval, :new.FOLLOWEE,:new.M_EMAIL||'���� ȸ������ �ȷο� �߽��ϴ�.');
  END;
 /

SELECT TNAME FROM TAB;
SELECT SEQUENCE_NAME FROM USER_SEQUENCES;
SELECT TRIGGER_NAME FROM USER_TRIGGERS;