/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.unitTests.json;

import com.mycode.domain.Car;
import com.mycode.repository.CarRepository;
import com.mycode.service.CarService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ahossein
 */
@RunWith(SpringRunner.class)
@JsonTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class JsonTests {

    @Autowired
    private JacksonTester<Car> json;
    @Autowired
    private CarService carService;
    @MockBean
    private CarRepository carRepository;

    @MockBean
    private MongoTemplate mongoTemplatel;
            
    @Test
    public void testSerialize() throws Exception {
        short year = 2010;
        Car car = new Car("ABC", "Honda", "Accord", year, 100000, "Red", "Ex-V6");
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(car)).isEqualToJson("/expected.json");
        // Or use JSON path based assertions
        assertThat(this.json.write(car)).hasJsonPathStringValue("@.make");
        assertThat(this.json.write(car)).extractingJsonPathStringValue("@.make")
                .isEqualTo("Honda");
    }

    @Test
    public void testDeserialize() throws Exception {
        short year = 2010;
        String content = "{\"vin\":\"ABC\",\"make\":\"Honda\",\"model\":\"Accord\",\"year\":2010,\"mileage\":100000,\"color\":\"Red\",\"trim\":\"Ex-V6\",\"type\":null,\"maintenanceTasksList\":[\"Oil Change\",\"Tire Rotation\"]}";
        assertThat(this.json.parse(content))
                .isEqualTo(new Car("ABC", "Honda", "Accord", year, 100000, "Red", "Ex-V6"));
        assertThat(this.json.parseObject(content).getVin()).isEqualTo("ABC");
        assertThat(this.json.parseObject(content).getMake()).isEqualTo("Honda");
        assertThat(this.json.parseObject(content).getModel()).isEqualTo("Accord");
        assertThat(this.json.parseObject(content).getYear()).isEqualTo(year);
        assertThat(this.json.parseObject(content).getMileage()).isEqualTo(100000);
        assertThat(this.json.parseObject(content).getColor()).isEqualTo("Red");
        assertThat(this.json.parseObject(content).getTrim()).isEqualTo("Ex-V6");
    }
}
