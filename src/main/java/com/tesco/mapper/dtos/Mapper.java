package com.tesco.mapper.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mapper {

    private String windowFrom;
    private String windowTo;

    private Defaults defaults;

    @JsonProperty("tills")
    private Map<String, Map<String, String>> serverToVersionAndDate;

    public Mapper(String windowFrom, String windowTo, Defaults defaults) {
        this.windowFrom = windowFrom;
        this.windowTo = windowTo;
        this.defaults = defaults;
        this.serverToVersionAndDate = new HashMap<>();
    }

    public String getWindowFrom() {
        return windowFrom;
    }

    public String getWindowTo() {
        return windowTo;
    }

    public Defaults getDefaults() {
        return defaults;
    }

    public void populateWithEntry(String server, String version, String date) {
        Map<String, String> versionAndDate = new HashMap<>();
        versionAndDate.put(version, date);
        serverToVersionAndDate.put(server, versionAndDate);
    }




}
