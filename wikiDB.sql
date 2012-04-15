

DROP DATABASE IF EXISTS WIKI;

CREATE DATABASE WIKI;

use WIKI;

/***************************************************************
* User table, has all the information about the user. It has the
* flag to indicate whether the user is still in the wiki database
* or not. The flag does not refer to the status in any individual
* projects.
****************************************************************/

CREATE TABLE User (
	FirstName VARCHAR (20) NOT NULL,
	LastName VARCHAR (20) NOT NULL,
	EmailAddress VARCHAR (50) NOT NULL,
	Password VARCHAR (15) NOT NULL,
	UserCreationTime TIMESTAMP NOT NULL,
	UserIsActive BOOLEAN NOT NULL,
	
	PRIMARY KEY (EmailAddress)
);

INSERT INTO User VALUES 
('Zhengyi', 'Liang', 'hahaha2009@hotmail.com', '04221988', NOW(), true),
('Jiada', 'Lee', 'evffegg@gmail.com', '524411', NOW(), true),
('Jingbin', 'Zhang', 'ivy_onlyone@hotmail.com', 'iloveu4ever', NOW(), true);

/**************************************************************
* Project table, has the flag indicating whether the project is
* active or not. When marking a project as inactive, meaning
* the project is ¡°deleted¡± and will not let anyone in the project
* but only the WIKI Admin to change the status.
***************************************************************/
CREATE TABLE Project (
	ProjectID INT NOT NULL AUTO_INCREMENT,
	ProjectName VARCHAR (40) NOT NULL,
	ProjectDescription VARCHAR (150) NOT NULL,
	ProjectCreator VARCHAR (50) NOT NULL,
	ProjectCreationTime TIMESTAMP NOT NULL,
	ProjectIsActive BOOLEAN NOT NULL,
	
	PRIMARY KEY (ProjectID),
	FOREIGN KEY (ProjectCreator) REFERENCES User (EmailAddress)
);

INSERT INTO Project 
(Projectname, ProjectDescription, ProjectCreator, ProjectCreationTime, ProjectIsActive)
VALUES
('test1', 'test testtest testtest testtest test', 'hahaha2009@hotmail.com', NOW(), true),
('test2', 'test testtest testtest testtest', 'hahaha2009@hotmail.com', NOW(), true),
('test3', 'test testtest testtest testtest testtest test', 'hahaha2009@hotmail.com', NOW(), true);

CREATE TABLE Role (
	RoleID INT NOT NULL,
	RoleName VARCHAR (15) NOT NULL,
	
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

/*****************************************************************
* Record table that keeps track of user with projects. This table
* combines the projectid and the email as the primary key so
* that multiple users can be in one project and can have different
* role names.
******************************************************************/
CREATE TABLE WIKIRecord (
	EmailAddress VARCHAR (50) NOT NULL,
	ProjectID INT NOT NULL,
	Role VARCHAR (15) NOT NULL,
	
	PRIMARY KEY (ProjectID, EmailAddress),
	FOREIGN KEY (ProjectID) REFERENCES Project (ProjectID),
	FOREIGN KEY (EmailAddress) REFERENCES User (EmailAddress),
	FOREIGN KEY (Role) REFERENCES Role (RoleName)
);

INSERT INTO WIKIRecord VALUES 
('hahaha2009@hotmail.com', '1', 'Admin'),
('hahaha2009@hotmail.com', '2', 'Admin'),
/*('hahaha2009@hotmail.com', '3', 'Admin'),*/
('ivy_onlyone@hotmail.com', '1', 'Coord'),
('evffegg@gmail.com', '1', 'Contri');

CREATE TABLE RequestTable (
	UserID VARCHAR (50) NOT NULL,
	ProjectID INT NOT NULL,
	RequestRole VARCHAR (15) NOT NULL,
	
	PRIMARY KEY (UserID),
	FOREIGN KEY (ProjectID) REFERENCES Project (ProjectID) ON DELETE CASCADE,
	FOREIGN KEY (RequestRole) REFERENCES Role (RoleName)
);

INSERT INTO RequestTable VALUES
('ivy_onlyone@hotmail.com', '1', 'Coord'),
('evffegg@gmail.com', '1', 'Contri');