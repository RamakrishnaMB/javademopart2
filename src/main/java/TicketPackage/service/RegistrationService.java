package TicketPackage.service;

import TicketPackage.model.Registration;
import TicketPackage.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

// The Service annotation is used in your service layer and simplifies the discovery of services for injection using Spring's dependency injection facilities.
@Service
public class RegistrationService implements IRegistrationService {

    // This is a reference to a RegistrationRepository, which is used to interact with the database.
    private final RegistrationRepository registrationRepository;

    // The Autowired annotation is used to automatically wire beans together.
    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // This method is used to create a new registration.
    @Override
    public Registration create(Registration registration) {
        return registrationRepository.save(registration);
    }

    // This method is used to get a registration by its ticket code.
    @Override
    public Registration get(String ticketCode) {
        return registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));
    }

    // This method is used to update an existing registration.
    @Override
    public Registration updateRegistration(Integer id, Registration registration) {
        return registrationRepository.findById(id)
                .map(existingRegistration -> {
                    existingRegistration.setProductId(registration.getProductId());
                    existingRegistration.setTicketCode(registration.getTicketCode());
                    existingRegistration.setAttendeeName(registration.getAttendeeName());
                    return registrationRepository.save(existingRegistration);
                })
                .orElseThrow(() -> new NoSuchElementException("Registration not found with id " + id));
    }

    // This method is used to delete a registration.
    @Override
    public ResponseEntity<?> deleteRegistration(Integer id) {
        return registrationRepository.findById(id)
                .map(registration -> {
                    registrationRepository.delete(registration);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseThrow(() -> new NoSuchElementException("Registration not found with id " + id));
    }
}
