package com.tesco.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tesco.mapper.dtos.Defaults;
import com.tesco.mapper.dtos.Mapper;
import com.tesco.mapper.utils.FileHandler;
import com.tesco.mapper.utils.TillTypeIdentifier;
import com.tesco.mapper.utils.TimeDivisioner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JsonCreator {

    private Mapper mapper;
    private FileHandler fileHandler;
    private TillTypeIdentifier tillTypeIdentifier;
    private TimeDivisioner timeDivisioner;

    private String versionToDeploy;

    public JsonCreator(FileHandler fileHandler,
                       Mapper mapper,
                       TillTypeIdentifier tillTypeIdentifier,
                       TimeDivisioner timeDivisioner,
                       String versionToDeploy) {

        this.fileHandler = fileHandler;
        this.tillTypeIdentifier = tillTypeIdentifier;
        this.versionToDeploy = versionToDeploy;
        this.mapper = mapper;
        this.timeDivisioner = timeDivisioner;

        populateTills();
    }

    private void populateTills() {
        List<String> timestampForEachTill = timeDivisioner.getTimeStamps();

        Set<String> tillsUnformated = fileHandler.getTillNamesFromFile();
        int index = 0;
        for (String unformatedTill : tillsUnformated) {
            String tillNameFormated = tillTypeIdentifier.returnExtractor(unformatedTill).extractNameForJson(unformatedTill);
            mapper.populateWithEntry(tillNameFormated, versionToDeploy, timestampForEachTill.get(index));
        }
    }

    public void generateMapper() {
        fileHandler.createJsonFromObject(mapper);
    }


    // arg [0] app version
    // arg [1] (Optional) default version
    // arg[2] (optional) window from
    // arg[3] (optional) window to
    // as system env we get the path to the file that needs to be created
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 4) {
            throw new IllegalArgumentException("wrong number of arguments");
        }

        String versionToDeploy = args[0];
        Defaults defaults = null;
        LocalDateTime windowFrom = LocalDateTime.now().withHour(23).withMinute(0);
        LocalDateTime windowTo = LocalDateTime.now().plusDays(1).withHour(5).withMinute(0);

        if (args.length == 2) {
            defaults = new Defaults(args[1]);
        }

        if(args.length > 2 && args[2] != null && args[3] != null) {
            windowFrom = LocalDateTime.parse(args[1]);
            windowTo = LocalDateTime.parse(args[2]);
        }


        FileHandler fileHandler = new FileHandler(new ObjectMapper());
        TimeDivisioner timeDivisioner = new TimeDivisioner(windowFrom, windowTo, fileHandler.getTillNamesFromFile().size());
        TillTypeIdentifier tillTypeIdentifier = new TillTypeIdentifier();
        String windowFromForMapper = timeDivisioner.extractHour(windowFrom) + ":" + timeDivisioner.extractMinute(windowFrom);
        String windowToForMapper = timeDivisioner.extractHour(windowTo) + ":" + timeDivisioner.extractMinute(windowTo);
        Mapper mapper = new Mapper(windowFromForMapper, windowToForMapper, defaults);
        JsonCreator jsonCreator = new JsonCreator(fileHandler, mapper, tillTypeIdentifier, timeDivisioner, versionToDeploy);

        jsonCreator.generateMapper();
    }
}
