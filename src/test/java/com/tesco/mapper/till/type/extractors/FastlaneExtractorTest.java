package com.tesco.mapper.till.type.extractors;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class FastlaneExtractorTest {

    @Test
    public void extractNameForJson_shouldExtractJsonValueFromTillName() {
        NameExtractor fastlaneExtractor = FastlaneExtractor.getInstance();

        assertThat(fastlaneExtractor.extractNameForJson("9999FASTLANE9"), equalTo("9999:9"));
        assertThat(fastlaneExtractor.extractNameForJson("9999FASTLANE90"), equalTo("9999:90"));
        assertThat(fastlaneExtractor.extractNameForJson("9999FASTLANE999"), equalTo("9999:999"));

    }

    @Test
    public void extractNameForJson_shouldReturnEmptyStringWhenTillNameIsLessThan13Charachters(){
        NameExtractor fastlaneExtractor = FastlaneExtractor.getInstance();

        assertThat(fastlaneExtractor.extractNameForJson("9999FASNE9"), equalTo(""));
    }
}