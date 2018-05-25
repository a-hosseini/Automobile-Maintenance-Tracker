/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.web;

import com.mycode.domain.Car;
import com.mycode.domain.DieselCar;
import com.mycode.domain.ElectricCar;
import com.mycode.domain.GasCar;
import com.mycode.repository.CarRepository;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abbas Hosseini <abbas.hosseini@guest-tek.com>
 */
@RestController
@RequestMapping("/car")
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    @Autowired
    private CarRepository carRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Car>> aggregateAll(@RequestParam(required = false) String VIN) throws ParseException {
        if (VIN != null) {
            Iterable<Car> carList = carRepository.findByVin(VIN);
            return new ResponseEntity<>(carList, HttpStatus.OK);
        }
        return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Iterable<Car>> delete(@RequestBody Car sentData) throws ParseException {
        if (sentData != null) {
            carRepository.delete(sentData);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
        return new ResponseEntity<>(carRepository.findAll(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Car> update(@RequestBody Car sentData) throws ParseException {
        if (sentData != null) {
            Car car;
            if (sentData.getType().equalsIgnoreCase("gas")) {
                car = carRepository.save(new GasCar(sentData));
            } else if (sentData.getType().equalsIgnoreCase("electric")) {
                car = carRepository.save(new ElectricCar(sentData));
            } else {
                car = carRepository.save(new DieselCar(sentData));
            }
            return new ResponseEntity<>(car, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<?> createMany(@RequestBody Car[] sentData) {
        try {
            List<Car> du = carRepository.insert(Arrays.asList(sentData));
            LOGGER.debug("'" + sentData.length + "' records stored.");
            return new ResponseEntity<>(du, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Failed to store records due to: ", e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Car sentData) {
        try {
            Car car;
            if (sentData.getType().equalsIgnoreCase("gas")) {
                car = carRepository.insert(new GasCar(sentData));
            } else if (sentData.getType().equalsIgnoreCase("electric")) {
                car = carRepository.insert(new ElectricCar(sentData));
            } else {
                car = carRepository.insert(new DieselCar(sentData));
            }
            LOGGER.debug("'1' record stored.");
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Failed to store records due to: ", e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
