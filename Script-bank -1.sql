--CREATE DATABASE bank;
DROP TABLE IF EXISTS users;
CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	user_type VARCHAR(30),
	user_fname VARCHAR(30),
	user_lname VARCHAR(30), 
	user_phone VARCHAR(30),
	user_username VARCHAR(50) UNIQUE,
	user_password VARCHAR(50)
);

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts(
	account_number SERIAL PRIMARY KEY,
	account_status VARCHAR(30),
	account_balance NUMERIC(50,2),
	account_user_fk INTEGER REFERENCES users(user_id)
);


--INSERT 
INSERT INTO users(user_type, user_fname, user_lname, user_phone, user_username, user_password)
	VALUES('Admin', 'Elizabeth', 'Jimenez', NULL, 'liz1988', 'wesley');
INSERT INTO users(user_type, user_fname, user_lname, user_phone, user_username, user_password)
	VALUES('Customer', 'Xavier', 'Jimenez', NULL, 'cash123', 'joey123');

INSERT INTO accounts(account_number, account_status, account_balance, account_user_fk)
	VALUES();
-- UPDATE 
UPDATE * FROM users SET users(user_type= ?, user_fname= ?, user_lname= ?, user_phone= ?, user_username= ?, user_password= ?) 
	WHERE user_id=?;
UPDATE accounts SET account_status= 'Approved', account_balance= 40.00 WHERE account_number= 8;
--SELECT 
--"SELECT * FROM avengers WHERE superhero_id =" +id+";"
SELECT * FROM users WHERE user_id =;
SELECT * FROM accounts WHERE account_number =;
--TRANSACTION

BEGIN; 
INTO users(user_type, user_fname, user_lname, user_phone, user_username, user_password)
	VALUES (?,?,?,?,?,?);
INSERT INTO accounts(account_number, account_status, account_balance, account_user_fk)
	VALUES (?,?,?,?);
COMMIT;