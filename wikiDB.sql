/***********************************************************
* Create the database named project and all of its tables
************************************************************/

DROP DATABASE IF EXISTS WIKI;

CREATE DATABASE WIKI;

use WIKI;
/*************************************************
* project table
*************************************************/
CREATE TABLE Project (
	ProjectID INT NOT NULL AUTO_INCREMENT,
	ProjectName VARCHAR (40) NOT NULL,
	ProjectIsActive BOOLEAN NOT NULL,
	ProjectDescription VARCHAR (150) NOT NULL,
	
	PRIMARY KEY (ProjectID)
);

INSERT INTO Project VALUES
('1', 'test1', true, 'test testtest testtest testtest test'),
('2', 'test2', true, 'test testtest testtest testtest'),
('3', 'test3', true, 'test testtest testtest testtest testtest test');

/*************************************************
* user table
*************************************************/
CREATE TABLE User (
	FirstName VARCHAR (20) NOT NULL,
	LastName VARCHAR (20) NOT NULL,
	EmailAddress VARCHAR (50) NOT NULL,
	Password VARCHAR (10) NOT NULL,
	
	PRIMARY KEY (EmailAddress)
);

INSERT INTO User VALUES 
('Zhengyi', 'Liang', 'hahaha2009@hotmail.com', '04221988');

/**********************************************
* record table that keeps track of user with projects
***********************************************/
CREATE TABLE WIKIRecord (
	UserEmail VARCHAR(50) NOT NULL,
	ProjectID INT NOT NULL,
	Role VARCHAR (10),
	
	PRIMARY KEY (ProjectID, UserEmail),
	FOREIGN KEY (ProjectID) REFERENCES Project (ProjectID),
	FOREIGN KEY (UserEmail) REFERENCES User (EmailAddress)
);

INSERT INTO WIKIRecord VALUES 
('hahaha2009@hotmail.com', '1', 'admin'),
('hahaha2009@hotmail.com', '2', 'admin'),
('hahaha2009@hotmail.com', '3', 'admin');