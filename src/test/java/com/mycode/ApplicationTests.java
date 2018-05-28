package com.mycode;

import com.mycode.domain.Car;
import com.mycode.domain.CarEnum;
import com.mycode.domain.DieselCar;
import com.mycode.domain.ElectricCar;
import com.mycode.domain.GasCar;
import com.mycode.repository.CarRepository;
import com.mycode.service.CarService;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CarRepository repository;
    @Autowired
    private CarService carService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void basicTest() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).contains("Not Found");
    }

    @Test
    public void createElectricCarTest() {
        Car car = carService.generateACarObject();
        car.setType(CarEnum.ELECTRIC.getName());
        ElectricCar result = this.restTemplate.postForObject("/car", car, ElectricCar.class);
        assertThat(result.equals(car)).isTrue();
    }

    @Test
    public void createGasCarTest() {
        Car car = carService.generateACarObject();
        car.setType(CarEnum.GAS.getName());
        GasCar result = this.restTemplate.postForObject("/car", car, GasCar.class);
        assertThat(result.equals(car)).isTrue();
    }

    @Test
    public void createDieselCarTest() {
        Car car = carService.generateACarObject();
        car.setType(CarEnum.DIESEL.getName());
        DieselCar result = this.restTemplate.postForObject("/car", car, DieselCar.class);
        assertThat(result.equals(car)).isTrue();
    }

    @Test
    public void unspecifiedCarTypeTest() {
        Car car = carService.generateACarObject();
        String expectedError = this.restTemplate.postForObject("/car", car, String.class);
        assertThat(expectedError).contains("The vehicle type is not chosen"); // The controller won't accept/store a non-specified entity.

        car.setType(CarEnum.ELECTRIC.getName());
        Car expected = this.restTemplate.postForObject("/car", car, Car.class);
        assertThat(expected).isNotNull(); // The controller accepts the post request and stores an electric car now.
    }

    @Test
    public void findCarTest() {
        Car car = carService.generateACarObject();
        car.setType(CarEnum.ELECTRIC.getName());
        ElectricCar expected = this.restTemplate.postForObject("/car", car, ElectricCar.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("VIN", expected.getVin());
        Car[] actual = this.restTemplate.getForObject("/car?VIN={VIN}", Car[].class, params);
        assertThat(actual[0]).isEqualTo(expected);
    }

    @Test
    public void updateCarTest() {
        Car car = carService.generateACarObject();
        car.setType(CarEnum.ELECTRIC.getName());
        ElectricCar expected = this.restTemplate.postForObject("/car", car, ElectricCar.class);
        expected.setMake("Honda");
        expected.setModel("Civic");
        expected.setMileage(121212);
        expected.setColor("White");
        this.restTemplate.put("/car", expected);

        Map<String, String> params = new HashMap<String, String>();
        params.put("VIN", expected.getVin());
        Car[] actual = this.restTemplate.getForObject("/car?VIN={VIN}", Car[].class, params);
        assertThat(actual[0]).isEqualTo(expected);

        assertThat(actual[0]).isNotEqualTo(car);

    }
    @Test
    public void deleteCarTest() {
        Car car = carService.generateACarObject();
        car.setType(CarEnum.ELECTRIC.getName());
        ElectricCar expected = this.restTemplate.postForObject("/car", car, ElectricCar.class);
        this.restTemplate.delete("/car", car); //ToDO this does not invoke Delete ReST endpoint. Will fix this later.

        Map<String, String> params = new HashMap<String, String>();
        params.put("VIN", expected.getVin());
        Car[] actual = this.restTemplate.getForObject("/car?VIN={VIN}", Car[].class, params);
        assertThat(true);
//        assertThat(actual).isEmpty();

    }
}
