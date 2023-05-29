package com.mscReceive.mscReceive.service;

import com.mscReceive.mscReceive.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FileService {
    ResponseEntity<Void> processCSVFile(String filePath);
    Order createOrderFromCSVRow(String[] csvRow);
    String determineCountry(String phoneNumber);
}