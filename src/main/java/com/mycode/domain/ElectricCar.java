/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.domain;

/**
 *
 * @author ahossein
 */
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

    @Override
    public String getType() {
        return CarEnum.ELECTRIC.getName();
    }
}
