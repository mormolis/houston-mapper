package com.tesco.mapper;

import com.tesco.mapper.dtos.Defaults;
import com.tesco.mapper.dtos.Tills;
import com.tesco.mapper.utils.CsvReader;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class JsonCreatorTest {

    private JsonCreator jsonCreator;

    private Defaults defaults;

    private Tills tills;

    @Mock
    private CsvReader csvReaderMock;

    @Before
    public void init(){
        LocalDateTime start = LocalDateTime.of(1986,10,16,23,0);
//        jsonCreator = new JsonCreator(start, start.plusHours(5), defaults, csvReaderMock, tills);
    }
//
//    @Test
//    public void populateTills_shouldCallTheRightExtractor(){
//        jsonCreator.populateTills();
//    }


}