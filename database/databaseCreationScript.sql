SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `my_ti` DEFAULT CHARACTER SET utf8 ;
USE `my_ti` ;

-- -----------------------------------------------------
-- Table `my_ti`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `my_ti`.`Users` ;

CREATE TABLE IF NOT EXISTS `my_ti`.`Users` (
  `UserID` INT(11) NOT NULL AUTO_INCREMENT,
  `Login` CHAR(32) NOT NULL,
  `Password` CHAR(32) NOT NULL,
  `FirstName` CHAR(32) NOT NULL,
  `LastName` CHAR(32) NOT NULL,
  `Email` CHAR(32) NOT NULL,
  PRIMARY KEY (`UserID`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

-- -----------------------------------------------------
-- Table `my_ti`.`ToDo_`
-- ------------------------------------ -----------------
DROP TABLE IF EXISTS `my_ti`.`ToDo` ;

CREATE TABLE IF NOT EXISTS `my_ti`.`ToDo` (
  `ToDoID` INT(11) NOT NULL AUTO_INCREMENT,
  `ToDoTime` TIMESTAMP,
  `DeadLineTime` TIMESTAMP,
  `CategoryId` INT(11),
  `ShortDesc` VARCHAR(100),
  `FullDesc` VARCHAR(1000),
  `Priority` SMALLINT NOT NULL,
  `IsDone` BOOLEAN NOT NULL,
  `Name` VARCHAR(100) NOT NULL,
  `UserId` INT(11) NOT NULL,

  PRIMARY KEY (`ToDoID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100;
-- -----------------------------------------------------
-- Table `my_ti`.`TimeLaps`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_ti`.`TimeLaps`(
    TimeLapsId INT(11) NOT NULL AUTO_INCREMENT,
    TimeLapsName VARCHAR(100),
    CompleteTime TIMESTAMP,
    UserID INT(11),
    ShortDescription VARCHAR(100),
    LongDescription VARCHAR(1000),
    Category VARCHAR(100),
  PRIMARY KEY (TimeLapsId)
)
  ENGINE =InnoDB
  AUTO_INCREMENT = 100;
  -- -----------------------------------------------------
-- Table `my_ti`.`Profiles`
DROP TABLE IF EXISTS `my_ti`.`Profiles` ;-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `my_ti`.`Profiles`(
    ProfileId INT(11) NOT NULL AUTO_INCREMENT,
    UserId INT (11) NOT NULL,
    FirstName VARCHAR (30),
    LastName VARCHAR (30),
    Email VARCHAR (30),

  PRIMARY KEY (ProfileId)

)
  ENGINE =InnoDB
  AUTO_INCREMENT = 100;


INSERT INTO my_ti.Users VALUES (1,'admin','qwerty','Admin','Admin','admin@myTi.com');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


