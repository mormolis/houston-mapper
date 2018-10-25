package com.tesco.mapper.till.type.extractors;

public class GhsExtractor implements NameExtractable{

    @Override
    public String extractNameForJson(String tillName) {
        if(tillName.length() >= 11){
            return tillName.substring(3,7) + ":" + tillName.substring(10);
        }
        return "";
    }
}
