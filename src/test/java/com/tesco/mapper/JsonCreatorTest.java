package com.tesco.mapper;

import com.tesco.mapper.dtos.Defaults;
import com.tesco.mapper.dtos.Tills;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class JsonCreatorTest {

    private JsonCreator jsonCreator;

    @Mock
    private Defaults defaultsMock;

    @Mock
    private Tills tillsMock;

    @Before
    public void init(){
        jsonCreator = new JsonCreator("00:00", "05:00");
    }

    @Test
    public void getTillNamesFromFile_shouldReadTheCsvAndCreateAListWithAllTheTills(){
        Set<String> tillnames = jsonCreator.getTillNamesFromFile();

        assertThat(tillnames.size(), equalTo(11));
    }

    @Test
    public void validateWindowTime_shouldTurnTimeToDefaultIfTimeNotGivenInRightFormat() {
        JsonCreator jsonCreator = new JsonCreator("1231231", "05000");

        assertThat(jsonCreator.getWindowFrom(), CoreMatchers.equalTo(JsonCreator.DEFAULT_WINDOW_FROM));
        assertThat(jsonCreator.getWindowTo(), CoreMatchers.equalTo(JsonCreator.DEFAULT_WINDOW_TO));
    }

    @Test
    public void validayWindowTime_shouldReturnTheGivenTimeIfInRightFormat(){
        JsonCreator jsonCreator = new JsonCreator("12:00", "21:00");

        assertThat(jsonCreator.getWindowFrom(), CoreMatchers.equalTo("12:00"));
        assertThat(jsonCreator.getWindowTo(), CoreMatchers.equalTo("21:00"));
    }

    @Test
    public void validateWindowTime_shouldTurnTimeToDefaultIfHourGivenIsWrong() {
        JsonCreator jsonCreator = new JsonCreator("55:00", "12:00");

        assertThat(jsonCreator.getWindowFrom(), CoreMatchers.equalTo(JsonCreator.DEFAULT_WINDOW_FROM));
        assertThat(jsonCreator.getWindowTo(), CoreMatchers.equalTo("12:00"));
    }

    @Test
    public void validateWindowTime_shouldTurnTimeToDefaultIfMinuteGivenIsWrong() {
        JsonCreator jsonCreator = new JsonCreator("00:00", "12:60");

        assertThat(jsonCreator.getWindowFrom(), CoreMatchers.equalTo("00:00"));
        assertThat(jsonCreator.getWindowTo(), CoreMatchers.equalTo("05:00"));
    }

}