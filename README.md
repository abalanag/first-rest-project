[x] Database

[x] Create package

[x] Retrieve information by id

[x] Retrieve information by title

[x] Delete package by id

[] Package list for a specific city by date

[] Delivered package value by date

Please implement a small logistics application that will allow an operator to track information about deliveries.
The application will be a Spring Boot web application, backed by a Postgres database. The project will be managed using Maven.
The application will publish a RESTful API. The output will be presented as JSON documents when we call the endpoints.
Please design and implement the appropriate database table.
For each package you need to have the following data:

- package title
- target city
- target distance in km
- package value
- delivery date

Create REST endpoints which allows a user to create a package, retrieve information about a package by id, retrieve information about a package by title, delete a package (knowing its id). Create the URL paths which make sense and use the appropriate HTTP methods for each operation.

Create a REST endpoint which retrieves a list of packages that will be delivered (be careful not to return packages which were delivered on dates which already passed) to the specified city. The city is specified as part of the URL.

Create a REST endpoint which retrieves the total value of all delivered packages on a given date. The date is specified as part of the URL.



Create a private GitHub repository and invite the trainer (danutchindris) as a collaborator.

Paste the URL of your repository as answer to this quiz question.