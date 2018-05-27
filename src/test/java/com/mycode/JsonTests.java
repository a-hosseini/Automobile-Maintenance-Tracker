/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode;

import com.mycode.domain.Car;
import com.mycode.repository.CarRepository;
import com.mycode.service.CarService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ahossein
 */
@RunWith(SpringRunner.class)
@JsonTest
public class JsonTests {

    @Autowired
    private JacksonTester<Car> json;

    @Autowired
    private CarRepository repository;
    @Autowired
    private CarService carService;
    

    @Test
    public void testSerialize() throws Exception {
        Car details = carService.generateACarObject();
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(details)).isEqualToJson("expected.json");
        // Or use JSON path based assertions
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
                .isEqualTo("Honda");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new Car());
        assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
    }
}
