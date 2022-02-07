package com.yalauchuk.http.api;

import java.util.Date;

public record VehicleAndDatesRequest(String vehicleType, Date[] dates) {
}
