package com.tesco.mapper.till.type.extractors;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DcosExtractorTest {

    @Test
    public void extractNameForJson_shouldExtractJsonValueFromTillName() {
        NameExtractor dcosExtractor = DcosExtractor.getInstance();

        assertThat(dcosExtractor.extractNameForJson("DCOS9999VTIL07V"), equalTo("9999:07"));
        assertThat(dcosExtractor.extractNameForJson("DCOS9999VTIL807V"), equalTo("9999:807"));
        assertThat(dcosExtractor.extractNameForJson("DCOS9999VTIL7V"), equalTo("9999:7"));
    }

    @Test
    public void extractNameForJson_shouldReturnEmptyStringWhenTillNameIsLessThan14Charachters(){
        NameExtractor dcosExtractor = DcosExtractor.getInstance();

        assertThat(dcosExtractor.extractNameForJson("DCOS9999VT"), equalTo(""));
    }
}