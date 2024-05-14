## FONOAPI
- Required Fonoapi is not working because of load issues 
- Below are the api's i have used
- https://fonoapi.freshpixl.com/v1/getToken - to get token , this is passed as a query param to device api
- https://fonoapi.freshpixl.com/v1/getdevice - to get device details
- Assumed if i pass full device name , i get response details for that device
- Could not test it since the Fonoapi is down
- At the application started , i call fonoapi for each mobile device and load the response in cache
  This i have used as a workaround in case the api isn't working 

# Liquibase
It is used to deploy databas changes

## What aspect of this exercise did you find most interesting?
The most interesting part of the exercise is around the workaround which we use when fonoapi is down.
This also provides a mechanism to have a caching layer which help you to return the response faster and also does not bombard the downstream with many request.
Cache expiration time is set to 30 mins , considering the information about device does not change very often
 
## What did you find most cumbersome?  
Most cumbersome , i would say adding the database scheme and database initial data set of 9 mobiles using liquibase. Although liquibase helps to bring up our schema and initial data load  , but creating the xml for that can be considered cumbersome.

Also , i face challenges in terms of Fonoapi since the api isnt working , had to take few assumptions about response structure of api's and testing became a bit of a task