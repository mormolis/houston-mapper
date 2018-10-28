package com.tesco.mapper.utils;

import com.tesco.mapper.JsonCreator;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimeDivisionerTest {


    @Test
    public void getWindowTimeInMinutes_shouldReturnTheWindowInMinutes(){
        LocalDateTime start = LocalDateTime.of(1986,10,16,23,0);
        LocalDateTime end = LocalDateTime.of(1986,10,17, 5,0);
        TimeDivisioner timeDivisioner = new TimeDivisioner(start, end, 10);
        assertThat(timeDivisioner.getWindowTimeInMinutes(), equalTo(6*60L));
    }

    @Test
    public void getWindowTimeInMinutes_shouldReturnTheWindowInMinutesForPositiveDiff(){
        LocalDateTime start2 = LocalDateTime.of(1986,10,17,1,0);
        LocalDateTime end2 = LocalDateTime.of(1986,10,17, 5,0);
        TimeDivisioner timeDivisioner1 = new TimeDivisioner(start2, end2, 0);

        assertThat(timeDivisioner1.getWindowTimeInMinutes(), equalTo(4*60L));
    }

    @Test
    public void getTimeStamps_shouldReturnAListOfTimesTheDeploymentsShouldHappenForEachTill(){
        LocalDateTime start2 = LocalDateTime.of(1986,10,17,1,0);
        LocalDateTime end2 = LocalDateTime.of(1986,10,17, 5,0);


        TimeDivisioner timeDivisioner1 = new TimeDivisioner(start2, end2,100);

        assertThat(timeDivisioner1.getTimeStamps().size(), equalTo(100));
    }

    @Test
    public void getTimeStamps_shouldReturnAListWithTheFirstElementEqualTheTimeStampOfOpening(){

        LocalDateTime start2 = LocalDateTime.of(1986,10,17,1,0);
        LocalDateTime end2 = LocalDateTime.of(1986,10,17, 5,0);


        TimeDivisioner timeDivisioner1 = new TimeDivisioner(start2, end2,100);

        assertThat(timeDivisioner1.getTimeStamps().get(0), equalTo(start2+""));
    }

    @Test
    public void getTimeStamps_shouldReturnAListWithTheLastElementBeforeTheEndOfTheWindow(){

        LocalDateTime start2 = LocalDateTime.of(1986,10,17,1,0);
        LocalDateTime end2 = LocalDateTime.of(1986,10,17, 5,0);


        TimeDivisioner timeDivisioner1 = new TimeDivisioner(start2, end2,100);

        assertTrue(LocalDateTime.parse(timeDivisioner1.getTimeStamps().get(99)).isBefore(end2));

    }

    @Test
    public void getTimeStamps_shodReturnAListWithTheLastElementBeforeTheEndOfTheWindow(){

        LocalDateTime start2 = LocalDateTime.of(1986,10,16,23,0);
        LocalDateTime end2 = LocalDateTime.of(1986,10,17, 5,0);

        TimeDivisioner timeDivisioner1 = new TimeDivisioner(start2, end2,1000);

        assertTrue(LocalDateTime.parse(timeDivisioner1.getTimeStamps().get(999)).isBefore(end2));
        assertFalse(LocalDateTime.parse(timeDivisioner1.getTimeStamps().get(999)).equals(start2));

    }






}