package de.neuefische.springexceptionhandlingtask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @GetMapping("{species}")
    String getAnimalSpecies(@PathVariable String species) {
        if (!species.equals("dog")) {
            throw new IllegalArgumentException("Only 'dog' is allowed");
        }
        return species;
    }

    @GetMapping
    String getAllAnimals() {
        throw new NoSuchElementException("No Animals found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}