/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.unitTests.web;

import com.mycode.domain.Car;
import com.mycode.repository.CarRepository;
import com.mycode.web.CarController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.mockito.BDDMockito.*;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author ahossein
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CarController.class, excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class ControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private MongoTemplate mongoTemplatel;

    @Test
    public void testGet() throws Exception {
        short year = 2010;
        given(this.carRepository.findByVin("ABC"))
                .willReturn(new Car("ABC", "Honda", "Accord", year, 100000, "Red", "Ex-V6"));
        this.mvc.perform(get("/car").param("VIN", "ABC").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("[{\"vin\":\"ABC\",\"make\":\"Honda\",\"model\":\"Accord\",\"year\":2010,\"mileage\":100000,\"color\":\"Red\",\"trim\":\"Ex-V6\",\"type\":null,\"maintenanceTasksList\":[\"Oil Change\",\"Tire Rotation\"]}]"));
    }

    @Test
    public void testCreate() throws Exception {
        short year = 2010;
        given(this.carRepository.findByVin("ABC"))
                .willReturn(new Car("ABC", "Honda", "Accord", year, 100000, "Red", "Ex-V6"));
        this.mvc.perform(get("/car").param("VIN", "ABC").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("[{\"vin\":\"ABC\",\"make\":\"Honda\",\"model\":\"Accord\",\"year\":2010,\"mileage\":100000,\"color\":\"Red\",\"trim\":\"Ex-V6\",\"type\":null,\"maintenanceTasksList\":[\"Oil Change\",\"Tire Rotation\"]}]"));
    }
}
