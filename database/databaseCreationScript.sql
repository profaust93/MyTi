DROP DATABASE if EXISTS my_ti ;

CREATE DATABASE IF NOT EXISTS `my_ti` DEFAULT CHARACTER SET utf8 ;
USE `my_ti` ;

-- -----------------------------------------------------
-- Table `my_ti`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS my_ti.Users;

CREATE TABLE IF NOT EXISTS my_ti.Users (
  UserId BIGINT(11) NOT NULL AUTO_INCREMENT,
  Username VARCHAR(45) NOT NULL ,
  Password VARCHAR(60) NOT NULL ,
  Enabled BIT NOT NULL DEFAULT 1,
  PRIMARY KEY (UserId)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100;

DROP TABLE IF EXISTS my_ti.User_roles;
CREATE TABLE my_ti.User_roles (
  User_role_id int(11) NOT NULL AUTO_INCREMENT,
  UserId BIGINT(11) NOT NULL,
  Role varchar(45) NOT NULL,
  PRIMARY KEY (User_role_id),
  UNIQUE KEY uni_username_role (Role,UserId),
  KEY fk_username_idx (UserId),
  CONSTRAINT fk_username FOREIGN KEY (UserId) REFERENCES my_ti.Users (UserId)
)
  ENGINE =InnoDB
  AUTO_INCREMENT = 100;

-- -----------------------------------------------------
-- Table `my_ti`.`TimeLaps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `my_ti`.`TimeLaps` ;

CREATE TABLE IF NOT EXISTS `my_ti`.`TimeLaps`(
    TimeLapsId BIGINT(11) NOT NULL AUTO_INCREMENT,
    CompleteTime TIMESTAMP,
    UserID BIGINT(11),
    ShortDescription VARCHAR(100),
    LongDescription VARCHAR(1000),
    Category VARCHAR(100),
    TimeLapsName VARCHAR(100),
  PRIMARY KEY (TimeLapsId),
  FOREIGN KEY (UserID) REFERENCES my_ti.Users(UserID)
)
  ENGINE =InnoDB
  AUTO_INCREMENT = 100;
  -- -----------------------------------------------------
-- Table `my_ti`.`Profiles`
DROP TABLE IF EXISTS `my_ti`.`Profiles` ;-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `my_ti`.`Profiles`(
    ProfileId BIGINT(11) NOT NULL AUTO_INCREMENT,
    UserId BIGINT(11) NOT NULL,
    FirstName VARCHAR (30),
    LastName VARCHAR (30),
    Email VARCHAR (30),

  PRIMARY KEY (ProfileId),
  FOREIGN KEY (UserId) REFERENCES Users (UserId)
    ON DELETE CASCADE
    ON UPDATE CASCADE

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
  EndTime TIMESTAMP,
  PRIMARY KEY (ChallengeId)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 100;

/*ToDo Table*/

DROP TABLE IF EXISTS my_ti.ToDoList;
CREATE TABLE IF NOT EXISTS my_ti.ToDoList
(
  Id BIGINT(11) NOT NULL AUTO_INCREMENT,
  Name VARCHAR(100),
  Notes LONGTEXT,
  CreateTime TIMESTAMP,
  DeadLineTime TIMESTAMP,
  Done BIT,
  UserId BIGINT(11) NOT NULL,
  PRIMARY KEY (Id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100;

DROP TABLE IF EXISTS my_ti.ToDoTask;
CREATE TABLE IF NOT EXISTS my_ti.ToDoTask
(
  Name VARCHAR(100),
  ToDoId BIGINT(11) NOT NULL,
  FOREIGN KEY (ToDoId) REFERENCES my_ti.ToDoList(Id)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS my_ti.UserMessages;
CREATE TABLE IF NOT EXISTS my_ti.UserMessages
(
  Id BIGINT(11) NOT NULL AUTO_INCREMENT,
  Message VARCHAR(100),
  SenderId BIGINT(11) NOT NULL,
  RecipientId BIGINT(11),
  ChallengeId BIGINT(11),
  PRIMARY KEY (Id),
  FOREIGN KEY (ChallengeId) REFERENCES my_ti.Challenge(ChallengeId)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100;

INSERT INTO Users(UserId,Username,Password,Enabled)
VALUES (1,'admin','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);
INSERT INTO Users(UserId,Username,Password,Enabled)
VALUES (2,'gera','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);
INSERT INTO Users(UserId,Username,Password,Enabled)
VALUES (3,'ruslan','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

INSERT INTO User_roles (UserId, Role)
VALUES (1, 'ROLE_USER');
INSERT INTO User_roles (UserId, Role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO User_roles (UserId, Role)
VALUES (2, 'ROLE_USER');


