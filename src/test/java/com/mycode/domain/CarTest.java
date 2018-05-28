/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahossein
 */
public class CarTest {

    public CarTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMaintenanceTasksList method, of class Car.
     */
    @Test
    public void testGetMaintenanceTasksList() {
        System.out.println("getMaintenanceTasksList");
        Car instance = new Car();
        String[] expResult = new String[]{"Oil Change", "Tire Rotation"};
        String[] result = instance.getMaintenanceTasksList();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of all getters in one method, of class Car.
     */
    @Test
    public void testGetters() {
        short year = 2010;
        Car instance = new Car("ABC", "Honda", "Accord", year, 100000, "Red", "Ex-V6");

        //VIN
        String expVin = "ABC";
        String resVin = instance.getVin();
        assertEquals(expVin, resVin);

        //Make
        String expMake = "Honda";
        String resMake = instance.getMake();
        assertEquals(expMake, resMake);

        //Model
        String expModel = "Accord";
        String resModel = instance.getModel();
        assertEquals(expModel, resModel);

        //Year
        Short expYear = year;
        Short resYear = instance.getYear();
        assertEquals(expYear, resYear);

        //Mileage
        int expMileage = 100000;
        int resMilage = instance.getMileage();
        assertEquals(expMileage, resMilage);
        
        //Trim
        String expTrim = "Ex-V6";
        String resTrim = instance.getTrim();
        assertEquals(expTrim, resTrim);
    }

    /**
     * Test of all setter methods, of class Car.
     */
    @Test
    public void testSetters() {
        short year = 2010;
        Car expected = new Car("ABC", "Honda", "Accord", year, 100000, "Red", "Ex-V6");
        Car result = new Car();
        
        result.setVin("ABC");
        result.setColor("Red");
        result.setTrim("Ex-V6");
        result.setMake("Honda");
        result.setModel("Accord");
        result.setYear(year);
        result.setMileage(100000);
        
        assertEquals(expected, result);
    }

    /**
     * Test of equalsAnyAttributes method, of class Car.
     */
    @Test
    public void testEqualsAnyAttributes() {
        System.out.println("equalsAnyAttributes");
        Car car = null;
        Car instance = new Car();
        boolean expResult = false;
        boolean result = instance.equalsAnyAttributes(car);
        assertEquals(expResult, result);
    }

}
