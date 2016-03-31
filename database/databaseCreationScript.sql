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
-- -----------------------------------------------------
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
  PRIMARY KEY (`ToDoID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100;
-- -----------------------------------------------------
-- Table `my_ti`.`TimeLaps`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_ti`.`TimeLaps`(
    TimeLapsId INT(11) NOT NULL AUTO_INCREMENT,
    CompleteTime TIMESTAMP,
    UserID INT(11),
    ShortDescription VARCHAR(100),
    LongDescription VARCHAR(1000),
    CategoryId VARCHAR(100),
  PRIMARY KEY (TimeLapsId)
)
  ENGINE =InnoDB
  AUTO_INCREMENT = 100;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
