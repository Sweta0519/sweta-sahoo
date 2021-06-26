**Rest API tests for** https://petstore3.swagger.io/ 

**TEST CASES**

1. Add new pet to the store.

2. Find pet by ID's from the store.

3. Update an existing pet from the store.

4. Delete an existing pet from the store

5. To get pet details that doesn't exist from the store.

6. To get pet details with put request.

7. To get category details from the store.

8. To find name of the pet from the store

9. To update invalid pet details.

10. To update pet details with get request.

11. To update pet details that doesn't exist from the store.

12. To add pet details with already existing Pet ID.

13. To delete pet details that doesn't exist from the store.


This repository contains example tests for sample Petstore server.

**Toolbox**:

- Test automation framework: TestNG
- Reporting: ReportNG
- API client/testing framework: rest-assured
- Build tool: Maven
- Java 1.8+
In order to run the tests execute:

```mvn clean test```

After execution completes, report will be available in ```target/surefire-reports/html/index.html```


**Explanation:**
In order to automate the test cases, added the TestNG framework dependencies along with the Rest Assured API testing Framework. And I have choosen this framwork, because it allows the testing of API's using simple methods like given(), when(), then().

