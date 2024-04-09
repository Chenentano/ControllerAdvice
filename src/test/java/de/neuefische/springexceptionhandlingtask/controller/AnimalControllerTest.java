package de.neuefische.springexceptionhandlingtask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAnimalSpeciesWithInvalidSpecies() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/animals/cat", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Only 'dog' is allowed", response.getBody());
    }

    @Test
    public void testGetAllAnimals() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:" + port + "/api/animals", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No Animals found", response.getBody());
    }
}