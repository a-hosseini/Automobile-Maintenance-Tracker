/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.web;

import com.mycode.domain.Car;
import com.mycode.repository.CarRepository;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.mockito.BDDMockito.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ahossein
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class ControllerTests {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private CarRepository carRepository;

    @Test
    public void testExample() throws Exception {
        given(this.carRepository.findByVin("123"))
                .willReturn(new Car());
        this.mvc.perform(get("/car").param("vin", "123").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
    }

}
