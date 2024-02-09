package TicketPackage.controller;

import TicketPackage.model.Registration;
import TicketPackage.service.RegistrationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

// This annotation is used at the class level and is used to define the base request mapping path
@RestController
@RequestMapping("/registrations")
// This annotation is used to specify a security requirement for a specific operation
@SecurityRequirement(name = "javainuseapi")
public class RegistrationController {

    // The service that will be used to process registration data
    private final RegistrationService registrationService;

    // This annotation is used to automatically inject objects into our class
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // This annotation is used for mapping HTTP POST requests onto specific handler methods
    @PostMapping
    public ResponseEntity<Registration> create(@RequestBody @Valid Registration registration) {
        Registration createdRegistration = registrationService.create(registration);
        return new ResponseEntity<>(createdRegistration, HttpStatus.CREATED);
    }

    // This annotation is used for mapping HTTP GET requests onto specific handler methods
    @GetMapping(path = "/{ticketCode}")
    public ResponseEntity<?> get(@PathVariable("ticketCode") String ticketCode) {
        try {
            // Try to get the registration with the given ticket code
            Registration registration = registrationService.get(ticketCode);

            // Check if registration is null or not found
            if (registration == null) {
                // If no registration is found with the given ticket code, return a 404 status code and an error message
                return new ResponseEntity<>("Registration with ticket code " + ticketCode + " not found", HttpStatus.NOT_FOUND);
            }

            // If registration is found, return it with 200 OK status
            return new ResponseEntity<>(registration, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // If an exception occurs, return a 404 status code and an error message
            return new ResponseEntity<>("Registration with ticket code " + ticketCode + " not found", HttpStatus.NOT_FOUND);
        }
    }


    // This annotation is used for mapping HTTP PUT requests onto specific handler methods
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegistration(@PathVariable Integer id, @RequestBody Registration registration) {
        try {
            return new ResponseEntity<>(registrationService.updateRegistration(id, registration), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Registration not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    // This annotation is used for mapping HTTP DELETE requests onto specific handler methods
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistration(@PathVariable Integer id) {
        try {
            registrationService.deleteRegistration(id);
            // Return a message in the response body with a 200 OK status
            return new ResponseEntity<>("Registration with id " + id + " was deleted.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Registration not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

}
