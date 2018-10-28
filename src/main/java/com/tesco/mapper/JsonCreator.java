package com.tesco.mapper;

import com.tesco.mapper.dtos.Defaults;
import com.tesco.mapper.dtos.Tills;
import com.tesco.mapper.utils.CsvReader;
import com.tesco.mapper.utils.TillTypeIdentifier;
import com.tesco.mapper.utils.TimeDivisioner;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JsonCreator {

    private LocalDateTime windowFrom;
    private LocalDateTime windowTo;
    private Defaults defaults;
    private Tills tills;
    private CsvReader csvReader;
    private TillTypeIdentifier tillTypeIdentifier;
    private TimeDivisioner timeDivisioner;

    private String versionToDeploy;

    public JsonCreator (LocalDateTime windowFrom,
                        LocalDateTime windowTo,
                        Defaults defaults,
                        CsvReader csvReader,
                        Tills tills,
                        TillTypeIdentifier tillTypeIdentifier,
                        TimeDivisioner timeDivisioner,
                        String versionToDeploy){

        this.windowFrom = windowFrom;
        this.windowTo = windowTo;
        this.defaults = defaults;
        this.csvReader = csvReader;
        this.tillTypeIdentifier = tillTypeIdentifier;
        this.versionToDeploy = versionToDeploy;

        populateTills();
    }


    public LocalDateTime getWindowFrom() {
        return windowFrom;
    }

    public LocalDateTime getWindowTo() {
        return windowTo;
    }

    private void populateTills(){
        List<String> timestampForEachTill =  timeDivisioner.getTimeStamps();

        Set<String> tillsUnformated = csvReader.getTillNamesFromFile();
        int index = 0;
        for (String unformatedTill : tillsUnformated) {
            String tillNameFormated = tillTypeIdentifier.returnExtractor(unformatedTill).extractNameForJson(unformatedTill);
            tills.populateWithEntry(tillNameFormated, versionToDeploy, timestampForEachTill.get(index));
        }
    }


}
