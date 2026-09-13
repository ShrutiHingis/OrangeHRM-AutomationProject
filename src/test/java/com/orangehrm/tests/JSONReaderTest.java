package com.orangehrm.tests;

import com.orangehrm.utils.JsonReader;
import org.testng.annotations.Test;
import tools.jackson.databind.JsonNode;

import tools.jackson.core.JacksonException;

public class JSONReaderTest {
    @Test
    public void verifyJsonData() throws JacksonException {

        String filePath = "src/test/resources/test_data/Employee.json";

        JsonNode  employeeData = JsonReader.readJsonFile(filePath);

        System.out.println("First Name: " + employeeData.get("firstName").asString());
    }
}
