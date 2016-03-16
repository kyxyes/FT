# Progress Report

Your progress report must include the following items:

## Team name

Personal Expense Tracker

## Team member name and email

* Ke Wang, wangke@gwu.edu
* Mengchen Pan,
* Pei He, phe@gwu.edu
* Yang Liu, yangliu1989@gwu.edu
* Yuxin Kang, kyx@gwu.edu

## Your product name

Personal Expense Tracker (or Wallet Watchdog, which maybe more cute)

## Short description

This product is intended to help users keep track of their everyday expenses. Users can easily add new transactions to their accounts, and view the categorical analysis of their historical spending data to help adjust their spending habits.

## Key accomplishments so far (what's done so far)

+ Create git usage rules
    * Create [git-guid.md] for teammates to make sure we can work together without confilcts.

+ Architecture design
    * Decide to use AngularJS in frontend
    * Decide to use Dropwizard in backend
    * Decide to use MySQL for database
    * Decide that frontend and backend communicate using REST API and JSON data structure

+ Web UI : Draw the mockup version
    * Came up with a rough design for the front end by referencing major financial tracking websites and financial services websites, and finished mockup drawing [ui-design.md]

+ Frontend design:
      * Already use RequireJS to design module for different functional parts
      * Build the basic frame 
      * Use bower as the front end package manager
      * Choose AngularMaterial as the UI compnents  

+ Frontend web page development
    * Framework setup : Yuxin Kang, Oct 30
    * User management pages, including login, register, update user info : Pei He, Nov. 21
    * Transaction management pages, including search, add, etc. : Yang Liu, Nov. 21
    * Analysis page, including different aspect and different presenting type : Yang Liu, Nov. 21

+ Testing : NPM test for front-end testing
    * Create a directory in your package root path.
    * Define test directory in package descriptor under directories section.
    * Define test script in package descriptor under scripts section.
    * Define dependency on this testing package.
    * Write tests
    * Test your package by running all tests npm test or run individual tests node ./path/to/test/group.js

+ Integration testing

+ Web API design
    * Create [api-design.md] which define the interface between frontend and backend development, so that both sides can be developed individually.

+ Backend development
    * Database design, create initializing SQL : Ke Wang, Oct 24.
    * Dropwizard framework preparation : Ke Wang, Oct 26.
    * API layer development
        - User management API : Ke Wang, Nov 1.
        - Expense entry API : Ke Wang, Nov 3.
        - Category API : Ke Wang, Nov 7.
    * Core Business unit test : Ke Wang, Nov 25.
    * Core Business development, including analysis report module : Ke Wang, Nov 15.
    * API layer development
        - Analysis API : Ke Wang, Nov 15.

+ Accomplishments track : 
    * Architecture design : all teammates
    * Web API Design ([api-design.md]) : Ke Wang
    * Build Frontend frame use RequireJS: Yang Liu, Yuxin Kang
    * Frontend design: Pei He, Mengchen Pan
    * Backend developement: Ke Wang

## Tasks left for the rest of semester (what needs to be done):



## Open Issues (things that you don't know what to do):

* [No response for link #7]



[git-guid.md]: https://github.com/GWU-KIM-CSCI/financial-tracker/blob/master/doc/git-guide.md
[ui-design.md]: https://github.com/GWU-KIM-CSCI/financial-tracker/blob/master/doc/ui-design.md
[api-design.md]: https://github.com/GWU-KIM-CSCI/financial-tracker/blob/master/doc/api-design.md
[No response for link #7]: https://github.com/GWU-KIM-CSCI/financial-tracker/issues/7
