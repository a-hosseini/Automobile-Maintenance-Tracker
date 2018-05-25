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
public enum CarEnum {

    DIESEL("Diesel"),
    GAS("Gas"),
    ELECTRIC("Electric");

    private String name;

    private CarEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
