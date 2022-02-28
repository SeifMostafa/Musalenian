package com.musala.MDS.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.musala.MDS.Producer;
import com.musala.MDS.entity.Musalenian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {


    @Autowired
    Producer producer;
    /*
    to allow user to register
     */
    @PostMapping
    public ResponseEntity<Musalenian> register(@RequestBody Musalenian musalenian) throws JsonProcessingException {
        /// invoke kafka producer  -> insert into database --> return response entity
        producer.register(musalenian);
        return ResponseEntity.status(HttpStatus.CREATED).body(musalenian);
    }

    /*
    to allow user to modify registration
     */
    @PutMapping
    public void modifyRegistration() {

    }

    /*
    to allow user to delete registration, delete user record
     */
    @DeleteMapping
    public void deleteRegistration() {

    }
}
