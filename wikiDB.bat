@ECHO off
:: Murach's Java Servlets and JSP (2nd Edition)
:: company: Mike Murach & Associates, Inc.
:: date:    Jan 1, 2008
:: 
:: Uses the MySQL monitor to run the SQL scripts that create
:: and populate the tables in the two sample databases used
:: by this book.
c:
cd \Program Files\MySQL\MySQL Server 5.5\bin\

mysql -uroot -psesame < %~dp0\wikiDB.sql

ECHO.
ECHO If no error message is shown, the databases named murach and music were created successfully.
ECHO.

:: Display 'press any key to continue' message
PAUSE