package com.yalauchuk.http.controller;

import com.yalauchuk.calculator.model.Car;
import com.yalauchuk.http.api.VehicleAndDatesRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CongestionTaxCalculatorControllerTest {

    @LocalServerPort
    int localServerPort;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldCallCalculationsEndpoint() throws ParseException {
        VehicleAndDatesRequest request = new VehicleAndDatesRequest(Car.NAME, new Date[]{
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-14 15:00:00")
        });

        ResponseEntity<Integer> actual = restTemplate.postForEntity(
                "http://localhost:" + localServerPort + "/api/calculations", request, Integer.class);

        Assertions.assertEquals(13, actual.getBody());
    }
}