--POINT TRIGGERS
/*
    [이벤트]                [포인트]
| 레시피 등록/수정(공개)   |    +100    |
| 레시피 삭제/수정(비공개)  |    -100    |
| 사진 리뷰 작성          |    +30     |
| 사진 리뷰 삭제          |    -30     |
| 일반 리뷰 작성          |    +10     |
| 일반 리뷰 삭제          |    -10     |
| 구독자 수 증가(1명당)    |    +50     |
| 게시글 작성(자유게시판)   |    +50     |
| 게시글 삭제(자유게시판)   |    -50     |
| 일반 댓글 작성           |    +10     |
| 일반 댓글 삭제           |    -10     |
*/
/*
 [등급]          [조건]
수습요리사 |   0~300       POINT / default 등급
보조요리사 |   301~1000    POINT / 레시피 3개 ~ 10개
초보요리사 |   1001~3000   POINT / 레시피 10개 ~ 29개
중급요리사 |   3001~9000   POINT / 레시피 30개 ~ 89개
고급요리사 |   9001~18000  POINT / 레시피 90개 ~ 179개
요리마스터 |   18001~54000 POINT / 레시피 180개 ~ 539개
요리의신   |   54001~      POINT / 레시피 540개 ~
*/


--[포인트 추가]--
--레시피 등록 시 :(ACCESSIBILITY=0) +100
DROP TRIGGER TGR_POINT_PLUS_RECIPE_INSERT_UPDATE;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_RECIPE_INSERT_UPDATE
AFTER INSERT ON RECIPE
FOR EACH ROW
WHEN(new.ACCESSIBILITY=0)
DECLARE point number;
BEGIN
  UPDATE MEMBER SET POINT=POINT+100 WHERE EMAIL=:new.M_EMAIL;
  SELECT POINT INTO point FROM MEMBER WHERE EMAIL=:new.M_EMAIL;
END;
/
--레시피 공개로 수정 시 :(ACCESSIBILITY=1->ACCESSIBILITY=0) +100
DROP TRIGGER TGR_POINT_PLUS_RECIPE_UPDATE;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_RECIPE_UPDATE
AFTER UPDATE ON RECIPE
FOR EACH ROW
BEGIN
  IF :old.ACCESSIBILITY=1 AND :new.ACCESSIBILITY=0
  THEN
  UPDATE MEMBER SET POINT=POINT+100 WHERE EMAIL=:new.M_EMAIL;
  END IF;
END;
/
--레시피 비공개로 수정 시 :(ACCESSIBILITY=0->ACCESSIBILITY=1) -100
DROP TRIGGER TGR_POINT_MINUS_RECIPE_UPDATE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_RECIPE_UPDATE
AFTER UPDATE ON RECIPE
FOR EACH ROW
BEGIN
  IF :old.ACCESSIBILITY=0 AND :new.ACCESSIBILITY=1
  THEN
  UPDATE MEMBER SET POINT=POINT-100 where EMAIL=:new.M_EMAIL;
  END IF;
END;
/
--레시피 삭제 시 :(ACCESSBILITY=0 인 레시피를 삭제한 경우 실행) -100
DROP TRIGGER TGR_POINT_MINUS_RECIPE_DELETE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_RECIPE_DELETE
AFTER DELETE ON RECIPE
FOR EACH ROW
WHEN(old.ACCESSIBILITY=0)
BEGIN
  UPDATE MEMBER SET POINT=POINT-100 where EMAIL=:old.M_EMAIL;
END;
/
--레시피 일반 리뷰 작성 +10
DROP TRIGGER TGR_POINT_PLUS_RECIPE_REPLY_INSERT;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_RECIPE_REPLY_INSERT
AFTER INSERT ON RECIPE_REPLY
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT+10 WHERE EMAIL=:new.M_EMAIL;
END;
/
--레시피 리뷰 사진 첨부 :(new.PHOTO=1) +20 /리뷰에 사진 추가 시 PHOTO=1로 변경 후 포인트 추가 트리거 실행
DROP TRIGGER TGR_POINT_PLUS_PHOTO_PRESENCE_RECIPE_PHOTO_INSERT;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_PHOTO_PRESENCE_RECIPE_PHOTO_INSERT
AFTER INSERT ON RECIPE_REPLY_PHOTO
FOR EACH ROW
BEGIN
  UPDATE RECIPE_REPLY SET PHOTO=1 WHERE ID =:new.RR_ID;
  UPDATE MEMBER SET POINT=POINT+20 WHERE EMAIL=(SELECT M_EMAIL FROM RECIPE_REPLY WHERE ID=:new.RR_ID);
END;
/
--레시피 대댓글 작성 +10
DROP TRIGGER TGR_POINT_PLUS_RECIPE_NESTED_REPLY_INSERT;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_RECIPE_NESTED_REPLY_INSERT
AFTER INSERT ON RECIPE_NESTED_REPLY
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT+10 WHERE EMAIL=:new.M_EMAIL;
END;
/

--레시피 일반 리뷰 삭제 -10
DROP TRIGGER TGR_POINT_MINUS_RECIPE_REPLY_DELETE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_RECIPE_REPLY_DELETE
AFTER DELETE ON RECIPE_REPLY
FOR EACH ROW
WHEN(old.PHOTO=0)
BEGIN
  UPDATE MEMBER SET POINT=POINT-10 WHERE EMAIL=:old.M_EMAIL;
END;
/
--레시피 사진첨부 리뷰 삭제 :(PHOTO=1 인 리뷰를 삭제한 경우 실행)
DROP TRIGGER TGR_POINT_MINUS_RECIPE_REPLY_PHOTO_DELETE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_RECIPE_REPLY_PHOTO_DELETE
AFTER DELETE ON RECIPE_REPLY
FOR EACH ROW
WHEN(old.PHOTO=1)
BEGIN
  UPDATE MEMBER SET POINT=POINT-30 WHERE EMAIL=:old.M_EMAIL;
END;
/
--레시피 대댓글 삭제 -10
DROP TRIGGER TGR_POINT_MINUS_RECIPE_NESTED_REPLY_DELETE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_RECIPE_NESTED_REPLY_DELETE
AFTER DELETE ON RECIPE_NESTED_REPLY
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT-10 WHERE EMAIL=:old.M_EMAIL;
END;
/
--구독자 수 증가 +50
DROP TRIGGER TGR_POINT_PLUS_FOLLOW_INSERT;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_FOLLOW_INSERT
AFTER INSERT ON FOLLOW
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT+50 WHERE EMAIL=:new.FOLLOWEE;
END;
/
--구독자 수 감소 +50
DROP TRIGGER TGR_POINT_MINUS_FOLLOW_DELETE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_FOLLOW_DELETE
AFTER DELETE ON FOLLOW
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT-50 WHERE EMAIL=:old.FOLLOWEE;
END;
/
--게시글 작성(자유게시판) +50
DROP TRIGGER TGR_POINT_PLUS_BOARD_INSERT;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_BOARD_INSERT
AFTER INSERT ON BOARD
FOR EACH ROW
WHEN(new.POST_TYPE=1)
BEGIN
  UPDATE MEMBER SET POINT=POINT+50 WHERE EMAIL=:new.M_EMAIL;
END;
/
--게시글 삭제(자유게시판) -50
DROP TRIGGER TGR_POINT_MINUS_BOARD_DELETE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_BOARD_DELETE
AFTER DELETE ON BOARD
FOR EACH ROW
WHEN(old.POST_TYPE=1)
BEGIN
  UPDATE MEMBER SET POINT=POINT-50 WHERE EMAIL=:old.M_EMAIL;
END;
/
--게시판 일반 댓글 작성 +10
DROP TRIGGER TGR_POINT_PLUS_BOARD_REPLY_INSERT;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_BOARD_REPLY_INSERT
AFTER INSERT ON BOARD_REPLY
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT+10 WHERE EMAIL=:new.M_EMAIL;
END;
/
--게시판 대댓글 작성 +10
DROP TRIGGER TGR_POINT_PLUS_BOARD_NESTED_REPLY;
CREATE OR REPLACE TRIGGER TGR_POINT_PLUS_BOARD_NESTED_REPLY
AFTER INSERT ON BOARD_NESTED_REPLY
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT+10 WHERE EMAIL=:new.M_EMAIL;
END;
/
--게시판 일반 댓글 삭제 -10
DROP TRIGGER TGR_POINT_MINUS_BOARD_REPLY_DELETE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_BOARD_REPLY_DELETE
AFTER DELETE ON BOARD_REPLY
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT-10 WHERE EMAIL=:old.M_EMAIL;
END;
/
--게시판 대댓글 삭제 -10
DROP TRIGGER TGR_POINT_MINUS_BOARD_NESTED_REPLY_DELETE;
CREATE OR REPLACE TRIGGER TGR_POINT_MINUS_BOARD_NESTED_REPLY_DELETE
AFTER DELETE ON BOARD_NESTED_REPLY
FOR EACH ROW
BEGIN
  UPDATE MEMBER SET POINT=POINT-10 WHERE EMAIL=:old.M_EMAIL;
END;
/

