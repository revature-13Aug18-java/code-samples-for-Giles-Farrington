# Project 0: Console Banking Application #

### Description ###

For this project the requirement was to create a mock banking ATM system. Project zero was my first Revature project, thus the requirements were kept simple for the user interface although no use of frameworks were allowed so database connection was somewhat more complicated then later projects. Our goal for this project was to create a rational database with Oracle SQL and connect it to Java with JDBC. All user interaction is done through console, which is relatively simple compared to a single page web application, although there were many constraints to check for from user input.

### User Stories ###

* Create a user account with username and password
* Login/logout with user account
* Create bank accounts (checking or savings) and name them
* Delete bank accounts
* Deposit and withdraw into created accounts
* View balance on accounts 

### Tech Stack ###

* Build a rational database with Oracle SQL
* Maven project with Java 1.8
* Utilize JDBC to establish connection with Java and rational database 
* JDBC uses at least one of each: Statement, PreparedStatement, and CallableStatement for queries
* Created multiple custom exceptions for user input
* Within Maven project I have multiple layers/packages: 
  * POJO's: My plain old Java objects: BankAccount and Customer
  * Util: Establishes connection to database with a ConnectionFactory class
  * DAO: Data Access Objects which interact with the database through JDBC
  * Service: Interacts with DAO methods
  * Exceptions: Where all my custom exceptions are stored
  * Main: Contains all code for user interaction through console
