/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.service.impl;

import com.mycode.domain.Car;
import com.mycode.domain.util.StringUtils;
import com.mycode.repository.CarRepository;
import com.mycode.service.CarService;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl<T extends Car> implements CarService<T> {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car findByVin(String VIN) {
        return carRepository.findByVin(VIN);
    }

    @Override
    public Car generateACarObject() {
        Random rnd = new Random();
        String vin = "VIN_".concat(StringUtils.generateARandomString(rnd, 20));
        String make = "MAKE_".concat(StringUtils.generateARandomString(rnd, 5));
        String model = "MODEL_".concat(StringUtils.generateARandomString(rnd, 5));
        Short year = new Integer(1950 + rnd.nextInt(68)).shortValue();
        int mileage = rnd.nextInt(1000000);
        String color = "COLOR_".concat(StringUtils.generateARandomString(rnd, 5));
        String trim = "TRIM_".concat(StringUtils.generateARandomString(rnd, 5));
        return new Car(vin, make, model, year, mileage, color, trim);
    }

}
