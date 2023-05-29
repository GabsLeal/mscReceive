package com.mscReceive.mscReceive.service;

import com.mscReceive.mscReceive.command.ReadCSVFile;
import com.mscReceive.mscReceive.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private ServiceB serviceB;
    private ReadCSVFile readCSVFile;
    @Override
    public ResponseEntity<Void> processCSVFile(String filePath) {
        try {
            List<String[]> csvRows = readCSVFile.readCSVFile(filePath);
            for (String[] csvRow : csvRows) {
                Order order = createOrderFromCSVRow(csvRow);
                serviceB.persistOrder(order);
            }
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            // Handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public Order createOrderFromCSVRow(String[] csvRow) {
        String phoneNumber = csvRow[0];
        String country = determineCountry(phoneNumber);
        Order order = new Order();
        order.setPhoneNumber(phoneNumber);
        order.setCountry(country);
        return order;
    }

    @Override
    public String determineCountry(String phoneNumber) {
        // Apply the regular expressions to determine the country based on the phone number
        if (phoneNumber.matches("\\(237\\)\\ ?[2368]\\d{7,8}$")) {
            return "Cameroon";
        } else if (phoneNumber.matches("\\(251\\)\\ ?[1-59]\\d{8}$")) {
            return "Ethiopia";
        } else if (phoneNumber.matches("\\(212\\)\\ ?[5-9]\\d{8}$")) {
            return "Morocco";
        } else if (phoneNumber.matches("\\(258\\)\\ ?[28]\\d{7,8}$")) {
            return "Mozambique";
        } else if (phoneNumber.matches("\\(256\\)\\ ?\\d{9}$")) {
            return "Uganda";
        } else {
            return "Unknown";
        }
    }
}
