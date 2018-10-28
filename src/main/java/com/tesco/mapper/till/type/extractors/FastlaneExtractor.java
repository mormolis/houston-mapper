package com.tesco.mapper.till.type.extractors;

public class FastlaneExtractor implements NameExtractor {

    private static FastlaneExtractor fastlaneExtractor;

    private FastlaneExtractor(){}

    public static NameExtractor getInstance(){
        if(fastlaneExtractor == null){
            fastlaneExtractor = new FastlaneExtractor();
        }
        return fastlaneExtractor;
    }

    @Override
    public String extractNameForJson(String tillName) {
        if(tillName.length() >= 13) {
            return tillName.substring(0,4) + ":" + tillName.substring(12);
        }
        return "";
    }
}
