## Employee SOAP WS Api

To run
`mvn spring-boot:run`

This app runs on port 10002.


It contains an endpoint for consuming SOAP Web Service directly (See the Employee REST API repo).

In the `db_scripts` folder, the `script.sql` file is located. Its purpose is to generate the MySQL
database script (Employee table) when connected to the database with the user and passwd. 
The port of MySQL service must be `3306`.


