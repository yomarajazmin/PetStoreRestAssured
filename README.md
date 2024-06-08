# PetStoreRestAssured

*Pre-requisites*:
- Have swagger pet store running (https://github.com/swagger-api/swagger-petstore)
- Have allure installed (https://allurereport.org/docs/install-for-windows/)

To execute all the test cases:
```
gradle clean test
```
To generate the report:
```
allure serve allure-results 
```

*Test cases*:

Pets module
- Test case 1: Add a new pet to the store
- Test case 2: Update an existing pet by Id
- Test case 3: Add a new pet to the store.
- Test case 4: Find pet by status
- Test case 5: Find pet by tag
- Test case 6: Get a pet by Id
- Test case 7: Update a pet with form data
- Test case 8: Delete a pet by Id

Module Store
- Test case 9: Place a new order in the store
- Test case 10: Returns a map of status codes to quantities
- Test case 11: Find purchase order by Id
- Test case 12: Delete order by Id

User module
- Test case 13: Create user
- Test case 14: Creates list of users with given input array
- Test case 15: Logs user into the system"
- Test case 16: Logs out current logged in user session
- Test case 17: Get user by username
- Test case 18: Update user
- Test case 19: Delete user

This solution uses RestAssured + Java + Gradle + Junit as base for automating the test cases proposed. 
The folder structure is composed of:
- endpoints: Classes that group requests and paths are located there
- entities: POJO classes can be found here
- payloads: All payloads needed when sending a request are inside this folder
- test: These classes have the test scripts proposed. 