package com.tesco.mapper.till.type.extractors;

public class FastlaneExtractor implements NameExtractable{

    @Override
    public String extractNameForJson(String tillName) {
        if(tillName.length() >= 13) {
            return tillName.substring(0,4) + ":" + tillName.substring(12);
        }
        return "";
    }
}
