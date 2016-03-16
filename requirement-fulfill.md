# Final Project Requirements

## You must submit:

- a working github repository URL that contains all the code for you project

- a 10 minute presentation of the project including demo and evaluation

- a individual copy of group evaluation form (handed out later in the course)

## Your project should ...

1. Be a web application that was approved by the professor

    Yes

2. Have a service oriented architecture

    Yes

3. Not depend on single monolithic web framwork

    Yes. We seperate our core business logic with web framework. We use dropwizard as our web server but not fully depend on it.

4. Has a database component

    Yes. We use SQLite as our database to store the expense information.
 
5. Has a user management component

    Yes. We have a user management componet. User can register and login to our website. We also have the authoriation and authentication process.

6. Has a computational component

    Yes. We have an analysis component which can compute the expense percentage for each category for each user.

7. Has unit test on most core business logic

    Yes.

8. Has integrated test that includes API testing

    Yes.

9. Has a UI element that works on browser

    Yes.

All requirements are subject to change.

## Detailed Explanation

### 1. Be a web application that was approved by the professor

You may not build whatever you want. Your project must be approved by the professor prior to Oct 8th lecture.
You will automatically fail the class if you build something that's outside the professors approved project.

### 2. Have a service oriented architecture

You project must have a web API layer and a client that consumes it, preferable RESTful.
Your project must have internal API that hooks in to your core business logic.
You project must have core business logic that's agnostic of the web and the database.

### 3. Not depend on single monolithic web framwork

All of your code should not be in a single monilithic web framework like Spring MVC, Django, or Ruby on Rails.
Doing so will cause you to automatically fail the class.

### 4. Has a database component

Most if not all data needed for your computation should be persisted in a persistent database.
You may or may not use an ORM to abstract away your database. However, there should be an interface that
hooks into your database such that if one were to replace a database with some other technology, it
should be relatively easy to do so.

### 5. Has a user management component


More details to come


