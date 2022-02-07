package com.yalauchuk.calculator;

import com.yalauchuk.calculator.model.Car;
import com.yalauchuk.calculator.model.Motorcycle;
import com.yalauchuk.calculator.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

class CongestionTaxCalculatorTest {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    CongestionTaxCalculator congestionTaxCalculator = new CongestionTaxCalculator();

    public static Stream<Arguments> getDateAndVehicleData() throws ParseException {
        return Stream.of(
                // Happy cases
                Arguments.of(Vehicle.of(Car.NAME), dateFormat.parse("2013-02-14 15:00:00"), 13),
                Arguments.of(Vehicle.of(Car.NAME), dateFormat.parse("2013-02-14 14:59:59"), 8),
                Arguments.of(Vehicle.of(Car.NAME), dateFormat.parse("2013-02-14 05:59:59"), 0),
                // Exceptional vehicles
                Arguments.of(Vehicle.of(Motorcycle.NAME), dateFormat.parse("2013-02-14 15:00:00"), 0),
                Arguments.of(Vehicle.of("Bus"), dateFormat.parse("2013-02-15 15:00:00"), 0),
                // Holidays
                Arguments.of(Vehicle.of(Car.NAME), dateFormat.parse("2013-07-14 15:00:00"), 0),
                Arguments.of(Vehicle.of(Car.NAME), dateFormat.parse("2013-04-01 15:00:00"), 0)
        );
    }

    public static Stream<Arguments> getDatesAndVehicleData() throws ParseException {
        return Stream.of(
                // Happy cases
                Arguments.of(Vehicle.of(Car.NAME), new Date[]{
                        dateFormat.parse("2013-02-14 15:00:00"),
                        dateFormat.parse("2013-02-14 16:59:00")
                }, 31),
                Arguments.of(Vehicle.of(Car.NAME), new Date[]{
                        dateFormat.parse("2013-02-14 15:00:00"),
                        dateFormat.parse("2013-02-24 15:00:00")
                }, 26),
                Arguments.of(Vehicle.of(Car.NAME), new Date[]{
                        dateFormat.parse("2013-02-14 07:00:00"),
                        dateFormat.parse("2013-02-15 07:00:00"),
                        dateFormat.parse("2013-02-16 07:00:00"),
                        dateFormat.parse("2013-02-17 07:00:00")
                }, 72),
                // 60 min rule
                Arguments.of(Vehicle.of(Car.NAME), new Date[]{
                        dateFormat.parse("2013-02-14 14:59:00"),
                        dateFormat.parse("2013-02-14 15:05:00")
                }, 13),
                Arguments.of(Vehicle.of(Car.NAME), new Date[]{
                        dateFormat.parse("2013-02-14 06:29:00"),
                        dateFormat.parse("2013-02-15 06:30:00"),
                        dateFormat.parse("2013-02-16 07:00:00")
                }, 18),
                // 60 per day and vehicle
                Arguments.of(Vehicle.of(Car.NAME), new Date[]{
                        dateFormat.parse("2013-02-14 06:00:00"),
                        dateFormat.parse("2013-02-14 06:30:00"),
                        dateFormat.parse("2013-02-14 07:00:00"),
                        dateFormat.parse("2013-02-14 08:00:00"),
                        dateFormat.parse("2013-02-14 08:30:00"),
                        dateFormat.parse("2013-02-14 15:00:00"),
                        dateFormat.parse("2013-02-14 15:30:00"),
                        dateFormat.parse("2013-02-14 17:00:00"),
                        dateFormat.parse("2013-02-14 18:00:00")
                }, 60)
        );
    }

    @ParameterizedTest
    @MethodSource("getDateAndVehicleData")
    void shouldGetTollFee(Vehicle vehicle, Date date, int expected) {
        int actual = congestionTaxCalculator.GetTollFee(date, vehicle);

        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getDatesAndVehicleData")
    void shouldGetTax(Vehicle vehicle, Date[] dates, int expected) {
        int actual = congestionTaxCalculator.getTax(vehicle, dates);

        Assertions.assertEquals(expected, actual);
    }
}
