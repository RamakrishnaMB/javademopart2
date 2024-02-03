package TicketPackage.controller;

import TicketPackage.model.Registration;
import TicketPackage.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<Registration> create(@RequestBody @Valid Registration registration) {
        Registration createdRegistration = registrationService.create(registration);
        return new ResponseEntity<>(createdRegistration, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{ticketCode}")
    public ResponseEntity<?> get(@PathVariable("ticketCode") String ticketCode) {
        try {
            return new ResponseEntity<>(registrationService.get(ticketCode), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Registration with ticket code " + ticketCode + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegistration(@PathVariable Integer id, @RequestBody Registration registration) {
        try {
            return new ResponseEntity<>(registrationService.updateRegistration(id, registration), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Registration not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegistration(@PathVariable Integer id) {
        try {
            registrationService.deleteRegistration(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Registration not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/{id}")
//    public Registration getRegistration(@PathVariable Integer id) {
//        return registrationRepository.findById(id).orElse(null);
//    }

}
