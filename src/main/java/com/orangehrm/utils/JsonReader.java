package com.orangehrm.utils;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
public class JsonReader {
    private static final ObjectMapper objMapper = new ObjectMapper();

    public static JsonNode readJsonFile(String filePath) throws JacksonException {
        return objMapper.readTree(new File(filePath));
    }
}
