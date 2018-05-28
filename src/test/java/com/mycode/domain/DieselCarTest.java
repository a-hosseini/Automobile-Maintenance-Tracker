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
public class DieselCarTest {

    public DieselCarTest() {
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
     * Test of getMaintenanceTasksList method, of class DieselCar.
     */
    @Test
    public void testGetMaintenanceTasksList() {
        System.out.println("getMaintenanceTasksList");
        Car instance = new DieselCar();
        String[] expResult = new String[]{"Oil Change", "Tire Rotation", "Combustion Leak Check"};
        String[] result = instance.getMaintenanceTasksList();
        assertArrayEquals(expResult, result);
    }

   /**
     * Test of getType method, of class DieselCar.
     */
    @Test
    public void testClassType() {
        System.out.println("getType");
        Car instance = new DieselCar();
        String expResult = CarEnum.DIESEL.getName();
        String result = instance.getType();
        assertEquals(expResult, result);
    }
    
}
