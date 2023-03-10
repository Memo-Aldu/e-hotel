set search_path TO public;
-- test table
CREATE TABLE test (
    email VARCHAR UNIQUE NOT NULL PRIMARY KEY,
    password TEXT NOT NULL CHECK (char_length(password) >=4)
);