/*
Ben's updates
Added dept_id column to users and users_history
Created users_courses_links table NO RELEVANT TRIGGER FUNCTION
Created inbox_history table NO RELEVANT TRIGGER FUNCTION 
Created courses_history table and relevant trigger function
Created sections_history table and relevant trigger function

Harsh's Updates
Added Drops
Fixed Typos

Ben's Updates
Changed course_number to string to account for 500x classes
Changed number of courses, lectures, and conferences to default to zero on the creation of a new course

Alex's Updates
Added insert statements to section_types table
Removed "Unique(user_id,role_id,deleted)" from users_roles_links

Matt's Updates
Debugged table populating queries 
Modified default_role function and trigger to not throw error while populating
Removed default_role 

Noah's v9. Update
	--added trigger for populating inbox_history table

Harsh's v10. Update
	--Modified sections table to add crn
	--Modified drops
	--Added inserts into section_types

Ben's v11. Update
	--Changed user_user_name in inbox, inbox_history, and inbox trigger 
--to author_user_name
	--Added course_load table

Alex's v11 Update
	--Added course_abrv to courses table
	--Changed course_number type to integer
*/

CREATE TABLE user_states
(
	id serial PRIMARY KEY,
	state varchar(255) NOT NULL,
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted boolean NOT NULL DEFAULT(FALSE),
	unique(state, deleted)
);


CREATE TABLE department_id
(
	id serial PRIMARY KEY,
	department_name varchar(255) UNIQUE NOT NULL
);



CREATE TABLE users
(
	id serial PRIMARY KEY,
	user_name varchar(255) UNIQUE NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) UNIQUE NOT NULL,
	encrypted_password varchar(255) NOT NULL,
	salt varchar(255) UNIQUE NOT NULL,
	user_state_id integer NOT NULL REFERENCES user_states(id),
	dept_id integer NOT NULL REFERENCES department_id(id) DEFAULT(1),
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);


CREATE UNIQUE INDEX users_user_name ON users(user_name);


CREATE TABLE users_history
(
	id serial PRIMARY KEY,
	user_id integer NOT NULL REFERENCES users(id) ON DELETE CASCADE,
	user_name varchar(255) NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	encrypted_password varchar(255) NOT NULL,
	salt varchar(255) NOT NULL,
	user_state_id integer NOT NULL REFERENCES user_states(id),
	dept_id integer NOT NULL REFERENCES department_id(id) DEFAULT(1),
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);


CREATE TABLE user_roles
(
	id serial PRIMARY KEY,
	role_name varchar(255) NOT NULL,
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted boolean NOT NULL DEFAULT(FALSE),
	UNIQUE(role_name, deleted)
);


CREATE TABLE users_roles_links
(
	id serial PRIMARY KEY,
	user_id integer NOT NULL REFERENCES users(id) ON DELETE CASCADE,
	role_id integer NOT NULL REFERENCES user_roles(id) ON DELETE CASCADE,
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	deleted boolean NOT NULL DEFAULT(FALSE),
	UNIQUE(user_id, role_id, deleted)
);

CREATE FUNCTION insert_users_history() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO users_history(user_id, user_name, first_name, last_name, email, encrypted_password, salt, user_state_id, dept_id)
VALUES(OLD.id, OLD.user_name, OLD.first_name, OLD.last_name, OLD.email, OLD.encrypted_password, OLD.salt, OLD.user_state_id, OLD.dept_id);
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
	

CREATE TRIGGER update_users
BEFORE UPDATE ON users
FOR EACH ROW 
EXECUTE PROCEDURE insert_users_history();


CREATE TABLE inbox
(
	id serial PRIMARY KEY,
	user_id integer NOT NULL REFERENCES users(id) ON DELETE CASCADE,
	author_user_name varchar(255) NOT NULL REFERENCES users(user_name) ON DELETE CASCADE,
	message text NOT NULL,
	resolved boolean NOT NULL DEFAULT(FALSE),
	received_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	dept_id integer REFERENCES department_id(id)
);

CREATE TABLE section_types
(
	id serial PRIMARY KEY,
	type_name varchar(255) NOT NULL
);

CREATE TABLE courses
(
	id serial PRIMARY KEY,
	name varchar(255) NOT NULL,
	dept_id integer NOT NULL REFERENCES department_id(id),
	course_abrv varchar(255) NOT NULL,
	course_number varchar(255) NOT NULL,
	number_of_lectures integer NOT NULL DEFAULT(0),
	number_of_labs integer NOT NULL DEFAULT(0),
	number_of_conferences integer NOT NULL DEFAULT(0),
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

CREATE TABLE sections
(
	id serial PRIMARY KEY,
	course_id integer NOT NULL REFERENCES courses(id),
	crn integer NOT NULL UNIQUE,
	section_name varchar(255) NOT NULL,
	section_id integer REFERENCES section_types(id) NOT NULL,
	expected_population integer NOT NULL,
	required_frequency integer NOT NULL,
	academic_year integer NOT NULL,
	academic_term varchar(255) NOT NULL,
	start_time integer NOT NULL,
	end_time integer NOT NULL,
	days_per_week varchar(255) NOT NULL,
	course_location varchar(255) NOT NULL,
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

CREATE TABLE course_load
(
	id serial PRIMARY KEY,
	user_id integer NOT NULL REFERENCES users(id),
	amount integer NOT NULL DEFAULT(0),
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	Upadated_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

INSERT INTO section_types(type_name) VALUES ('Lecture'), ('Conference'), ('Lab');


INSERT INTO user_roles(role_name) VALUES('Faculty'), ('Administrator'),('Moderator');

INSERT INTO user_states(state) VALUES('ACTIVE'), ('DEACTIVATED');

INSERT INTO department_id(department_name) VALUES('Not Assigned'), ('Aerospace'),('Biology'), ('Biomedical'),
('Chemistry'), ('Computer Science'), ('Electrical and Computer Engineering'), 
('Environmental Engineering'),('Fire Protection Engineering'), ('Humanities'), ('Mathematics'), 
('Mechanical Engineering'), ('Physics'), ('Robotics Engineering');

CREATE FUNCTION insert_users_role_links() RETURNS TRIGGER
AS 
$BODY$
BEGIN
INSERT INTO users_roles_links(user_id, role_id)
VALUES(NEW.id, 1);
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
	

CREATE TRIGGER insert_users_role_links
AFTER INSERT ON users
FOR EACH ROW
EXECUTE PROCEDURE insert_users_role_links();


CREATE TABLE users_sections_links
(
	id serial PRIMARY KEY,
	user_id integer NOT NULL REFERENCES users(id) ON DELETE CASCADE,
	section_id integer NOT NULL REFERENCES sections(id) ON DELETE CASCADE,
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

CREATE TABLE inbox_history
(
	id serial PRIMARY KEY,
	message_id integer NOT NULL REFERENCES inbox(id) ON DELETE CASCADE,
	user_id integer NOT NULL REFERENCES users(id) ON DELETE CASCADE,
	author_user_name varchar(255) NOT NULL REFERENCES users(user_name) ON DELETE CASCADE,
	message text NOT NULL,
	resolved boolean NOT NULL DEFAULT(FALSE),
	received_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	dept_id integer REFERENCES department_id(id)
);

CREATE TABLE courses_history
(
	id serial PRIMARY KEY,
	course_id integer NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
	dept_id integer NOT NULL REFERENCES department_id(id),
	name varchar(255) NOT NULL,
	course_number varchar(255) NOT NULL,
	number_of_lectures integer NOT NULL,
	number_of_labs integer NOT NULL,
	number_of_conferences integer NOT NULL,
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

CREATE TABLE sections_history
(
	id serial PRIMARY KEY,
	sect_id integer NOT NULL REFERENCES sections(id) ON DELETE CASCADE,
	course_id integer REFERENCES courses(id),
	section_name varchar(255) NOT NULL,
	section_id integer REFERENCES section_types(id) NOT NULL,
	expected_population integer NOT NULL,
	required_frequency integer NOT NULL,
	academic_year integer NOT NULL,
	academic_term varchar(255) NOT NULL,
	start_time integer NOT NULL,
	end_time integer NOT NULL,
	days_per_week varchar(255) NOT NULL,
	course_location varchar(255) NOT NULL,
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

CREATE FUNCTION insert_courses_history() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO courses_history(course_id, name, dept_it, course_number, number_of_lectures, number_of_labs, number_of_conferences)
VALUES(OLD.id, OLD.name, OLD.dept_id, OLD.course_number, OLD.number_of_lectures, OLD.number_of_labs, OLD.number_of_conferences);
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER update_courses
BEFORE UPDATE ON courses
FOR EACH ROW 
EXECUTE PROCEDURE insert_courses_history();

CREATE FUNCTION insert_inbox_history() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO inbox_history(message_id,user_id,author_user_name,message,resolved,received_at,dept_id)
VALUES(OLD.id, OLD.user_id, OLD.author_user_name, OLD.message, OLD.resolved, OLD.received_at, OLD.dept_id);
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER insert_inbox_history
BEFORE UPDATE ON inbox
FOR EACH ROW 
EXECUTE PROCEDURE insert_inbox_history();



CREATE FUNCTION insert_sections_history() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO sections_history(sect_id, course_id, section_name, section_id, expected_population, required_frequency, academic_year, academic_term, start_time, end_time, days_per_week, course_location)
VALUES(OLD.id, OLD.course_id, OLD.section_name, OLD.section_id, OLD.expected_population, OLD.required_frequency, OLD.academic_year, OLD.academic_term, OLD.start_time, OLD.end_time, OLD.days_per_week, OLD.course_location);
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER update_sections
BEFORE UPDATE ON sections
FOR EACH ROW 
EXECUTE PROCEDURE insert_sections_history();





----------------------------------------------------------------------------------------------------------------------

/*
DROP TRIGGER update_users ON users;
DROP TRIGGER insert_users_role_links ON users;
DROP TRIGGER insert_inbox_history ON inbox;
DROP TRIGGER update_courses ON courses;
DROP TRIGGER update_sections ON sections;

DROP FUNCTION insert_inbox_history();
DROP FUNCTION insert_users_history();
DROP FUNCTION insert_users_role_links();
DROP FUNCTION insert_courses_history();
DROP FUNCTION insert_sections_history();

DROP TABLE course_load;
DROP TABLE users_roles_links;
DROP TABLE user_roles;
DROP TABLE users_history;
DROP TABLE inbox_history;
DROP TABLE inbox;
DROP TABLE courses_history;
DROP TABLE sections_history;
DROP TABLE users_sections_links;
DROP TABLE sections;
DROP TABLE section_types;
DROP TABLE courses;
DROP TABLE users;
DROP TABLE user_states;
DROP TABLE department_id;

*/
