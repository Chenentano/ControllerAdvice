package de.neuefische.springexceptionhandlingtask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) {
        if (!brand.equals("porsche")) {
            throw new IllegalArgumentException("Only 'porsche' allowed");
        }
        return brand;
    }

    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class})
    public ResponseEntity<String> handleExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }
}
