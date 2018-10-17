# Project 3: RideForce Ride-Share Application #

**Link to Repository:** https://github.com/revaturelabs/rideshare-client

### Description ###

For new Revature employees without a car, it can be difficult to find a ride to work without expensive alternatives.
This initial difficulty for some new employees may turn them off from the Revature experience.
Thus our goal was to create a mobile friendly single page web application in which employees can interact with eachother to determine rides in a simple and intuitive manner.

This project was batch-wide, thus our batch was split into multiple groups: Dev-Ops, Front-end, and MicroServices was further split into: Users, Maps, and Matching. Dev-Ops group handled the pipeline, while the front-end group implemented the single page web application through Angular. Users group implemented the back-end for handling all user information. The maps group implemented back-end for all information pertaining to the maps aspect while integrating with and utilizing Google Maps API. Finally the matching group handled back-end and algorithms for determining how the matching system worked within our application.

I was placed within the Angular front-end group, thus all my work was focused on user interaction. This project had a mobile friendly focus in mind so during styling implementation of our single-page application, it was important to ensure everything looked nice and professional on a mobile view. I mainly focused on implementing and styling the navigation bar which contained all important links to each area of our application. The navigation bar also has a settings icon with a drop down menu selection. I also was in charge of footer styling, ensuring that the footer always remained on bottom of page and information contained within always correctly displayed with different window sizes and mobile view. I also implemented and wrote the entire About page, which contains all information regarding our application for users to understand how to interact and navigate it. I also worked on the main landing page view and helped other group-members with their respective duties.
	
### User Stories ###

* Access application through phone/desktop browser
* Create trainer accounts and generate code for their batch to create accounts
* Create an account with a generated code given by their trainer
* While registering new account, specify whether a driver and can give rides or a passenger and looking for rides
* Login/logout with created account 
* View information on how to use the application through the about page
* Edit account information
* If passenger account, swipe through drivers, view some information about them, and like/dislike them
* Order of swipeable drivers determined by location, so closer drivers will appear first
* If driver, view profiles which have liked them and like/dislike them
* If passenger and driver like eachother, view contact information and contact eachother about future rides
* View map where it focuses on your location and pins are located where nearby drivers/employees currently reside
* View list of drivers which you have previously liked 

### Tech Stack ###

* Maven project utilizing Java 1.8
* Relational database with Oracle SQL hosted on Amazon RDS
* Pipeline utilizing Amazon EC2 and Jenkins
* Spring Boot
* Angular 6
* Bootstrap
* Amazon S3 for hosting web application


