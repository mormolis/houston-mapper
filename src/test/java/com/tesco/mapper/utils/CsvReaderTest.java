package com.tesco.mapper.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class CsvReaderTest {

    @Test
    public void getTillNamesFromFile_shouldReadTheCsvAndCreateAListWithAllTheTills(){

        FileHandler fileHandler = new FileHandler(new ObjectMapper());

        Set<String> tillnames = fileHandler.getTillNamesFromFile();

        assertThat(tillnames.size(), equalTo(11));
    }

}