/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.repository;

import com.mycode.domain.Car;
import com.mycode.domain.DieselCar;
import com.mycode.domain.ElectricCar;
import com.mycode.domain.GasCar;
import com.mycode.service.CarService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ahossein
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class CarRepositoryTests {

    @Autowired
    private CarRepository repository;
    @Autowired
    private CarService carService;

    /*All car types are dynamically assigned and tested in this test.*/
    @Test
    public void testCreateCars() throws Exception {
        Car car = carService.generateACarObject();
        testCreateCars(new ElectricCar(car)); // Test to see if electric cars are saved right!
        testCreateCars(new GasCar(car)); // Test to see if gas cars are saved right!
        testCreateCars(new DieselCar(car)); // Test to see if diesel cars are saved right!
    }

    @Test
    public void testFindCars() throws Exception {

        Car car = carService.generateACarObject();
        testFindCars(new ElectricCar(car)); // Test to see if electric cars are saved, searched and loaded right!
        testFindCars(new GasCar(car)); // Test to see if gas cars are saved, searched and loaded right!
        testFindCars(new DieselCar(car)); // Test to see if diesel cars are saved, searched and loaded right!

    }

    @Test
    public void testDeleteCars() throws Exception {

        Car car = carService.generateACarObject();
        testDeleteCars(new ElectricCar(car)); // Test to see if electric cars are saved and deleted right!
        testDeleteCars(new GasCar(car)); // Test to see if gas cars are saved and deleted right!
        testDeleteCars(new DieselCar(car)); // Test to see if diesel cars are saved and deleted right!

    }

    @Test
    public void testUpdateCars() throws Exception {
        Car car = carService.generateACarObject();
        Car updateCar = carService.generateACarObject();
        int count = 0;
        while (updateCar.equalsAnyAttributes(car)) { // We want to make sure all persistant attributes are updated so none of them should be equal between the objects.
            updateCar = carService.generateACarObject();
            if (count++ > 5) {// This is exteremly rare to happen but to be mindful of endless loop.
                throw new Exception("A rare endless loop happened!");
            }
        }

        // Test updates with no type change.
        testUpdateCars(new ElectricCar(car), new ElectricCar(updateCar)); // Test to see if all attributes electric cars are updated correctly with no type change.
        testUpdateCars(new GasCar(car), new GasCar(updateCar)); // Test to see if all attributes gas cars are updated correctly with no type change
        testUpdateCars(new DieselCar(car), new DieselCar(updateCar)); // Test to see if all attributes diesel cars are updated correctly with no type change

        // Test updates with all combinations of type change.
        testUpdateCars(new ElectricCar(car), new GasCar(updateCar)); // Test to see if all attributes electric cars are updated correctly. Its type should change to gas.
        testUpdateCars(new ElectricCar(car), new DieselCar(updateCar)); // Test to see if all attributes electric cars are updated correctly. Its type should change to diesel.
        testUpdateCars(new GasCar(car), new ElectricCar(updateCar)); // Test to see if all attributes gas cars are updated correctly. Its type should change to electric.
        testUpdateCars(new GasCar(car), new DieselCar(updateCar)); // Test to see if all attributes gas cars are updated correctly. Its type should change to diesel.
        testUpdateCars(new DieselCar(car), new ElectricCar(updateCar)); // Test to see if all attributes diesel cars are updated correctly. Its type should change to electric.
        testUpdateCars(new DieselCar(car), new GasCar(updateCar)); // Test to see if all attributes diesel cars are updated correctly. Its type should change to gas.
    }

    private void testCreateCars(Car newCar) {
        this.repository.delete(newCar); // Delete the row that might exist from previous tests.
        Car savedCar = this.repository.insert(newCar);
        assertThat(savedCar.getVin()).isEqualTo(newCar.getVin());
        assertThat(savedCar.getMake()).isEqualTo(newCar.getMake());
        assertThat(savedCar.getModel()).isEqualTo(newCar.getModel());
        assertThat(savedCar.getYear()).isEqualTo(newCar.getYear());
        assertThat(savedCar.getMileage()).isEqualTo(newCar.getMileage());
        assertThat(savedCar.getColor()).isEqualTo(newCar.getColor());
        assertThat(savedCar.getTrim()).isEqualTo(newCar.getTrim());
        assertThat(savedCar.getType()).isEqualTo(newCar.getType()); // This attribute is transient so it is not save in the db. This line makes sure the new car is saved and retrieved in the right subclass (Electric, Gas or Diesel). 
    }

    private void testFindCars(Car newCar) {
        this.repository.delete(newCar); // Delete the row that might exist from previous tests.
        Car savedCar = this.repository.insert(newCar);
        Car loadedCar = this.repository.findByVin(newCar.getVin());
        assertThat(loadedCar.getMake()).isEqualTo(savedCar.getMake());
        assertThat(loadedCar.getVin()).isEqualTo(savedCar.getVin());
        assertThat(savedCar.getModel()).isEqualTo(savedCar.getModel());
        assertThat(savedCar.getYear()).isEqualTo(savedCar.getYear());
        assertThat(savedCar.getMileage()).isEqualTo(savedCar.getMileage());
        assertThat(savedCar.getColor()).isEqualTo(savedCar.getColor());
        assertThat(savedCar.getTrim()).isEqualTo(savedCar.getTrim());
        assertThat(savedCar.getType()).isEqualTo(newCar.getType());
    }

    private void testDeleteCars(Car newCar) {
        this.repository.delete(newCar); // Delete the row that might exist from previous tests.
        Car savedCar = this.repository.insert(newCar);
        Car loadedCar = this.repository.findByVin(newCar.getVin());
        assertThat(loadedCar).isNotNull();
        this.repository.delete(newCar);
        Car loadedCar2 = this.repository.findByVin(newCar.getVin());
        assertThat(loadedCar2).isNull();

    }

    private void testUpdateCars(Car newCar, Car updateCar) throws Exception {
        this.repository.delete(newCar); // Delete the row that might exist from previous tests.
        Car savedCar = this.repository.insert(newCar);
        updateCar.setVin(newCar.getVin()); // Vin the the only identifier (primary key) to match/find a car for update. 
        Car updatedCar = this.repository.save(updateCar);
        assertThat(updatedCar.getMake()).isEqualTo(updateCar.getMake());
        assertThat(updatedCar.getVin()).isEqualTo(updateCar.getVin());
        assertThat(updatedCar.getModel()).isEqualTo(updateCar.getModel());
        assertThat(updatedCar.getYear()).isEqualTo(updateCar.getYear());
        assertThat(updatedCar.getMileage()).isEqualTo(updateCar.getMileage());
        assertThat(updatedCar.getColor()).isEqualTo(updateCar.getColor());
        assertThat(updatedCar.getTrim()).isEqualTo(updateCar.getTrim());

        // And of course t test equalsAnyAttributes method. The method is also test in the corresponding unit test.
        assertThat(updatedCar.getMake()).isNotEqualTo(savedCar.getMake());
        assertThat(updatedCar.getVin()).isEqualTo(savedCar.getVin()); // VINs must be equal as they are the same cars.
        assertThat(updatedCar.getModel()).isNotEqualTo(savedCar.getModel());
        assertThat(updatedCar.getYear()).isNotEqualTo(savedCar.getYear());
        assertThat(updatedCar.getMileage()).isNotEqualTo(savedCar.getMileage());
        assertThat(updatedCar.getColor()).isNotEqualTo(savedCar.getColor());
        assertThat(updatedCar.getTrim()).isNotEqualTo(savedCar.getTrim());

        // We don't change the car's subclass. But let's test it here to ensure.
        assertThat(updatedCar.getType()).isEqualTo(updateCar.getType());
    }

}
