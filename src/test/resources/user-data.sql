INSERT INTO USER_TYPE (TYPE_ID, USER_TYPE, CREATED_ON, MODIFIED_ON) VALUES (1,'ADMIN',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)

INSERT INTO USER (USER_ID, USER_NAME, ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, CONTACT, EMAIL, PASSWD, CREATED_ON, MODIFIED_ON) VALUES (1,'ADMIN','ADMIN',NULL,NULL,'7700000000','admin@simpleBug.com','LetMeIn',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)

INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1,1)