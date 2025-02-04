

CREATE TABLE person (
	id serial NOT NULL,
	"name" varchar(100) NULL,
	gender varchar(10) NULL,
	age int4 NULL,
	identification varchar(50) NOT NULL,
	address varchar(50) NULL,
	phone varchar(50) NULL,
	CONSTRAINT person_identification_key UNIQUE (identification),
	CONSTRAINT person_pkey PRIMARY KEY (id)
);

CREATE TABLE client (
	id serial NOT NULL,
	"password" varchar(50) NULL,
	status varchar(50) NULL,
	person_id varchar(50) NULL,
	CONSTRAINT client_pkey PRIMARY KEY (id)
);

CREATE TABLE account (
	id serial NOT NULL,
	number_account varchar(100) unique NOT NULL,
	identification varchar(100)  NOT NULL,
	type_account varchar(10) NULL,
	initial_amount float NULL,
	status int not null
);

CREATE TABLE movement ( 
    id serial NOT NULL,
    number_account varchar(100) NULL,
    movement_at timestamp DEFAULT now() NOT NULL,
    type_movement varchar(100) NULL,
    initial_amount float NULL,
    movement_amount float NULL,
    status int NOT NULL
);