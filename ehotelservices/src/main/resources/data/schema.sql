set search_path TO ehotel;

-- CUSTOM DOMAIN
CREATE EXTENSION citext; --DONE
CREATE DOMAIN email AS citext --DONE
    CHECK ( value ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );

-- ENUM --DONE
CREATE TYPE reservation_status AS ENUM ('PENDING', 'APPROVED', 'REJECTED', 'CANCELED', 'UPDATED');
CREATE TYPE room_status AS ENUM ('OCCUPIED', 'UNOCCUPIED', 'CLOSED');
CREATE TYPE payment_status AS ENUM ('PAYED', 'UNPAID');
CREATE TYPE app_role AS ENUM ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HOTEL_MANAGER', 'ROLE_MANAGER', 'ROLE_EMPLOYEE', 'ROLE_CUSTOMER');

CREATE CAST (varchar AS app_role) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS reservation_status) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS room_status) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS payment_status) WITH INOUT AS IMPLICIT;


-- ENTITIES
CREATE TABLE app_user ( --DONE
    email email UNIQUE NOT NULL PRIMARY KEY,
    password TEXT NOT NULL CHECK (char_length(password) >=4),
    user_role app_role DEFAULT app_role('ROLE_USER'),
    account_non_expired BOOLEAN DEFAULT TRUE,
    account_non_locked BOOLEAN DEFAULT TRUE,
    credentials_non_expired BOOLEAN DEFAULT TRUE,
    account_enable BOOLEAN DEFAULT TRUE
);

CREATE TABLE hotel_chain( --DONE
    chain_ID BIGSERIAL PRIMARY KEY,
    chain_email email NOT NULL UNIQUE,
    chain_name VARCHAR(30) NOT NULL,
    chain_phone_number TEXT NOT NULL UNIQUE,
    chain_rating smallint not null check(chain_rating between 1 and 5),
    chain_central_address TEXT not null);

CREATE TABLE hotel( --DONE
    hotel_ID BIGSERIAL PRIMARY KEY,
    chain_ID BIGSERIAL NOT NULL,
    hotel_name VARCHAR NOT NULL,
    hotel_address TEXT NOT NULL,
    hotel_email email NOT NULL UNIQUE,
    hotel_phone_number TEXT NOT NULL UNIQUE,
    hotel_rating smallint not null check(hotel_rating between 1 and 5),
    city VARCHAR(20),
    foreign key (chain_ID) references hotel_chain (chain_ID)
      on delete cascade --cant exist without chain_hotel
);

CREATE TABLE role( --DONE
    role_ID BIGSERIAL PRIMARY KEY, --pk
    hotel_ID BIGSERIAL NOT NULL, --fk
    role_title VARCHAR NOT NULL,
    role_description TEXT,
    foreign key (hotel_ID) references hotel (hotel_ID)
     ON DELETE CASCADE
);

CREATE TABLE department( --DONE
    dept_ID BIGSERIAL PRIMARY KEY, --pk
    hotel_ID BIGSERIAL NOT NULL, --fk
    dept_name VARCHAR NOT NULL,
    foreign key (hotel_ID) references hotel (hotel_ID)
       ON DELETE CASCADE
);

CREATE TABLE employee( --DONE
    employee_NAS VARCHAR(9) PRIMARY KEY, --pk
    employee_email email UNIQUE, --fk
    employee_role BIGSERIAL, --fk
    employee_dept BIGSERIAL, --fk
    employee_phone_number TEXT NOT NULL UNIQUE,
    employee_name VARCHAR NOT NULL,
    employee_middle_name VARCHAR,
    employee_last_name VARCHAR NOT NULL,
    employee_address TEXT NOT NULL,
    employee_salary numeric(9,2) NOT NULL,
    employee_DOB DATE NOT NULL,
    employee_registration_Date DATE NOT NULL default CURRENT_DATE,
    foreign key (employee_email) references app_user (email)
     ON DELETE SET NULL,
    foreign key (employee_dept) references department (dept_ID)
     ON DELETE SET NULL,
    foreign key (employee_role) references role (role_ID)
     ON DELETE SET NULL
);

ALTER TABLE department --DONE
    ADD COLUMN dept_manager_NAS VARCHAR(9),
	ADD FOREIGN KEY (dept_manager_NAS) REFERENCES employee(employee_NAS)
	ON DELETE RESTRICT;

CREATE TABLE customer( --DONE
    customer_NAS VARCHAR(9) PRIMARY KEY, --pk
    customer_email email UNIQUE, --fk
    customer_phone_number TEXT NOT NULL UNIQUE,
    customer_name VARCHAR NOT NULL,
    customer_middle_name VARCHAR NOT NULL,
    customer_last_name VARCHAR NOT NULL,
    customer_address TEXT NOT NULL,
    customer_DOB DATE NOT NULL,
    customer_registration_Date DATE NOT NULL default CURRENT_DATE,
    foreign key (customer_email) references app_user (email)
     ON DELETE SET NULL
);

CREATE TABLE room_view( --DONE
    view_ID BIGSERIAL PRIMARY KEY, --pk
    hotel_ID BIGSERIAL NOT NULL, --fk
    view_name VARCHAR NOT NULL,
    view_description TEXT,
    foreign key (hotel_ID) references hotel (hotel_ID)
      ON DELETE CASCADE
);

CREATE TABLE room_type( --DONE
    type_ID BIGSERIAL PRIMARY KEY, --pk
    hotel_ID BIGSERIAL NOT NULL, --fk
    view_ID BIGSERIAL, --fk
    price_per_night numeric(6,2) check (price_per_night > 0),
    capacity smallint NOT NULL check (capacity > 0),
    room_name VARCHAR NOT NULL,
    room_description TEXT,
    foreign key (hotel_ID) references hotel (hotel_ID)
      ON DELETE CASCADE,
    foreign key (view_ID) references room_view (view_ID)
      ON DELETE SET NULL

);

CREATE TABLE room( --DONE
    room_ID BIGSERIAL PRIMARY KEY, --pk
    hotel_ID BIGSERIAL NOT NULL, --fk
    room_type_ID BIGSERIAL, --fk
    occupancy_status room_status default 'UNOCCUPIED',
    room_number INT NOT NULL,
    foreign key (hotel_ID) references hotel (hotel_ID)
     ON DELETE CASCADE,
    foreign key (room_type_ID) references room_type (type_ID)
     ON DELETE RESTRICT --cannot delete room_type if rooms exist with that type
);

CREATE TABLE incident( --DONE
    incident_ID BIGSERIAL PRIMARY KEY, --pk
    room_ID BIGSERIAL NOT NULL, --fk
    reporter_id VARCHAR(9), --fk
    incident_description TEXT NOT NULL,
    incident_date DATE default CURRENT_DATE,
    foreign key (room_ID) references room (room_ID)
     ON DELETE CASCADE,
    foreign key (reporter_id) references employee (employee_NAS)
);

CREATE TABLE commodity( --DONE
    com_ID BIGSERIAL PRIMARY KEY, --pk
    room_ID BIGSERIAL NOT NULL, --fk
    com_name VARCHAR NOT NULL,
    foreign key (room_ID) references room (room_ID)
      ON DELETE CASCADE
);

CREATE TABLE extension( --DONE
    extension_ID BIGSERIAL PRIMARY KEY, --pk
    room_ID BIGSERIAL NOT NULL, --fk
    extension_name VARCHAR NOT NULL,
    extension_price NUMERIC(5,2) NOT NULL,
    foreign key (room_ID) references room (room_ID)
      ON DELETE CASCADE
);

CREATE TABLE stay( --DONE
    stay_ID BIGSERIAL PRIMARY KEY, --pk
    customer_NAS VARCHAR(9), --fk
    employee_NAS VARCHAR(9), --fk
    payment_total NUMERIC (7,2) NOT NULL,
    payment_status payment_status default 'UNPAID',
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    creation_date DATE default CURRENT_DATE,
    foreign key (customer_NAS) references customer (customer_NAS)
     ON DELETE SET NULL,
    foreign key (employee_NAS) references employee (employee_NAS)
     ON DELETE SET NULL
);

CREATE TABLE reservation( --DONE
    reservation_ID BIGSERIAL PRIMARY KEY, --pk
    customer_NAS VARCHAR(9), --fk
    reservation_status reservation_status default 'PENDING',
    special_request TEXT,
    total_price NUMERIC (7,2) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    creation_date DATE default CURRENT_DATE,
    foreign key (customer_NAS) references customer (customer_NAS)
        ON DELETE SET NULL
);

-- tables M:M
CREATE TABLE extension_stay( --DONE
    stay_ID BIGSERIAL NOT NULL, -- fk
    extension_ID BIGSERIAL NOT NULL,
    foreign key (stay_ID) references stay (stay_ID)
       ON DELETE CASCADE,
    foreign key (extension_ID) references extension(extension_ID)
       ON DELETE CASCADE
);

CREATE TABLE extension_reservation( --DONE
    reservation_ID BIGSERIAL, -- fk
    extension_ID BIGSERIAL,
    foreign key (reservation_ID) references reservation(reservation_ID)
      ON DELETE CASCADE,
    foreign key (extension_ID) references extension (extension_ID)
);

CREATE TABLE reviews( --DONE
    hotel_ID BIGSERIAL NOT NULL,
    customer_NAS VARCHAR(9) NOT NULL, --fk,
    review_comment TEXT NOT NULL,
    review_rating smallint not null check(review_rating between 1 and 5),
    review_date DATE DEFAULT CURRENT_DATE,
    foreign key (hotel_ID) references hotel(hotel_ID)
        ON DELETE CASCADE,
    foreign key (customer_NAS) references customer(customer_NAS)
        ON DELETE SET NULL
);

CREATE TABLE room_reservation(--DONE
    room_ID BIGSERIAL NOT NULL,
    reservation_ID BIGSERIAL NOT NULL,
    foreign key (room_ID) references room(room_ID)
     ON DELETE CASCADE,
    foreign key (reservation_ID) references reservation(reservation_ID)
     ON DELETE CASCADE
);

CREATE TABLE room_stay( --DONE
    room_ID BIGSERIAL NOT NULL,
    stay_ID BIGSERIAL NOT NULL,
    foreign key (room_ID) references room(room_ID)
      ON DELETE CASCADE,
    foreign key (stay_ID) references stay(stay_ID)
      ON DELETE CASCADE
);

--Views
CREATE VIEW total_capacity_per_hotel AS
SELECT H.hotel_id, SUM(RTS.type_capacity)  FROM hotel H,
        (SELECT (RS.num_rooms * RT.capacity) AS type_capacity, RT.type_id, RT.hotel_id FROM room_type RT,
            (SELECT count(R.room_id) AS num_rooms, room_type_id FROM room R GROUP BY R.room_type_id) AS RS
        WHERE RT.type_id = RS.room_type_id) AS RTS
WHERE H.hotel_id = RTS.hotel_id GROUP BY H.hotel_id;

CREATE VIEW total_room_per_city AS
SELECT COUNT(R.room_id) AS total_rooms, city FROM room R
    FULL JOIN appdb.ehotel.hotel H ON R.hotel_id = H.hotel_id GROUP BY city;