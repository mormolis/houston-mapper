package com.tesco.mapper;

import com.tesco.mapper.dtos.Defaults;
import com.tesco.mapper.utils.FileHandler;
import org.junit.Before;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;

public class JsonCreatorTest {

    private JsonCreator jsonCreator;

    private Defaults defaults;

    @Mock
    private FileHandler fileHandlerMock;

    @Before
    public void init(){
        LocalDateTime start = LocalDateTime.of(1986,10,16,23,0);
//        jsonCreator = new JsonCreator(start, start.plusHours(5), defaults, fileHandlerMock, tills);
    }
//
//    @Test
//    public void populateTills_shouldCallTheRightExtractor(){
//        jsonCreator.populateTills();
//    }


}