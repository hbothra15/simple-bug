INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (1,'USER_TYPE','ADMIN',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (2,'USER_TYPE','SUPPORT',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (3,'USER_TYPE','VENDOR',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (4,'STATUS_TYPE','TO_DO',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (5,'STATUS_TYPE','IN_PROGRESS',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (6,'STATUS_TYPE','FIXED',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (7,'STATUS_TYPE','CLOSED',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (8,'STATUS_TYPE','REJECTED',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (9,'BUG_TYPE','PURCHASE',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (10,'BUG_TYPE','INSTALLATION',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (11,'BUG_TYPE','WARRANTY',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO SIMPLE_LKP (LKP_CODE,LKP_TYPE, LKP_VALUE, CREATED_ON, MODIFIED_ON) VALUES (12,'BUG_TYPE','REPAIR',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)

INSERT INTO USER (USER_ID, USER_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, CONTACT, EMAIL, PASSWD, CREATED_ON, MODIFIED_ON) VALUES (1,'ADMIN','ADMIN',NULL,NULL,'7700000000','admin@simpleBug.com','LetMeIn',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO USER (USER_ID, USER_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, CONTACT, EMAIL, PASSWD, CREATED_BY, MODIFIED_BY, CREATED_ON, MODIFIED_ON) VALUES (2,'SUPPORT','Address 1',NULL,NULL,'7700000001','support@simpleBug.com','LetMeIn',1,1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO USER (USER_ID, USER_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, CONTACT, EMAIL, PASSWD, CREATED_BY, MODIFIED_BY, CREATED_ON, MODIFIED_ON) VALUES (3,'VENDOR','Address 1',NULL,NULL,'7700000002','vendor@simpleBug.com','LetMeIn',1,1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)

INSERT INTO BUGS (BUG_ID, BUG_TITLE, BUG_DESCR, CREATED_ON, CREATED_BY, PROJECT, MODIFIED_BY, MODIFIED_ON, BUG_TYPE, BUG_STATUS, ASSIGNED_TO) VALUES (1,'First Bug','Default Bug',CURRENT_TIMESTAMP,3,0,1,CURRENT_TIMESTAMP,10,4,2)
INSERT INTO BUGS (BUG_ID, BUG_TITLE, BUG_DESCR, CREATED_ON, CREATED_BY, PROJECT, MODIFIED_BY, MODIFIED_ON, BUG_TYPE, BUG_STATUS, ASSIGNED_TO) VALUES (2,'Second Bug','Default Bug',CURRENT_TIMESTAMP,3,0,1,CURRENT_TIMESTAMP,10,5,1)
INSERT INTO BUGS (BUG_ID, BUG_TITLE, BUG_DESCR, CREATED_ON, CREATED_BY, PROJECT, MODIFIED_BY, MODIFIED_ON, BUG_TYPE, BUG_STATUS, ASSIGNED_TO) VALUES (3,'Third Bug','Default Bug',CURRENT_TIMESTAMP,1,0,1,CURRENT_TIMESTAMP,10,6,2)

INSERT INTO H$BUGS (HISTORY_ID, BUG_ID, BUG_TITLE, BUG_DESCR, CREATED_ON, CREATED_BY, MODIFIED_BY, MODIFIED_ON, BUG_TYPE, BUG_STATUS, ASSIGNED_TO) VALUES (1,1,'First Bug','Default Bug',CURRENT_TIMESTAMP,3,1,CURRENT_TIMESTAMP,10,4,1)

INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1,1)
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (2,2)
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (3,3)