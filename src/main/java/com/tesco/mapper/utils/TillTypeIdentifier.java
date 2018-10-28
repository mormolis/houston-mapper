package com.tesco.mapper.utils;

import com.tesco.mapper.TillType;
import com.tesco.mapper.till.type.extractors.NameExtractor;

public class TillTypeIdentifier {


    private static final String WPOS_REGEX = "WE\\d+POS\\d+";
    private static final String FASTLANE_REGEX = "\\d+FASTLANE\\d+";
    private static final String GHSPOS_REGEX = "GHS\\d+POS\\d+";
    private static final String GHSFSR_REGEX = "GHS\\d+FSR\\d+";
    private static final String DCOS_REGEX = "DCOS\\d+VTIL\\d+V";

    public NameExtractor returnExtractor(String tillName) {
        if (tillName.toUpperCase().matches(WPOS_REGEX)) {
            return TillType.WEPOS.getExtractor();
        } else if (tillName.toUpperCase().matches(FASTLANE_REGEX)) {
            return TillType.FASTLANE.getExtractor();
        } else if (tillName.toUpperCase().matches(GHSPOS_REGEX) || tillName.toUpperCase().matches(GHSFSR_REGEX)) {
            return TillType.GHS.getExtractor();
        } else if (tillName.toUpperCase().matches(DCOS_REGEX)) {
            return TillType.DCOS.getExtractor();
        }

        throw new RuntimeException("Error while trying to identify the type of till");
    }
}
