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
public class GasCar extends Car {

    public GasCar() {
    }

    public GasCar(Car sentData) {
        super(sentData);
    }

    @Override
    public String getType() {
        return CarEnum.GAS.getName();
    }
}
