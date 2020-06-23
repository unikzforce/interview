CREATE SEQUENCE sequence_generator START 1050 INCREMENT By 50;

CREATE TABLE contact
(
	id BIGINT PRIMARY KEY,
	name VARCHAR,
	phoneNumber varchar,
	email VARCHAR,
	organization VARCHAR,
	github VARCHAR
);

CREATE TABLE gitrepo
(
	id BIGINT PRIMARY KEY,
	url VARCHAR,
	contactId BIGINT,
	FOREIGN KEY (contactId) REFERENCES contact (id)
);
