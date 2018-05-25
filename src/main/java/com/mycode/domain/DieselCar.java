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

    @Override
    public String getType(){
        return CarEnum.DIESEL.getName();
    }
}
