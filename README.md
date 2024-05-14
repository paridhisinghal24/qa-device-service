## To Run Project
- Right-click on your main class QaDeviceServiceApplication and select Run As > Java Application

## To Run Test
- In the JUnit view, you should see a toolbar with buttons to run tests. Click on the button with a green play icon to run all tests in your project or
- ./gradlew test

## Access the application 
   - Port : 8080 
   - URL :  localhost:8080
  
## Endpoints:
- Get all mobile : 

		curl --location 'http://localhost:8080/api/mobile'

- Add a mobile   :

	  	curl --location --request POST 'http://localhost:8080/api/mobile/Samsung test' 

- Book a mobile  : 

		curl --location --request POST 'http://localhost:8080/api/mobile/Apple iPhone 12/book' --header 'user-id: 1'
  
- Return a mobile

		curl --location --request POST 'http://localhost:8080/api/mobile/Apple iPhone 12/return' --header 'user-id: 1' 
	
- Get a user

		curl --location --request GET 'http://localhost:8080/api/user/1' --header 'Content-Type: application/json' 
  		

- Add a user

		curl --location 'http://localhost:8080/api/user' --header 'Content-Type: application/json' --data-raw '{
		"name" : "Singhal",
		"email" : "test1@example.com",
		"phoneNumber" : "+971-987678766"
		}'



## Development Environment:
- GITHUB for version control https://github.com/paridhisinghal24/qa-device-service
- Gradle 
- Java 21
- MySQL 8.0+ (If required, change the DB connection url, user and password information by updating application.properties)
- Liquibase
