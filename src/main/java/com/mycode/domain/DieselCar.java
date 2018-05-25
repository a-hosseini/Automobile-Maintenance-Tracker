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
@Document(collection = "diesel_car")
public class DieselCar extends Car {

    public DieselCar() {
    }

    public DieselCar(Car sentData) {
        super(sentData);
    }

    @Override
    public String[] getMaintenanceTasksList() {
        return new String[]{"Oil Change", "Tire Rotation", "Combustion Leak Check"};
    }

}
