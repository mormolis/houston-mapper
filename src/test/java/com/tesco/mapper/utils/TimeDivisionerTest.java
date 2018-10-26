package com.tesco.mapper.utils;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class TimeDivisionerTest {

    private TimeDivisioner timeDivisioner;

    @Before
    public void init(){

        timeDivisioner = new TimeDivisioner("23:00", "05:00", 10);
        LocalTime start = LocalTime.parse("23:00");
        LocalTime end = LocalTime.parse("05:00");
        long seconds = Duration.between(start, end).getSeconds();
        seconds = seconds > 0 ? seconds : (24*60*60 + seconds);
        System.out.println(seconds/(60*60));


    }

    @Test
    public void voidance(){

    }





}