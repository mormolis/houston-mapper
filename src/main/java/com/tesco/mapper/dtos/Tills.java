package com.tesco.mapper.dtos;

import java.util.HashMap;
import java.util.Map;

public class Tills {

    private Map<String, Map<String, String>> serverToVersionAndDate;

    public Tills() {
        this.serverToVersionAndDate = new HashMap<>();
    }

    public void populateWithEntry(String server, String version, String date) {
        Map<String, String> versionAndDate = new HashMap<>();
        versionAndDate.put(version, date);
        serverToVersionAndDate.put(server, versionAndDate);
    }

    public Map<String, Map<String, String>> getServerToVersionAndDate() {
        return serverToVersionAndDate;
    }

    @Override
    public String toString() {
        return "Tills{" +
                "serverToVersionAndDate=" + serverToVersionAndDate +
                '}';
    }
}
