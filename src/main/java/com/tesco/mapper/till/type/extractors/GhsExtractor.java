package com.tesco.mapper.till.type.extractors;

public class GhsExtractor implements NameExtractor {

    private static GhsExtractor ghsExtractor;

    private GhsExtractor(){}

    public static NameExtractor getInstance(){
        if(ghsExtractor == null){
            ghsExtractor = new GhsExtractor();
        }
        return ghsExtractor;
    }

    @Override
    public String extractNameForJson(String tillName) {
        if(tillName.length() >= 11){
            return tillName.substring(3,7) + ":" + tillName.substring(10);
        }
        return "";
    }
}
