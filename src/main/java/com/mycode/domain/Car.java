/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.domain;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.lang.NonNull;

/**
 *
 * @author ahossein
 */
public class Car implements Serializable {

    @Id
    private String vin;
    @NonNull
    private String make;
    private String model;
    private short year;
    private int mileage;
    private String color;
    private String trim;
    @Transient
    private String type; //Diesel, Electric, Gas

    public Car() {

    }

    public Car(String vin, String make, String model, short year, int mileage, String color, String trim) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.trim = trim;
//        this.type = sentData.type;
    }

    public Car(Car sentData) {
        this.vin = sentData.vin;
        this.make = sentData.make;
        this.model = sentData.model;
        this.year = sentData.year;
        this.mileage = sentData.mileage;
        this.color = sentData.color;
        this.trim = sentData.trim;
        this.type = sentData.type;
    }

    public String[] getMaintenanceTasksList() {
        return new String[]{"Oil Change", "Tire Rotation"};
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equalsAnyAttributes(Car car) {
        if (car == null) {
            return false;
        }
        if (this.vin.equalsIgnoreCase(car.getVin())
                || this.make.equalsIgnoreCase(car.getMake())
                || this.model.equalsIgnoreCase(car.getModel())
                || this.year == car.getYear()
                || this.mileage == car.getMileage()
                || this.trim.equalsIgnoreCase(car.getTrim())
                || this.color.equalsIgnoreCase(car.getColor())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.vin);
        hash = 23 * hash + Objects.hashCode(this.make);
        hash = 23 * hash + Objects.hashCode(this.model);
        hash = 23 * hash + this.year;
        hash = 23 * hash + this.mileage;
        hash = 23 * hash + Objects.hashCode(this.color);
        hash = 23 * hash + Objects.hashCode(this.trim);
        hash = 23 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
//        if (getClass() != obj.getClass()) { // commented because we want to have polymorphism.
//            return false;
//        }
        final Car other = (Car) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.trim, other.trim)) {
            return false;
        }
        return true;
    }

}
