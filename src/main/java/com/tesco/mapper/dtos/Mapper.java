package com.tesco.mapper.dtos;

import java.util.Objects;

public class Mapper {

    private String windowFrom;
    private String windowTo;

    private Defaults defaults;

    private Tills tills;

    public Mapper(String windowFrom, String windowTo, Defaults defaults, Tills tills) {
        this.windowFrom = windowFrom;
        this.windowTo = windowTo;
        this.defaults = defaults;
        this.tills = tills;
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

    public Tills getTills() {
        return tills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mapper)) return false;
        Mapper mapper = (Mapper) o;
        return Objects.equals(getWindowFrom(), mapper.getWindowFrom()) &&
                Objects.equals(getWindowTo(), mapper.getWindowTo()) &&
                Objects.equals(getDefaults(), mapper.getDefaults()) &&
                Objects.equals(getTills(), mapper.getTills());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getWindowFrom(), getWindowTo(), getDefaults(), getTills());
    }

}
