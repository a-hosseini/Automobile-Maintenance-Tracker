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
@Document(collection = "gas_car")
public class GasCar extends Car {

    public GasCar() {
    }

    public GasCar(Car sentData) {
        super(sentData);
    }
}
