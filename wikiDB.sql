/**************************************************************
* Project table, has the flag indicating whether the project is
* active or not. When marking a project as inactive, meaning
* the project is ¡°deleted¡± and will not let anyone in the project
* but only the WIKI Admin to change the status.
***************************************************************/

DROP DATABASE IF EXISTS WIKI;

CREATE DATABASE WIKI;

use WIKI;
/***************************************************************
* User table, has all the information about the user. It has the
* flag to indicate whether the user is still in the wiki database
* or not. The flag does not refer to the status in any individual
* projects.
****************************************************************/

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

/*****************************************************************
* Record table that keeps track of user with projects. This table
* combines the projectid and the email as the primary key so
* that multiple users can be in one project and can have different
* role names.
******************************************************************/
CREATE TABLE User (
	FirstName VARCHAR (20) NOT NULL,
	LastName VARCHAR (20) NOT NULL,
	EmailAddress VARCHAR (50) NOT NULL,
	Password VARCHAR (15) NOT NULL,
	UserIsActive BOOLEAN NOT NULL,
	
	PRIMARY KEY (EmailAddress)
);

INSERT INTO User VALUES 
('Zhengyi', 'Liang', 'hahaha2009@hotmail.com', '04221988', true),
('Jiada', 'Lee', 'evffegg@gmail.com', '524411', true),
('Jingbin', 'Zhang', 'ivy_onlyone@hotmail.com', 'iloveu4ever', true);

CREATE TABLE Role (
	RoleID INT NOT NULL,
	RoleName VARCHAR(15) NOT NULL,
	
	PRIMARY KEY (RoleName)
);

INSERT INTO Role VALUES
('1', 'Admin'),
('2', 'Coord'),
('3', 'Contri'),
('4', 'Obser'),
('5', 'Inactive'),
('6', 'Pending'),
('0', 'N/A');

/**********************************************
* record table that keeps track of user with projects
***********************************************/
CREATE TABLE WIKIRecord (
	UserEmail VARCHAR(50) NOT NULL,
	ProjectID INT NOT NULL,
	Role VARCHAR(15) NOT NULL,
	
	PRIMARY KEY (ProjectID, UserEmail),
	FOREIGN KEY (ProjectID) REFERENCES Project (ProjectID),
	FOREIGN KEY (UserEmail) REFERENCES User (EmailAddress),
	FOREIGN KEY (Role) REFERENCES Role (RoleName)
);

INSERT INTO WIKIRecord VALUES 
('hahaha2009@hotmail.com', '1', 'Admin'),
('hahaha2009@hotmail.com', '2', 'Admin'),
('hahaha2009@hotmail.com', '3', 'Admin'),
('ivy_onlyone@hotmail.com', '1', 'Coord'),
('evffegg@gmail.com', '1', 'Contri');