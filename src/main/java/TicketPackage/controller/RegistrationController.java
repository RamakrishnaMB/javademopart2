package TicketPackage.controller;
import TicketPackage.model.Registration;
import TicketPackage.repository.RegistrationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @PostMapping
    public Registration create(@RequestBody @Valid Registration registration) {
        return registrationRepository.save(registration);
    }

    @GetMapping(path = "/{ticketCode}")
    public Registration get(@PathVariable("ticketCode") String ticketCode) {
        return registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));
    }

    @PutMapping("/{id}")
    public Registration updateRegistration(@PathVariable Integer id, @RequestBody Registration registration) {
        return registrationRepository.findById(id)
                .map(existingRegistration -> {
                    existingRegistration.setProductId(registration.getProductId());
                    existingRegistration.setTicketCode(registration.getTicketCode());
                    existingRegistration.setAttendeeName(registration.getAttendeeName());
                    return registrationRepository.save(existingRegistration);
                })
                .orElseThrow(() -> new NoSuchElementException("Registration not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegistration(@PathVariable Integer id) {
        return registrationRepository.findById(id)
                .map(registration -> {
                    registrationRepository.delete(registration);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new NoSuchElementException("Registration not found with id " + id));
    }

//    @GetMapping("/{id}")
//    public Registration getRegistration(@PathVariable Integer id) {
//        return registrationRepository.findById(id).orElse(null);
//    }

}