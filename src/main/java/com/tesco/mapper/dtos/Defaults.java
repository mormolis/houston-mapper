package com.tesco.mapper.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Defaults {

    @JsonProperty("default")
    private String defaultVersion;

    public Defaults(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    public String getDefaultVersion() {
        return defaultVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Defaults)) return false;
        Defaults defaults = (Defaults) o;
        return Objects.equals(getDefaultVersion(), defaults.getDefaultVersion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDefaultVersion());
    }

    @Override
    public String toString() {
        return "defaults\": {" +
                "default\": \"" + defaultVersion + '\"' +
                '}';
    }
}
