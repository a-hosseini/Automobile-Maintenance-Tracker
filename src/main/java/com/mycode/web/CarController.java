/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.web;

import com.mycode.domain.Car;
import com.mycode.domain.CarEnum;
import com.mycode.domain.DieselCar;
import com.mycode.domain.ElectricCar;
import com.mycode.domain.GasCar;
import com.mycode.repository.CarRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    @Autowired
    private CarRepository carRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Car>> find(@RequestParam(required = false) String VIN) throws ParseException {
        List<Car> list = new ArrayList<>();
        if (VIN != null) {
            Car car = carRepository.findByVin(VIN);
            car.add(linkTo(methodOn(CarController.class).find(VIN)).withSelfRel());
            list.add(car);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        list = carRepository.findAll();
        list.forEach(item -> { // This can also be done with ResourceAssemblers. 
            try {
                item.add(linkTo(methodOn(CarController.class).find(item.getVin())).withSelfRel());
            } catch (ParseException ex) {
                LOGGER.error(null, ex);
            }
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestBody Car sentData) throws ParseException {
        try {
            carRepository.delete(sentData);
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Car> update(@RequestBody Car sentData) throws ParseException {
        Car car;
        if (sentData.getType() == null) {
            return new ResponseEntity("Vehicle type has to be specified to be able to update it.", HttpStatus.BAD_REQUEST);
        } else if (sentData.getType().equalsIgnoreCase(CarEnum.GAS.getName())) {
            car = carRepository.save(new GasCar(sentData));
        } else if (sentData.getType().equalsIgnoreCase(CarEnum.ELECTRIC.getName())) {
            car = carRepository.save(new ElectricCar(sentData));
        } else if (sentData.getType().equalsIgnoreCase(CarEnum.DIESEL.getName())) {
            car = carRepository.save(new DieselCar(sentData));
        } else {
            return new ResponseEntity("The vehicle type is not chosen right!. \nPlease pick 'Gas','Diesel' or 'Electric'", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(car, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<?> createMany(@RequestBody Car[] sentData) {
        try {
            List<Car> du = carRepository.insert(Arrays.asList(sentData));
            LOGGER.debug("'" + sentData.length + "' records stored.");
            return new ResponseEntity<>(du, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Failed to store records due to: ", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Car sentData) {
        try {
            if (sentData.getType() == null) {
                return new ResponseEntity("The vehicle type is not chosen!. \nPlease pick 'Gas','Diesel' or 'Electric'", HttpStatus.BAD_REQUEST);
            }
            Car car;
            if (sentData.getType().equalsIgnoreCase(CarEnum.GAS.getName())) {
                car = carRepository.insert(new GasCar(sentData));
            } else if (sentData.getType().equalsIgnoreCase(CarEnum.ELECTRIC.getName())) {
                car = carRepository.insert(new ElectricCar(sentData));
            } else if (sentData.getType().equalsIgnoreCase(CarEnum.DIESEL.getName())) {
                car = carRepository.insert(new DieselCar(sentData));
            } else {
                return new ResponseEntity("The vehicle type is not chosen right!. \nPlease pick 'Gas','Diesel' or 'Electric'", HttpStatus.BAD_REQUEST);
            }
            LOGGER.debug("'1' record stored.");
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Failed to store records due to: ", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
