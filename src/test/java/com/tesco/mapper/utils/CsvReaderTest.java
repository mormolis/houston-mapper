package com.tesco.mapper.utils;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class CsvReaderTest {

    @Test
    public void getTillNamesFromFile_shouldReadTheCsvAndCreateAListWithAllTheTills(){

        CsvReader csvReader = new CsvReader();

        Set<String> tillnames = csvReader.getTillNamesFromFile();

        assertThat(tillnames.size(), equalTo(11));
    }

}