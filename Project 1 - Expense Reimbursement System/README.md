# Project 1: Expense Reimbursement System #

### Description ###

This project was my first true full-stack application. An expense reimbursement system is a good relatively simple first full-stack task to take on individually and it was an amazing learning experience. My goal was to create a clean, simple, and pleasing to look at single page web application that handled the required tasks. From an employee standpoint, these tasks included creating an account, submitting new reimbursements of desired types and amount, and viewing all of their respective reimbursements showing the current staus of each. From a manager standpoint, they also are able to create an account, view all reimbursements submitted by employees, filture by status, and finally the ability to approve or deny reimbursements. 

My first step was to create the relational database with Oracle SQL hosted on an Amazon RDS instance. Create a Maven project with Java 1.8 and connect to database through JDBC. Create HTML, CSS, and Javascript which connects to Java through Ajax calls and Java servlets.


### User Stories ###

* Access the application through their browser
* Create an employee account 
* Create a finance manager account
* Login/logout with desired account type
* Employee accounts can create reimbursement 
* Employees can view previously submitted reimbursements and see whether they are pending, denied, or approved
* Finance managers can view every submitted reimbursement request from all employees
* Finance managers can filter reimbursements to only view pending 
* Finance managers can approve or deny pending reimbursements 

### Tech Stack ###

* Relational Database using Oracle SQL hosted on Amazon RDS
* Java Servlets
* JDBC for connection and queries with remote database
* JavaScript with AJAX calls for handling JSON 
* HTML and CSS
* Maven Project with Java 1.8 containing the following layers/packages:
  * POJO's: Plain Old Java Objects: Reimbursement, Reimbursement Status, Reimbursement Type, User Roles, and Users
  * Util for establishing connection with JDBC using a ConnectionFactory class
  * DAO: Data Access Objects using JDBC for queries to and from database
  * Service layer for handling interactions with DAO layer
  * Servlets for handling interaction with front-end JavaScript and HTML views
  * Web folder containing Javascript, HTML, and CSS
* Tomcat to establish connection with browser

  
