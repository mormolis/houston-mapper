package com.tesco.mapper.till.type.extractors;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class GhsExtractorTest {

    @Test
    public void extractNameForJson() {

        NameExtractor ghsExtractor = GhsExtractor.getInstance();

        assertThat(ghsExtractor.extractNameForJson("GHS9999FSR01"), equalTo("9999:01"));
        assertThat(ghsExtractor.extractNameForJson("GHS9999FSR011"), equalTo("9999:011"));
        assertThat(ghsExtractor.extractNameForJson("GHS9999FSR1"), equalTo("9999:1"));
    }

    @Test
    public void extractNameForJson_shouldReturnEmptyStringWhenTillNameIsLessThan11Charachters(){
        NameExtractor ghsExtractor = GhsExtractor.getInstance();

        assertThat(ghsExtractor.extractNameForJson("GHS9999FS1"), equalTo(""));
    }
}