package com.tesco.mapper.utils;

import com.tesco.mapper.till.type.extractors.DcosExtractor;
import com.tesco.mapper.till.type.extractors.FastlaneExtractor;
import com.tesco.mapper.till.type.extractors.GhsExtractor;
import com.tesco.mapper.till.type.extractors.WePosExtractor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class TillTypeIdentifierTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TillTypeIdentifier tillTypeIdentifier = new TillTypeIdentifier();

    @Test
    public void returnExtractor_shouldReturnWEPOSExtractorIfTillNamesIsWePos() {

        assertThat(tillTypeIdentifier.returnExtractor("we9999pos2"), equalTo(WePosExtractor.getInstance()));
        assertThat(tillTypeIdentifier.returnExtractor("9999fastlane2"), not(equalTo(WePosExtractor.getInstance())));
    }

    @Test
    public void returnExtractor_shouldReturnFastLaneExtractorIfTillNamesIsFastlane() {

        assertThat(tillTypeIdentifier.returnExtractor("9999FASTLANE44"), equalTo(FastlaneExtractor.getInstance()));
        assertThat(tillTypeIdentifier.returnExtractor("ghs9999pos44"), not(equalTo(FastlaneExtractor.getInstance())));
    }

    @Test
    public void returnExtractor_shouldReturnGHSExtractorIfTillNamesIsGHS() {

        assertThat(tillTypeIdentifier.returnExtractor("GHS999POS01"), equalTo(GhsExtractor.getInstance()));
        assertThat(tillTypeIdentifier.returnExtractor("GHS999FSR2"), equalTo(GhsExtractor.getInstance()));
        assertThat(tillTypeIdentifier.returnExtractor("9999FASTLANE44"), not(equalTo(GhsExtractor.getInstance())));
    }

    @Test
    public void returnExtractor_shouldReturnDCOSExtractorIfTillNamesIsDcos() {

        assertThat(tillTypeIdentifier.returnExtractor("DCOS9999VTIL07V"), equalTo(DcosExtractor.getInstance()));
    }

    @Test
    public void returnExtractor_shouldThrowExceptionWhenTillNameIsWrong() {

        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Error while trying to identify the type of till");
        assertThat(tillTypeIdentifier.returnExtractor("DCOdfS9999VTIL07V"), equalTo(DcosExtractor.getInstance()));

    }


}