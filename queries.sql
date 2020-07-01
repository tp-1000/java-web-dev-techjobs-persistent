## Part 1: Test it with SQL
/* results ---
id 			int PK
employer 	varchar(255)
name 		varchar(255)
skills 		varchar(255)
-------end */

SELECT COLUMN_NAME, DATA_TYPE
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = "techjobs" and TABLE_NAME = "job";

##or

DESCRIBE job;

## Part 2: Test it with SQL

## Part 3: Test it with SQL

## Part 4: Test it with SQL