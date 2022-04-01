CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	role_code varchar(5) NOT NULL,
	role_name varchar(30) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE roles ADD CONSTRAINT role_pk
	PRIMARY KEY(id);
ALTER TABLE roles ADD CONSTRAINT role_bk
	UNIQUE(role_code);
ALTER TABLE roles ADD CONSTRAINT role_ck
	UNIQUE(role_code,role_name);

CREATE TABLE users(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	role_id varchar(36) NOT NULL,
	"email" varchar(100) NOT NULL,
	"password" varchar(100) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE users ADD CONSTRAINT users_pk
	PRIMARY KEY(id);
ALTER TABLE users ADD CONSTRAINT roles_fk
	FOREIGN KEY(role_id) REFERENCES roles(id);
ALTER TABLE users ADD CONSTRAINT users_bk
	UNIQUE("email");
ALTER TABLE users ADD CONSTRAINT users_ck
	UNIQUE(role_id, "email");

CREATE TABLE file(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	extension_name varchar(6) NOT NULL,
	"content" bytea NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);

CREATE TABLE industry(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	industry_code varchar(5) NOT NULL,
	industry_name varchar(50) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE industry ADD CONSTRAINT industry_pk
	PRIMARY KEY(id);
ALTER TABLE industry ADD CONSTRAINT industry_bk
	UNIQUE(industry_code);
ALTER TABLE industry ADD CONSTRAINT industry_ck
	UNIQUE(industry_code, industry_name);

CREATE TABLE position(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	position_code varchar(5) NOT NULL,
	position_name varchar(50) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE "position" ADD CONSTRAINT position_pk
	PRIMARY KEY(id);
ALTER TABLE "position" ADD CONSTRAINT position_bk
	UNIQUE(position_code);
ALTER TABLE "position" ADD CONSTRAINT position_ck
	UNIQUE(position_code, position_name);


CREATE TABLE province(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	province_code varchar(5) NOT NULL,
	province_name varchar(100) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE province ADD CONSTRAINT province_pk
	PRIMARY KEY(id);
ALTER TABLE province ADD CONSTRAINT province_bk
	UNIQUE(province_code);
ALTER TABLE province ADD CONSTRAINT province_ck
	UNIQUE(province_code, province_name);

CREATE TABLE city(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	province_id varchar(36) NOT NULL,
	city_code varchar(5) NOT NULL,
	city_name varchar(100) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE city ADD CONSTRAINT city_pk
	PRIMARY KEY(id);
ALTER TABLE city ADD CONSTRAINT province_fk
	FOREIGN KEY(province_id) REFERENCES province(id);
ALTER TABLE city ADD CONSTRAINT city_bk
	UNIQUE(city_code);
ALTER TABLE city ADD CONSTRAINT city_ck
	UNIQUE(city_code, city_name, province_id);

CREATE TABLE social_media(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	instagram varchar(100),
	twitter varchar(100),
	facebook varchar(100),
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE social_media ADD CONSTRAINT media_pk
	PRIMARY KEY(id);

CREATE TABLE profile(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	user_id varchar(36) NOT NULL,
	industry_id varchar(36),
	position_id varchar(36),
	city_id varchar(36),
	file_id varchar(36),
	social_media_id varchar(36),
	full_name varchar(50) NOT NULL,
	company_name varchar(50),
	phone_number varchar(13),
	postal_code varchar(6),
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE profile ADD CONSTRAINT profile_pk
	PRIMARY KEY(id);
ALTER TABLE profile ADD CONSTRAINT user_fk
	FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE profile ADD CONSTRAINT file_fk
	FOREIGN KEY(file_id) REFERENCES file(id);
ALTER TABLE profile ADD CONSTRAINT industry_fk
	FOREIGN KEY(industry_id) REFERENCES industry(id);
ALTER TABLE profile ADD CONSTRAINT position_fk
	FOREIGN KEY(position_id) REFERENCES "position"(id);
ALTER TABLE profile ADD CONSTRAINT city_fk
	FOREIGN KEY(city_id) REFERENCES city(id);
ALTER TABLE profile ADD CONSTRAINT social_media_fk
	FOREIGN KEY(social_media_id) REFERENCES social_media(id);


CREATE TABLE transaction_status(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	status_code varchar(5) NOT NULL,
	status_name varchar(30) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE transaction_status ADD CONSTRAINT transaction_status_pk
	PRIMARY KEY(id);
ALTER TABLE transaction_status ADD CONSTRAINT transaction_status_code_bk
	UNIQUE(status_code);
ALTER TABLE transaction_status ADD CONSTRAINT transaction_status_ck
	UNIQUE(status_code, status_name);

CREATE TABLE thread_type(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	thread_type_code varchar(5) NOT NULL,
	thread_type_name varchar(15) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE thread_type ADD CONSTRAINT thread_type_pk
	PRIMARY KEY(id);
ALTER TABLE thread_type ADD CONSTRAINT thread_type_bk
	UNIQUE(thread_type_code);
ALTER TABLE thread_type ADD CONSTRAINT thread_type_ck
	UNIQUE(thread_type_name, thread_type_code);

CREATE TABLE thread(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	thread_type_id varchar(36) NOT NULL,
	file_id varchar(36),
	title text NOT NULL,
	"content" text NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE thread ADD CONSTRAINT forum_pk
	PRIMARY KEY(id);
ALTER TABLE thread ADD CONSTRAINT type_fk
	FOREIGN KEY(thread_type_id) REFERENCES thread_type(id);
ALTER TABLE thread ADD CONSTRAINT file_fk
	FOREIGN KEY(file_id) REFERENCES file(id);

CREATE TABLE thread_detail(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	thread_id varchar(36) NOT NULL,
	"comment" text NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE thread_detail ADD CONSTRAINT thread_detail_pk
	PRIMARY KEY(id);
ALTER TABLE thread_detail ADD CONSTRAINT thread_fk
	FOREIGN KEY(thread_id) REFERENCES thread(id);

CREATE TABLE thread_bookmark(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	thread_id varchar(36) NOT NULL,
	user_id varchar(36) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE thread_bookmark ADD CONSTRAINT bookmark_pk
	PRIMARY KEY(id);
ALTER TABLE thread_bookmark ADD CONSTRAINT thread_fk
	FOREIGN KEY(thread_id) REFERENCES thread(id);
ALTER TABLE thread_bookmark ADD CONSTRAINT user_fk
	FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE thread_bookmark ADD CONSTRAINT bookmark_bk
	UNIQUE(thread_id, user_id);

CREATE TABLE thread_like(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	thread_id varchar(36) NOT NULL,
	user_id varchar(36) NOT NULL,
	like_counter int DEFAULT 0,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE thread_like ADD CONSTRAINT like_pk
	PRIMARY KEY(id);
ALTER TABLE thread_like ADD CONSTRAINT thread_fk
	FOREIGN KEY(thread_id) REFERENCES thread(id);
ALTER TABLE thread_like ADD CONSTRAINT user_fk
	FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE thread_like ADD CONSTRAINT like_ck
	UNIQUE(thread_id, user_id);
	
CREATE TABLE article(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	file_id varchar(36),
	title text NOT NULL,
	"content" text NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE article ADD CONSTRAINT article_pk
	PRIMARY KEY(id);
ALTER TABLE article ADD CONSTRAINT file_fk
	FOREIGN KEY(file_id) REFERENCES file(id);

CREATE TABLE polling_header(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	thread_id varchar(36) NOT NULL,
	title varchar(100) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE polling_header ADD CONSTRAINT header_pk
	PRIMARY KEY(id);
ALTER TABLE polling_header ADD CONSTRAINT thread_fk
	FOREIGN KEY(thread_id) REFERENCES thread(id);

CREATE TABLE polling_detail(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	polling_header_id varchar(36) NOT NULL,
	polling_name varchar(100) NOT NULL,
	polling_counter int DEFAULT 0,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE polling_detail ADD CONSTRAINT polling_detail_pk
	PRIMARY KEY(id);
ALTER TABLE polling_detail ADD CONSTRAINT polling_header_fk
	FOREIGN KEY(polling_header_id) REFERENCES polling_header(id);

CREATE TABLE polling_voter(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	polling_detail_id varchar(36) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE polling_voter ADD CONSTRAINT polling_voter_pk
	PRIMARY KEY(id);
ALTER TABLE polling_voter ADD CONSTRAINT polling_detail_fk
	FOREIGN KEY(polling_detail_id) REFERENCES polling_detail(id);

CREATE TABLE category(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	category_code varchar(5) NOT NULL,
	category_name varchar(50) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE category ADD CONSTRAINT category_pk
	PRIMARY KEY(id);
ALTER TABLE category ADD CONSTRAINT category_bk
	UNIQUE(category_code);
ALTER TABLE category ADD CONSTRAINT category_ck
	UNIQUE(category_code, category_name);

CREATE TABLE activity_type(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	activity_type_code varchar(5) NOT NULL,
	activity_type_name varchar(10) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE activity_type ADD CONSTRAINT activity_type_pk
	PRIMARY KEY(id);
ALTER TABLE activity_type ADD CONSTRAINT activity_type_bk
	UNIQUE(activity_type_code);
ALTER TABLE activity_type ADD CONSTRAINT activity_type_ck
	UNIQUE(activity_type_code, activity_type_name);

CREATE TABLE activity(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	file_id varchar(36),
	activity_type_id varchar(36) NOT NULL,
	category_id varchar(36) NOT NULL,
	transaction_status_id varchar(36) NOT NULL,
	payment_file_id varchar(36),
	activity_name varchar(100) NOT NULL,
	date_start date NOT NULL,
	date_end date NOT NULL,
	time_start time NOT NULL,
	time_end time NOT NULL,
	price bigint,
	"location" varchar(50),
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE activity ADD CONSTRAINT activity_id
	PRIMARY KEY(id);
ALTER TABLE activity ADD CONSTRAINT file_fk
	FOREIGN KEY(file_id) REFERENCES file(id);
ALTER TABLE activity ADD CONSTRAINT activity_type_fk
	FOREIGN KEY(activity_type_id) REFERENCES activity_type(id);
ALTER TABLE activity ADD CONSTRAINT transaction_status_fk
	FOREIGN KEY(transaction_status_id) REFERENCES transaction_status(id);
ALTER TABLE activity ADD CONSTRAINT category_fk
	FOREIGN KEY(category_id) REFERENCES category(id);
ALTER TABLE activity ADD CONSTRAINT payment_file_fk
	FOREIGN KEY(payment_file_id) REFERENCES file(id);

CREATE TABLE subscribe(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	duration int NOT NULL,
	price bigint NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE subscribe ADD CONSTRAINT subscribe_pk
	PRIMARY KEY(id);

CREATE TABLE orders(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	transaction_status_id varchar(36) NOT NULL,
	user_id varchar(36) NOT NULL,
	file_id varchar(36),
	invoice varchar(10) NOT NULL,
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE orders ADD CONSTRAINT order_pk
	PRIMARY KEY(id);
ALTER TABLE orders ADD CONSTRAINT user_fk
	FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE orders ADD CONSTRAINT transaction_status_fk
	FOREIGN KEY(transaction_status_id) REFERENCES transaction_status(id);
ALTER TABLE orders ADD CONSTRAINT file_fk
	FOREIGN KEY(file_id) REFERENCES file(id);

CREATE TABLE order_detail(
	id varchar(36) DEFAULT uuid_generate_v4 (),
	order_id varchar(36),
	subscribe_id varchar(36),
	activity_id varchar(36),
	created_by varchar(36),
	created_at timestamp WITHOUT time zone DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(36),
	updated_at timestamp WITHOUT time zone,
	"version" int DEFAULT 0,
	is_active boolean DEFAULT true
);

ALTER TABLE order_detail ADD CONSTRAINT order_detail_pk
	PRIMARY KEY(id);
ALTER TABLE order_detail ADD CONSTRAINT order_fk
	FOREIGN KEY(order_id) REFERENCES orders(id);
ALTER TABLE order_detail ADD CONSTRAINT subscribe_fk
	FOREIGN KEY(subscribe_id) REFERENCES subscribe(id);
ALTER TABLE order_detail ADD CONSTRAINT activity_fk
	FOREIGN KEY(activity_id) REFERENCES activity(id);

