package com.tesco.mapper;

public class JsonCreator {

    public static final String DEFAULT_WINDOW_FROM = "23:00";
    public static final String DEFAULT_WINDOW_TO = "05:00";

    private String windowFrom;
    private String windowTo;

    public JsonCreator(String windowFrom, String windowTo) {
        this.windowFrom =  validateWindowTime(windowFrom) ? windowFrom : DEFAULT_WINDOW_FROM;
        this.windowTo = validateWindowTime(windowTo) ? windowTo : DEFAULT_WINDOW_TO;
    }



    public JsonCreator() {
        this.windowFrom =  DEFAULT_WINDOW_FROM;
        this.windowTo = DEFAULT_WINDOW_TO;
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
