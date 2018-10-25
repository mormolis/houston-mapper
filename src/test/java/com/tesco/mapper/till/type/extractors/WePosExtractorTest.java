package com.tesco.mapper.till.type.extractors;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class WePosExtractorTest {

    @Test
    public void extractNameForJson_shouldExtractJsonValueFromTillName() {
        WePosExtractor wePosExtractor = new WePosExtractor();

        assertThat(wePosExtractor.extractNameForJson("WE9999POS99"), equalTo("9999:99"));
        assertThat(wePosExtractor.extractNameForJson("WE9999POS992"), equalTo("9999:992"));
        assertThat(wePosExtractor.extractNameForJson("WE9999POS1"), equalTo("9999:1"));
    }

    @Test
    public void extractNameForJson_shouldReturnEmptyStringWhenTillNameIsLessThan10Charachters(){
        GhsExtractor wePosExtractor = new GhsExtractor();

        assertThat(wePosExtractor.extractNameForJson("WE9999PO1"), equalTo(""));
    }

}