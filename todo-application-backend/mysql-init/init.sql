-- MySQL initialization script for Todo Application
-- This script runs when the MySQL container starts for the first time
-- Create database if it doesn't exist (though it's already created by environment variables)
CREATE DATABASE IF NOT EXISTS todo_db;

-- Use the database
USE todo_db;

CREATE TABLE statusdetails (
                status INT NOT NULL,
                description VARCHAR(255) NULL,
                PRIMARY KEY (status)
);

INSERT INTO statusdetails (status, description) VALUES
(10, 'Pending'),
(20, 'Completed');
