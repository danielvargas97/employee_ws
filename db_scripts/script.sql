
CREATE DATABASE IF NOT EXISTS employee_db
;

SET FOREIGN_KEY_CHECKS=0
;

/* Drop Tables */

DROP TABLE IF EXISTS `employee` CASCADE
;

/* Create Tables */

CREATE TABLE `employee`
(
	`id` VARCHAR(38) NOT NULL,
	`document_type` VARCHAR(4) NOT NULL,
	`document_number` VARCHAR(30) NOT NULL,
	`first_name` VARCHAR(50) NULL,
	`last_name` VARCHAR(50) NULL,
	`birth_date` DATE NOT NULL,
	`job_start_date` DATE NOT NULL,
	`job_title` VARCHAR(100) NOT NULL,
	`salary` DOUBLE(10,2) NOT NULL,
	CONSTRAINT `PK_Employee` PRIMARY KEY (`id` ASC)
)

;

SET FOREIGN_KEY_CHECKS=1
;

