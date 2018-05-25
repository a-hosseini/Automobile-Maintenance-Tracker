/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author ahossein
 */
@Document(collection = "electric_car")
public class ElectricCar extends Car {

    public ElectricCar() {
    }

    public ElectricCar(Car sentData) {
        super(sentData);
    }

    @Override
    public String[] getMaintenanceTasksList() {
        return new String[]{"Tire Rotation", "Batterÿ Charger Check"};

    }
}
