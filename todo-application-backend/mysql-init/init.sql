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

-- --------------------------------------------------------
-- Table structure for table `task`
-- --------------------------------------------------------

CREATE TABLE `task` (
                        `uid` INT NOT NULL AUTO_INCREMENT,
                        `created_by` VARCHAR(255) DEFAULT NULL,
                        `created_date` DATETIME(6) DEFAULT NULL,
                        `modified_by` VARCHAR(255) DEFAULT NULL,
                        `modified_date` DATETIME(6) DEFAULT NULL,
                        `title` VARCHAR(255) DEFAULT NULL,
                        `description` VARCHAR(255) DEFAULT NULL,
                        `status_id` INT DEFAULT NULL,
                        `is_active` BIT(1) DEFAULT NULL,
                        PRIMARY KEY (`uid`),
                        KEY `FK_task_status` (`status_id`),
                        CONSTRAINT `FK_task_status` FOREIGN KEY (`status_id`) REFERENCES `statusdetails` (`status`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;



