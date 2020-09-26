# InsuranceAPI
A spring based microservice application for claiming insurance.

### Technology
 * Java 13
 * Spring Boot Framework
 * Maven
 * Postgres
 * liquibase
 
### Prerequisite

 Need to install postgres db (For more detail https://www.postgresql.org/docs/9.6/tutorial-install.html)
 
 Create database and user with password (as per name mentioned in application.properties) for postgres db. 
  (For detail: https://www.codementor.io/@engineerapart/getting-started-with-postgresql-on-mac-osx-are8jcopb)
  
### For Building and Running Application
mvn clean install

mvn spring-boot:run

### For Running Application
POST http://localhost:8331/insurance/claims/create (for creating claims) 
For exapmle:
```
 {
    "firstName": "Test",
    "lastName": "User",
	"emailId":"abcd@gmail.com",
	"policyNumber": "Policy123",
	"originalFlightNumber":"FLN113121",
	"reasonOfDelay":"Weather",
	"consequenceOfDelay" :"CANCELLATION",
	"replacementFlightDetail": {
		"flightNo":"FLN111",
		"date":"02-01-2020 06:07:59"
	},
	"delayTime":"12",
	"dateOfOriginalFlight":"22-01-2020 06:07:59"
}
```
##### For Retrieving claim details
* GET http://localhost:8331/insurance/claims/ 
* GET http://localhost:8331/insurance/claims/claim/{claimId}
* GET http://localhost:8331/insurance/claims/{emailId}
