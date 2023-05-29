package com.mscReceive.mscReceive.controller;

import com.mscReceive.mscReceive.model.Order;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface FileController {

    @ApiOperation("Process CSV file")
    @PostMapping("/csv/process")
    ResponseEntity<Void> processCSVFile(@RequestParam("file") String filePath);

    @ApiOperation("Create order from CSV row")
    @PostMapping("/csv/order")
    ResponseEntity<Order> createOrderFromCSVRow(@RequestBody String[] csvRow);

    @ApiOperation("Determine country from phone number")
    @PostMapping("/csv/country")
    ResponseEntity<String> determineCountry(@RequestParam("phoneNumber") String phoneNumber);
}
