ALTER TABLE RECIPE MODIFY STAR NUMBER(3,1) DEFAULT 0;
ALTER TABLE RECIPE ADD STAR_SUM NUMBER DEFAULT 0;
ALTER TABLE RECIPE ADD STARS NUMBER DEFAULT 0;

--평점 수 카운팅
DROP TRIGGER TGR_RATING_RECIPE_STAR;
CREATE OR REPLACE TRIGGER TGR_RATING_RECIPE_STAR
AFTER INSERT ON RATING
FOR EACH ROW
BEGIN
  UPDATE RECIPE SET STARS=STARS+1 WHERE ID=:new.R_ID;
  UPDATE RECIPE SET STAR_SUM=STAR_SUM+:new.STAR WHERE ID=:new.R_ID;
  UPDATE RECIPE SET STAR=STAR_SUM/STARS WHERE ID=:new.R_ID;
END;
/
--이하 테스트 
/*
SELECT NVL(SUM(STAR),0) FROM RATING GROUP BY R_ID HAVING R_ID=630;
commit;
delete from RATING;
update RECIPE set STARS=0 where ID=630;
update RECIPE set STAR=0 where ID=630;
select ID, STAR, STARS from RECIPE order by ID desc;
insert into RATING (ID,R_ID,M_EMAIL,STAR) values(RATING_SEQ.nextval,630,'test2@test.com',3);
*/