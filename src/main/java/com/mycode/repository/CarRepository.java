/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.repository;

import com.mycode.domain.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Abbas Hosseini <abbas.hosseini@guest-tek.com>
 */
public interface CarRepository extends MongoRepository<Car, Integer> {

    public Iterable<Car> findByVin(String VIN);

}
