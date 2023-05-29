package com.mscReceive.mscReceive.command;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.util.List;

@Slf4j
public class ReadCSVFile {

    public List<String[]> readCSVFile(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();
        } catch (Exception e) {
            log.error("Error occurred while reading CSV file: {}", e.getMessage());
            return null;
        }
    }

}