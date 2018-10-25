package com.tesco.mapper.till.type.extractors;

public class DcosExtractor implements NameExtractable {

    @Override
    public String extractNameForJson(String tillName) {
        if (tillName.length()>=14){
            return tillName.substring(4, 8) + ":" + tillName.substring(12, tillName.length() - 1);
        }
        return "";
    }
}
