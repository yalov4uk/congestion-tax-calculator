package com.yalauchuk.http.controller;


import com.yalauchuk.calculator.CongestionTaxCalculator;
import com.yalauchuk.calculator.model.Vehicle;
import com.yalauchuk.http.api.VehicleAndDatesRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/calculations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CongestionTaxCalculatorController {

    private final CongestionTaxCalculator congestionTaxCalculator;

    public CongestionTaxCalculatorController(CongestionTaxCalculator congestionTaxCalculator) {
        this.congestionTaxCalculator = congestionTaxCalculator;
    }

    @PostMapping
    public int calculate(@RequestBody VehicleAndDatesRequest vehicleAndDatesRequest) {
        return congestionTaxCalculator.getTax(Vehicle.of(vehicleAndDatesRequest.vehicleType()),
                vehicleAndDatesRequest.dates());
    }
}
