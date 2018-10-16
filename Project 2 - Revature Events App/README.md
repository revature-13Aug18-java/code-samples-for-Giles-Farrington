# Project 2: Revature Events Application #

### Description ###

Revature has specific events for employees usually determined on a monthly basis. 
These events include recurring events such as gameboard night, where employees get together and play popular boardgames 
and enjoy pizza provided by Revature. Some other examples of events are bicycle rides and Linkedin photo ops.
These events provided are all organized through paper, such as posters to advertise event and sign-up sheets to sign up.
Our goal was to provide an electronic source to organize, view, and sign-up for these events through a single page web application.

As a group project each of has had our own individual roles. Initially I helped create the Enitity Relationship Diagram for our database. Once the ERD was complete and began to structure and implement the backend. I was also in charge of Dev-Ops during this project, so I set up an EC2 instance with Linux and installed all dependencies and SDK's required for project to run. Set up the EC2 with Tomcat to connect with Jenkins. Once jenkins was set up all of our backend code was automatically built on the pipeline after a push to our repository through a Github web hook and Slack would notify group of new build. I also set up our remote database through Amazon RDS. Our entire group was now able to connect to the RDS and EC2 instance for a true continuous integration environment. Finally, towards the end I helped out with front-end using Angular.

**Users are able to:**

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

* Pipeline:
  * Amazon EC2 running Linux
  * PuTTY used to access Linux on EC2
  * Tomcat
  * Jenkins with Github and Slack integration
* Database used Oracle SQL on a remote Amazon RDS
* Spring Data
* Spring MVC
* Maven Project with Java 1.8 containing the following layer/packages:
  * Bean POJO's: Event and User
  * Repositories which extended JPARepository
  * Services which interacted with our repositories
  * Controllers which handled HTTP requests with our Angular front-end
* Angular 4


