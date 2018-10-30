package com.tesco.mapper.utils;

import com.tesco.mapper.dtos.Mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileHandler {

    private static final String TILL_LIST_CSV = File.separator + "tills-to-deploy-to.csv";

    private ObjectMapper objectMapper;

    public FileHandler(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public Set<String> getTillNamesFromFile() {

        Set<String> tillNames = new HashSet<>();
        try {
            populateSetWithTillNamesFromCSV(tillNames);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return tillNames;
    }

    public void createJsonFromObject(Mapper mapper) {
        try {
            String path = System.getenv("JSON_MAPPER_PATH");
            File file = new File(path);
            objectMapper.writeValue(file, mapper);
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("could not create mapper.json");
        }

    }

    private void populateSetWithTillNamesFromCSV(Set<String> tillNames) throws URISyntaxException {
        String line;
        URL urlOfFileWithTills = this.getClass().getResource(TILL_LIST_CSV);
        String tillsCsvPath = Paths.get(urlOfFileWithTills.toURI()).toAbsolutePath().toString();
        try (BufferedReader br = new BufferedReader(new FileReader(tillsCsvPath))) {
            while ((line = br.readLine()) != null) {
                tillNames.addAll(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
