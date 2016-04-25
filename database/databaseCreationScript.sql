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
  `UserId` INT(11) NOT NULL AUTO_INCREMENT,
  `Login` CHAR(32) NOT NULL,
  `Password` CHAR(32) NOT NULL,
  `FirstName` CHAR(32) NOT NULL,
  `LastName` CHAR(32) NOT NULL,
  `Email` CHAR(32) NOT NULL,
  PRIMARY KEY (`UserId`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

-- -----------------------------------------------------
-- Table `my_ti`.`TimeLaps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `my_ti`.`TimeLaps` ;

CREATE TABLE IF NOT EXISTS `my_ti`.`TimeLaps`(
    TimeLapsId BIGINT(11) NOT NULL AUTO_INCREMENT,
    CompleteTime TINYBLOB,
    UserID BIGINT(11),
    ShortDescription VARCHAR(100),
    LongDescription VARCHAR(1000),
    Category VARCHAR(100),
    TimeLapsName VARCHAR(100),
  PRIMARY KEY (TimeLapsId)
)
  ENGINE =InnoDB
  AUTO_INCREMENT = 100;
  -- -----------------------------------------------------
-- Table `my_ti`.`Profiles`
DROP TABLE IF EXISTS `my_ti`.`Profiles` ;-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `my_ti`.`Profiles`(
    ProfileId INT(11) NOT NULL AUTO_INCREMENT,
    UserId INT (11) NOT NULL UNIQUE,
    FirstName VARCHAR (30),
    LastName VARCHAR (30),
    Email VARCHAR (30),

  PRIMARY KEY (ProfileId),
  FOREIGN KEY (UserId) REFERENCES Users (UserId) ON DELETE CASCADE on UPDATE CASCADE

)
  ENGINE =InnoDB
  AUTO_INCREMENT = 100;
-- -----------------------------------------------------
-- Table `my_ti`.`Challenge`
DROP TABLE IF EXISTS `my_ti`.`Challenge` ;-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_ti`.`Challenge`
(
  ChallengeId BIGINT(11) NOT NULL AUTO_INCREMENT,
  ChallengeName VARCHAR(100),
  FromUserId BIGINT(11),
  ToUserId BIGINT(11),
  ChallengeState VARCHAR(20),
  Description VARCHAR(1000),
  EndTime TINYBLOB,
  PRIMARY KEY (ChallengeId)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100;

/*ToDo Table*/

DROP TABLE IF EXISTS my_ti.ToDoList;
CREATE TABLE IF NOT EXISTS my_ti.ToDoList
(
  Id BIGINT (11) NOT NULL AUTO_INCREMENT,
  ListName VARCHAR(100),
  CreateTime TIMESTAMP,
  DeadLineTime TIMESTAMP,
  UserId BIGINT(11),
  Notes TEXT,
  IsComplete BOOLEAN,
  PRIMARY KEY (Id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100;

DROP TABLE IF EXISTS my_ti.ToDoTask;
CREATE TABLE IF NOT EXISTS my_ti.ToDoTask
(
  Id BIGINT(11) NOT NULL AUTO_INCREMENT,
  TaskName VARCHAR(100),
  IsComplete BOOLEAN,
  Description VARCHAR(255),
  ToDoListId BIGINT(11),
  Goals INT(11),
  CompletedGoals INT(11),
  PRIMARY KEY (Id),
  FOREIGN KEY (ToDoListId) REFERENCES my_ti.ToDoList(Id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100;

DROP TABLE IF EXISTS my_ti.UserMessages;
CREATE TABLE IF NOT EXISTS my_ti.UserMessages
(
  Id BIGINT(11) NOT NULL AUTO_INCREMENT,
  Message VARCHAR(100),
  SernderId BIGINT(11) NOT NULL,
  ReceipentId VARCHAR(255),
  ChallengeId BIGINT(11),
  PRIMARY KEY (Id),
  FOREIGN KEY (ChallengeId) REFERENCES my_ti.challenge(ChallengeId)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100;

INSERT INTO my_ti.Users VALUES (1,'admin','qwerty','Admin','Admin','admin@myTi.com');
INSERT INTO my_ti.Users VALUES (2,'admin2','qwerty','Admin2','Admin2','admin2@myTi.com');
INSERT INTO Profiles VALUES (default, 1, "testName", "testFamilyName","testEmail");

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


