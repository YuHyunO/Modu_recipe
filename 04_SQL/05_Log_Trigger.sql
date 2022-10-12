DROP TRIGGER TGR_LOG_FOLLOW

CREATE OR REPLACE TRIGGER TGR_LOG_FOLLOW
AFTER INSERT ON FOLLOW
FOR EACH ROW
BEGIN
  INSERT INTO LOG_FOLLOW (ID,LOG_FOLLOWEE,LOG_EVENT) VALUES(LOG_FOLLOW_SEQ.nextval, :new.FOLLOWEE,:new.M_EMAIL||'���� ȸ������ �ȷο� �߽��ϴ�.');
END;
/