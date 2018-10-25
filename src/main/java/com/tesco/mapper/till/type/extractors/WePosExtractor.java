package com.tesco.mapper.till.type.extractors;

public class WePosExtractor implements NameExtractable{

    @Override
    public String extractNameForJson(String tillName) {
        if(tillName.length() >= 10){
            return tillName.substring(2,6) + ":" + tillName.substring(9);
        }
        return "";
    }
}
