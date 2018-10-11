CREATE TABLE Customer
(
  customer_id number(20) primary key,
  username varchar2(50) unique not null,
  password varchar2(50) not null,
  firstname varchar2(50) not null,
  lastname varchar2(50)
);
drop table bank_account;
drop table Customer;
CREATE TABLE Bank_Account
(
  account_id number(10) primary key,
  account_type varchar2(50) not null,
  account_name varchar2(50) not null,
  balance number(20) not null,
  customer_id number(20) not null,
  constraint fk_account_customer 
  foreign key(customer_id) references Customer(customer_id)
);

insert into Customer values(null, 'Gilessldfsdfsd', 'password', 'blah', 'blahington');
insert into Customer(username) values('GilesDingus'); 
select * from Customer;

CREATE OR REPLACE PROCEDURE getCustomerByUsername(
  in_username in CUSTOMER.USERNAME%TYPE,
  c_userid OUT Customer.customer_id%TYPE,
  c_username OUT Customer.username%type,
  c_password OUT Customer.password%type,
  c_firstname OUT Customer.firstname%type,
  c_lastname OUT Customer.lastname%type)
is
begin
  select customer_id, username, password, firstname, lastname
  into c_userid,c_username, c_password, c_firstname, c_lastname
  from CUSTOMER where username = in_username;
end;
/

CREATE OR REPLACE PROCEDURE getAccountBy;

commit;
truncate table bank_account;
truncate table customer;

select * from bank_account;
select * from customer;

drop table bank_account;

CREATE SEQUENCE account_seq
start with 1
increment by 1
minvalue 1
maxvalue 100000;

CREATE OR REPLACE TRIGGER cust_acc_key_gen
BEFORE INSERT ON bank_account
FOR EACH ROW

BEGIN
  SELECT account_seq.NEXTVAL
  INTO   :new.account_id
  FROM   dual;
END;
/


CREATE OR REPLACE TRIGGER cust_key_gen
BEFORE INSERT ON customer 
FOR EACH ROW

BEGIN
  SELECT customer_seq.NEXTVAL
  INTO   :new.customer_id
  FROM   dual;
END;



commit;
