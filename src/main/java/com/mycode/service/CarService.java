/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.service;

import com.mycode.domain.Car;

/**
 *
 * @author ahossein
 * @param <T>
 */
public interface CarService<T extends Car> {

    public Car findByVin(String VIN);

    public Car generateACarObject();
}
