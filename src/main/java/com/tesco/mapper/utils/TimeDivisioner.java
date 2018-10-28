package com.tesco.mapper.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeDivisioner {

    public static final String DEFAULT_WINDOW_FROM = "23:00";
    public static final String DEFAULT_WINDOW_TO = "05:00";
    public static final int NUMBER_OF_TILLS_PER_GROUP = 100;

    private LocalDateTime startingLocalDateTime;
    private LocalDateTime endingLocalDateTime;
    private String startingTime;
    private String endingTime;
    private int numberOfTills;


    public TimeDivisioner(LocalDateTime startingLocalDateTime, LocalDateTime endingLocalDateTime, int numberOfTills) {
        this.startingLocalDateTime = startingLocalDateTime;
        this.endingLocalDateTime = endingLocalDateTime;
        this.startingTime = returnValidatedTimeString(startingLocalDateTime, DEFAULT_WINDOW_FROM);
        this.endingTime = returnValidatedTimeString(endingLocalDateTime, DEFAULT_WINDOW_TO);
        this.numberOfTills = numberOfTills;
    }


    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public int getNumberOfTills() {
        return numberOfTills;
    }

    public long getWindowTimeInMinutes() {
        LocalTime start = LocalTime.parse(startingTime);
        LocalTime end = LocalTime.parse(endingTime);
        long minutes = Duration.between(start, end).getSeconds() / 60;
        minutes = minutes > 0 ? minutes : (24 * 60 + minutes);
        return minutes;
    }

    private String extractHour(LocalDateTime localDateTime) {
        String hour = localDateTime.getHour() + "";
        if (hour.length() == 1) {
            return "0" + hour;
        }
        return hour;
    }

    private String extractMinute(LocalDateTime localDateTime) {
        String minute = localDateTime.getMinute() + "";
        if (minute.length() == 1) {
            return "0" + minute;
        }
        return minute;
    }

    private boolean validateWindowTime(String time) {

        return time.matches("\\d\\d:\\d\\d") && isTimeWithinLimits(time);
    }

    private boolean isTimeWithinLimits(String time) {
        String[] splitTime = time.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);

        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }

    private String returnValidatedTimeString(LocalDateTime localDateTime, String defaultTime) {
        String hour = extractHour(localDateTime);
        String minute = extractMinute(localDateTime);
        return validateWindowTime(hour + ":" + minute) ? hour + ":" + minute : defaultTime;
    }

    public List<String> getTimeStamps() {

        List<String> timestamps = new ArrayList<>();
        int minutesPerTillGroup;
        int availableMinutes = (int) getWindowTimeInMinutes();
        int numberOfGroups = numberOfTills / NUMBER_OF_TILLS_PER_GROUP;

        if (numberOfGroups != 0) {
            minutesPerTillGroup = availableMinutes / numberOfGroups;
            generateTimeStampsForGroupedTills(minutesPerTillGroup, numberOfGroups, timestamps);
        } else {
            generateTimeStampForNonGroupedTills(timestamps);
        }

        return timestamps;
    }

    private void generateTimeStampForNonGroupedTills(List<String> timestamps) {
        for (int i = 0; i < numberOfTills; i++) {
            timestamps.add(startingLocalDateTime + "");
        }
    }

    private void generateTimeStampsForGroupedTills(int minutesPerTillGroup, int numberOfGroups, List<String> timestamps) {
        int tillCounter = 0;
        int minutesToAdd = 0;
        for (int groups = 0; groups < numberOfGroups; groups++) {
            for (int i = 0; i < NUMBER_OF_TILLS_PER_GROUP; i++) {
                timestamps.add(startingLocalDateTime.plusMinutes(minutesToAdd) + "");
                tillCounter++;
                if (tillCounter == numberOfTills) {
                    break;
                }
            }
            minutesToAdd+=minutesPerTillGroup;
        }
    }
}
