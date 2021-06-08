
# integration-tests-demo

A Java REST API to demonstrate how **integration tests** can be made with *Rest Assured* and *Wiremock*.

## Application content
The application is a REST API of **person** domain composed by:
* HTTP GET endpoint exposed at `/persons` that returns all the persons;
* HTTP GET endpoint exposed at `/persons/{id}` that returns the given person id;
* HTTP POST endpoint exposed at `/persons` that registers the given person and return a `Location` header with only the created person id;
* HTTP GET endpoint exposed at `/check-cpf`that returns `HTTP OK - 200`  when the called API responds successfully and `HTTP BAD REQUEST - 400`  when the called API responds anything different (this endpoint is only used for testing by stubbing the called API with an invalid response).

## Running the application

Just run the main class application `src/main/java/com/ariielm/integrationtestsdemo/IntegrationTestsDemoApplication.java` Postman on the IDE or with Maven `spring-boot:run`.

To test the application, just use the Postman collection located at 

## Tests with only Rest Assured
Run the `src/test/java/com/ariielm/integrationtestsdemo/controller/PersonControllerTestIT.java` on your IDE.
The Spring boot context will be created, then the **Rest Assured** will invoke the `/persons` endpoints (e.g. line 27) and make the asserts to check if the application is working perfectly (e.g. line 31).

## Tests with Rest Assured and Wiremock
Run the `src/test/java/com/ariielm/integrationtestsdemo/controller/CheckCPFControllerTest.java` on your IDE.
The Spring boot context will be created on a dynamic port, the **Wiremock** API will be created on a different dynamic port, then **Wiremock** will stub the called endpoint (line 22), the **Rest Assured** will invoke the `/check-cpf` endpoint (line 30) and make the asserts to check if the application is working perfectly (line 32).