CREATE SEQUENCE sequence_generator START 1050 INCREMENT By 50;

CREATE TABLE contact
(
	id BIGINT PRIMARY KEY,
	name VARCHAR,
	phone_number VARCHAR UNIQUE NOT NULL,
	email VARCHAR,
	organization VARCHAR,
	github VARCHAR UNIQUE NOT NULL
);

CREATE TABLE gitrepo
(
	id BIGINT PRIMARY KEY,
	url VARCHAR,
	contact_id BIGINT,
	FOREIGN KEY (contact_id) REFERENCES contact (id)
);
