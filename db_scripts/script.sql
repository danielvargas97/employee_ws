
CREATE DATABASE IF NOT EXISTS employee_db
;

SET FOREIGN_KEY_CHECKS=0
;

/* Drop Tables */

DROP TABLE IF EXISTS `Employee` CASCADE
;

/* Create Tables */

CREATE TABLE `Employee`
(
	`id` VARCHAR(38) NOT NULL,
	`documentType` VARCHAR(4) NOT NULL,
	`documentNumber` VARCHAR(30) NOT NULL,
	`firstName` VARCHAR(50) NULL,
	`lastName` VARCHAR(50) NULL,
	`birthDate` DATE NOT NULL,
	`jobStartDate` DATE NOT NULL,
	`jobTitle` VARCHAR(100) NOT NULL,
	`salary` DOUBLE(10,2) NOT NULL,
	CONSTRAINT `PK_Employee` PRIMARY KEY (`id` ASC)
)

;

SET FOREIGN_KEY_CHECKS=1
;
