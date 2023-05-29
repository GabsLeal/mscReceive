package com.mscReceive.mscReceive.controller;

import com.mscReceive.mscReceive.model.Order;
import com.mscReceive.mscReceive.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
@Api(tags = "File Controller")
@RequiredArgsConstructor
public class FileControllerImpl implements FileController {

    private final FileService fileService;

    @ApiOperation("Process CSV file")
    @PostMapping("/csv/process")
    @Override
    public ResponseEntity<Void> processCSVFile(@RequestParam("file") String filePath) {
        try {
            fileService.processCSVFile(filePath);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("Create order from CSV row")
    @PostMapping("/csv/order")
    @Override
    public ResponseEntity<Order> createOrderFromCSVRow(@RequestBody String[] csvRow) {
        try {
            Order order = fileService.createOrderFromCSVRow(csvRow);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation("Determine country from phone number")
    @GetMapping("/csv/country")
    @Override
    public ResponseEntity<String> determineCountry(@RequestParam("phoneNumber") String phoneNumber) {
        try {
            String country = fileService.determineCountry(phoneNumber);
            return ResponseEntity.ok(country);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
