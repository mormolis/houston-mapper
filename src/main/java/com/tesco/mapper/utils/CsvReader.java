package com.tesco.mapper.utils;

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

public class CsvReader {

    private static final String TILL_LIST_CSV = File.separator + "tills-to-deploy-to.csv";

    public Set<String> getTillNamesFromFile() {

        Set<String> tillNames = new HashSet<>();
        try {
            populateSetWithTillNamesFromCSV(tillNames);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return tillNames;
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
