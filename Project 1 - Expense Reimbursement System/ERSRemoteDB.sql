CREATE TABLE ERS_REIMBURSEMENT
(
  REIMB_ID number(10) PRIMARY KEY,
  REIMB_AMOUNT number,
  REIMB_SUBMITTED timestamp,
  REIMB_RESOLVED timestamp,
  REIMB_DESCRIPTION varchar2(250),
  REIMB_RECEIPT blob,
  REIMB_AUTHOR number(10),
  REIMB_RESOLVER number(10),
  REIMB_STATUS_ID number(10),
  REIMB_TYPE_ID number(10),
  CONSTRAINT ERS_USERS_FK_AUTH FOREIGN KEY(REIMB_AUTHOR)
    REFERENCES ERS_USERS(ERS_USERS_ID),
  CONSTRAINT ERS_USERS_FK_RESLVR FOREIGN KEY(REIMB_RESOLVER)
    REFERENCES ERS_USERS(ERS_USERS_ID),
  CONSTRAINT ERS_REIMBURSEMENT_STATUS_FK FOREIGN KEY(REIMB_STATUS_ID)
    REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID),
  CONSTRAINT ERS_REIMBURSEMENT_TYPE_FK FOREIGN KEY(REIMB_TYPE_ID)
    REFERENCES ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID)
);

DROP TABLE ERS_REIMBURSEMENT;
CREATE TABLE ERS_USERS
(
  ERS_USERS_ID number(10) PRIMARY KEY,
  ERS_USERNAME varchar2(50) UNIQUE,
  ERS_PASSWORD varchar2(50),
  USER_FIRST_NAME varchar2(100),
  USER_LAST_NAME varchar2(100),
  USER_EMAIL varchar2(150) UNIQUE,
  USER_ROLE_ID number(10),
  CONSTRAINT USER_ROLES_FK FOREIGN KEY(USER_ROLE_ID)
    REFERENCES ERS_USER_ROLES(ERS_USER_ROLE_ID)
);
DROP TABLE ERS_USERS;
DROP TABLE ERS_USER_ROLES;

CREATE TABLE ERS_REIMBURSEMENT_STATUS
(
  REIMB_STATUS_ID number(10) PRIMARY KEY,
  REIMB_STATUS varchar2(10)
);
DROP TABLE ERS_REIMBURSEMENT_STATUS;
CREATE TABLE ERS_REIMBURSEMENT_TYPE
(
  REIMB_TYPE_ID number(10) PRIMARY KEY,
  REIMB_TYPE varchar2(10)
);
DROP TABLE ERS_REIMBURSEMENT_TYPE;

CREATE TABLE ERS_USER_ROLES
(
  ERS_USER_ROLE_ID number(10) PRIMARY KEY,
  USER_ROLE varchar2(10)
);

CREATE SEQUENCE REIMBURSEMENT_SEQ
start with 1
increment by 1
minvalue 1
maxvalue 100000;

CREATE OR REPLACE TRIGGER REIMBURSEMENT_SEQ_KEY_GEN
BEFORE INSERT ON ERS_REIMBURSEMENT
FOR EACH ROW

BEGIN
  SELECT REIMBURSEMENT_SEQ.NEXTVAL
  INTO   :new.REIMB_ID
  FROM   dual;
END;
/
--------------------------------------------------------------------------------------
CREATE SEQUENCE REIMBURSEMENT_S_SEQ
start with 1
increment by 1
minvalue 1
maxvalue 100000;

CREATE OR REPLACE TRIGGER REIMBURSEMENT_S_SEQ_KEY_GEN
BEFORE INSERT ON ERS_REIMBURSEMENT_STATUS
FOR EACH ROW

BEGIN
  SELECT REIMBURSEMENT_S_SEQ.NEXTVAL
  INTO   :new.REIMB_STATUS_ID
  FROM   dual;
END;
/
------------------------------------------------------------------
CREATE SEQUENCE REIMBURSEMENT_T_SEQ
start with 1
increment by 1
minvalue 1
maxvalue 100000;

CREATE OR REPLACE TRIGGER REIMBURSEMENT_T_SEQ_KEY_GEN
BEFORE INSERT ON ERS_REIMBURSEMENT_TYPE
FOR EACH ROW

BEGIN
  SELECT REIMBURSEMENT_T_SEQ.NEXTVAL
  INTO   :new.REIMB_TYPE_ID
  FROM   dual;
END;
/
---------------------------------------------------------------------------

CREATE SEQUENCE USER_ROLES_SEQ
start with 1
increment by 1
minvalue 1
maxvalue 100000;

CREATE OR REPLACE TRIGGER USER_ROLES_SEQ_KEY_GEN
BEFORE INSERT ON ERS_USER_ROLES
FOR EACH ROW

BEGIN
  SELECT USER_ROLES_SEQ.NEXTVAL
  INTO   :new.ERS_USER_ROLE_ID
  FROM   dual;
END;
/

-----------------------------------------------------------------------------

CREATE SEQUENCE USERS_SEQ
start with 1
increment by 1
minvalue 1
maxvalue 100000;

CREATE OR REPLACE TRIGGER USERS_SEQ_KEY_GEN
BEFORE INSERT ON ERS_USERS
FOR EACH ROW

BEGIN
  SELECT USERS_SEQ.NEXTVAL
  INTO   :new.ERS_USERS_ID
  FROM   dual;
END;
/

select * from ers_reimbursement_status;

delete from ers_reimbursement where reimb_id=347;

delete from ERS_REIMBURSEMENT_STATUS where REIMB_STATUS_ID = 22;
select * from ers_reimbursement_type;

delete from ers_reimbursement_type;


select * from ers_user_roles;

select * from ers_users;

select* from ers_reimbursement;
update ers_user_roles set USER_ROLE= 'finance' where ers_user_role_id = 2;

ALTER SEQUENCE Reimbursement_t_seq INCREMENT by 1; 
-- testUser password urID=1 = employee
-- testUser2 password urID = 2 = finance

commit;
