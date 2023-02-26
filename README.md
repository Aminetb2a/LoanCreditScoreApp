# Patika.dev / DefineX Java Spring Practicum Final Case Content

This is the Final Case test application of
the [DefineX Java Spring Practicum](https://cohorts.patika.dev/cohortDetails/definex-java-spring-practicum/events)
program hosted by [Patika.dev](https://patika.dev/).

## Project Subject :

Writing a restful application for a loan application system, which will take the loan application requests and return
the loan result to the customer according to the relevant criteria, using the Spring Boot framework and optionally
writing the frontend.

### Backend

Using the identity number, name-surname, monthly income, phone information, date of birth and the collateral (optional)
identity number, the credit score service, which is assumed to have been written before, is accessed and the credit
score
of the relevant person is obtained and the credit result (Approval or Rejection) is shown to the user according to the
following rules.

#### Rules:

- New users can be defined in the system, existing customers can be updated or deleted.
- If the credit score is below 500, the user is rejected. (Credit result: Rejected)
- If the credit score is between 500 points and 1000 points and if the monthly income is below 5000 TL,
  the user's loan application is approved and a limit of 10,000 TL is assigned to the user. (Credit Result: Approval).
  - If he has given a guarantee, 10 percent of the amount of the guarantee is added to the credit limit.
- If the credit score is between 500 points and 1000 points and the monthly income is between 5000 TL and 10,000 TL, the
  loan application of the user is approved and a limit of 20,000 TL is assigned to the user. (Credit Result: Approval)
  - If a guarantee has been given, 20 percent of the guarantee amount is added to the credit limit.
- If the credit score is between 500 points and 1000 points and the monthly income is above 10.000 TL, the loan
  application of the user is approved and the user is assigned a limit of `MONTHLY INCOME INFORMATION * CREDIT LIMIT
  MULTIPLIER/2`. (Credit Result: Approval)
  - If a guarantee is given, 25 percent of the guarantee amount is added to the
    credit limit.
- If the credit score is equal to or above 1000 points, the user is assigned a limit equal to `MONTHLY INCOME * CREDIT
  LIMIT MULTIPLIER`. (Credit Result: Approval)
  - If a guarantee is given, 50 percent of the guarantee amount is added to the
    credit limit.

As a result of the conclusion of the loan, the relevant application is recorded in the database.

Afterwards, an informative SMS is sent to the relevant phone number and the approval status information (rejection or
approval), limit
information is returned from the endpoint.

A completed loan application can only be queried with the ID number and date of birth. If the date of birth and identity
information do not match, it should not be questioned.

#### `Notes: The credit limit multiplier is 4 by default.`

### Frontend (optional):

Identity number, name-surname, monthly income, telephone information, date of birth and collateral (optional)
information are obtained with the form and the user is shown the credit result and credit limit.

JavaScript, HTML, CSS are sufficient in the simplest way, optionally front-end framework can be used. Interface design
and experience is left to you.

### Expectations from the Project (Scorecard):

The backend project works correctly according to the specified rules (adding the readme file or writing the Dockerfile)

Paying attention to the quality (Clean Code), structuring (Structure) of the code, being open to development for new
features and being understandable. (SOLID principles)

Writing a test

Using Design Patterns

Documentation (Swagger, OpenApi etc.)

Use of technologies such as NoSQL, RDBMS(ORM) and Hibernate (JPA)

Establishment of logging mechanism

Frontend project running

## Properties

This application uses MongoDB's Atlas (Local) database, to run it properly you need to set the username and password of
your database first.
And it uses an SMS service (Twilio) that you need to assign your username, password and phone number.

### Documentation

You need to check the [documentation] (http://localhost:8080/swagger-ui/index.html) to be able to use this application
API correctly.

![Application Documentation](src/main/assests/Loan Credit Application.png?raw=true "Loan Application Documentation")


