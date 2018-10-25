package com.tesco.mapper;

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

public class JsonCreator {

    public static final String DEFAULT_WINDOW_FROM = "23:00";
    public static final String DEFAULT_WINDOW_TO = "05:00";

    private static final String TILL_LIST_CSV = File.separator + "tills-to-deploy-to.csv";
    private String windowFrom;
    private String windowTo;

    public JsonCreator(String windowFrom, String windowTo) {
        this.windowFrom =  validateWindowTime(windowFrom) ? windowFrom : DEFAULT_WINDOW_FROM;;
        this.windowTo = validateWindowTime(windowTo) ? windowTo : DEFAULT_WINDOW_TO;;
    }

    public JsonCreator() {
        this.windowFrom =  DEFAULT_WINDOW_FROM;
        this.windowTo = DEFAULT_WINDOW_TO;
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

    private boolean validateWindowTime(String time) {

        return time.matches("\\d\\d:\\d\\d") && isTimeWithinLimmits(time);
    }

    private boolean isTimeWithinLimmits(String time) {
        String[] splitedTime = time.split(":");
        int hour = Integer.parseInt(splitedTime[0]);
        int minute = Integer.parseInt(splitedTime[1]);

        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }

    public String getWindowFrom() {
        return windowFrom;
    }

    public String getWindowTo() {
        return windowTo;
    }
}
