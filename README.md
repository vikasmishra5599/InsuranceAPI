# CloudInsurance
A spring based microservice application for claiming insurance.

###Technology
 * Java13
 * Spring Boot Framework
 * Maven
  
###For Building and Running Application
mvn clean install

mvn spring-boot:run

###For Running Application
POST http://localhost:8080/cloudinsurance/claims/create (for creating claims) 
```
 {
    "firstName": "Test",
    "lastName": "User",
	"emailId":"abcd@gmail.com",
	"policyNumber": "pol123",
	"originalFlightNumber":"FLN113121",
	"reasonOfDelay":"Weather",
	"consequenceOfDelay" :"CANCELLATION",
	"replacementFlightDetail": {
		"flightNo":"CVASS",
		"date":"02-01-2020 06:07:59"
	},
	"delayTime":"12",
	"dateOfOriginalFlight":"22-01-2020 06:07:59"
}
```
#####For Retrieving claim details
* GET http://localhost:8080/cloudinsurance/claims/ 
* GET http://localhost:8080/cloudinsurance/claims/claim/{claimId}
* GET http://localhost:8080/cloudinsurance/claims/{emailId}