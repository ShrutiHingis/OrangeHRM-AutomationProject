package com.orangehrm.api;

import com.orangehrm.utils.JsonReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tools.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class EmployeeApiTest {

        private String createdEmployeeId;

        @Test
        public void validateEmployeeViaAPI() throws Exception {

            // Base URL
            RestAssured.baseURI = "https://reqres.in/api";

            // Read employee data from JSON
            String filePath =
                    "src/test/resources/test-data/Employee.json";

            JsonNode employeeData =
                    JsonReader.readJsonFile(filePath);

            String firstName =
                    employeeData.get("firstName").asString();

            String lastName =
                    employeeData.get("lastName").asString();

            String jobTitle =
                    employeeData.get("jobTitle").asString();

            // Create request body
            Map<String, String> requestBody =
                    new HashMap<>();

            requestBody.put(
                    "name",
                    firstName + " " + lastName
            );

            requestBody.put(
                    "job",
                    jobTitle
            );

            // Send POST request
            Response response =
                    given()
                            .header("x-api-key", "reqres-free-v1")
                            .contentType("application/json")
                            .body(requestBody)
                            .when()
                            .post("/users");

            // Verify response status
            Assert.assertEquals(
                    response.statusCode(),
                    201,
                    "Employee was not created through API"
            );

            // Print API response
            response.prettyPrint();

            // Verify employee name
            Assert.assertEquals(
                    response.jsonPath().getString("name"),
                    firstName + " " + lastName,
                    "Employee name does not match"
            );

            // Verify job title
            Assert.assertEquals(
                    response.jsonPath().getString("job"),
                    jobTitle,
                    "Job Title does not match"
            );

            // Store API-generated ID
            createdEmployeeId =
                    response.jsonPath().getString("id");

            System.out.println(
                    "API Employee created successfully. ID: "
                            + createdEmployeeId
            );
        }

        @Test(dependsOnMethods = "validateEmployeeViaAPI")
        public void deleteEmployeeViaAPI() {

            Response response =
                    given()
                            .header("x-api-key", "reqres-free-v1")
                            .when()
                            .delete("/users/" + createdEmployeeId);

            // Verify delete response
            Assert.assertEquals(
                    response.statusCode(),
                    204,
                    "Employee was not deleted through API"
            );

            System.out.println(
                    "API Employee deleted successfully. ID: "
                            + createdEmployeeId
            );
        }
}
