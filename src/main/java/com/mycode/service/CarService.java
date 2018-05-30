/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.service;

import com.mycode.domain.Car;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author ahossein
 * @param <T>
 */
@CrossOrigin(origins = "http://localhost:4200")
public interface CarService<T extends Car> {

    public Car findByVin(String VIN);

    public Car generateACarObject();
}
