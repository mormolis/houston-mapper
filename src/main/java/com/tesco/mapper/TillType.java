package com.tesco.mapper;

import com.tesco.mapper.till.type.extractors.*;

public enum TillType {

    WEPOS(WePosExtractor.getInstance()),
    DCOS(DcosExtractor.getInstance()),
    FASTLANE(FastlaneExtractor.getInstance()),
    GHS(GhsExtractor.getInstance());

    NameExtractor extractor;
    TillType(NameExtractor extractor){
        this.extractor = extractor;
    }

    public NameExtractor getExtractor(){
        return extractor;
    }
}
