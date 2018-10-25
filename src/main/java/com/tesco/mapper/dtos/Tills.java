package com.tesco.mapper.dtos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Tills {

    private Map<String, Map<String, Date>> serverToVersionAndDate;

    public Tills() {
        this.serverToVersionAndDate = new HashMap<>();
    }

    public void populateWithEntry(String server, String version, Date date){
        Map<String, Date> versionAndDate = new HashMap<>();
        versionAndDate.put(version, date);
        serverToVersionAndDate.put(server, versionAndDate);
    }

    @Override
    public String toString() {
        return "Tills{" +
                "serverToVersionAndDate=" + serverToVersionAndDate +
                '}';
    }
}
