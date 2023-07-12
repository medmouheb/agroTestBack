package com.agrotech.api.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.agrotech.api.exceptions.CSVReaderException;
import com.agrotech.api.exceptions.EmptyFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CSVReader {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<CSVRecord> read(MultipartFile file) throws CSVReaderException, EmptyFileException {
        if(!hasCSVFormat(file)){
            throw new CSVReaderException("Invalid CSV file");
        }
        try {
            InputStream is = file.getInputStream();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            List<CSVRecord> records = csvParser.getRecords();
            if(records.isEmpty()){
                throw new EmptyFileException("Empty CSV file");
            }
            csvParser.close();
            fileReader.close();
            is.close();
            return records;

            /*for (CSVRecord csvRecord : csvRecords) {
                Tutorial tutorial = new Tutorial(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Title"),
                        csvRecord.get("Description"),
                        Boolean.parseBoolean(csvRecord.get("Published"))
                );

                tutorials.add(tutorial);
            }*/

        } catch (IOException e) {
            throw new CSVReaderException("Failed to parse CSV file");
        }
    }
}
