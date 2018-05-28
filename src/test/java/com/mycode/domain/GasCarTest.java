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
public class GasCarTest {

    public GasCarTest() {
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
     * Test of getType method, of class DieselCar.
     */
    @Test
    public void testClassType() {
        System.out.println("getType");
        Car instance = new GasCar();
        String expResult = CarEnum.GAS.getName();
        String result = instance.getType();
        assertEquals(expResult, result);
    }
}
