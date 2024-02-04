package TicketPackage.service;

import TicketPackage.model.Registration;
import TicketPackage.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RegistrationService implements IRegistrationService {

    // Autowired repository for Registration
    private final RegistrationRepository registrationRepository;

    // Constructor-based dependency injection for RegistrationRepository
    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // Method to create a new Registration
    @Override
    public Registration create(Registration registration) {
        // Save the new Registration to the repository
        return registrationRepository.save(registration);
    }

    // Method to get a Registration by ticketCode
    @Override
    public Registration get(String ticketCode) {
        try{
            // Find the first Registration by ticketCode or throw an exception if not found
            return registrationRepository.findFirstByTicketCode(ticketCode)
                    .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));
        }
        catch (Exception ex){
            // Handle any other exceptions
            return null;
        }
    }

    // Method to update a Registration by id
    @Override
    public Registration updateRegistration(Integer id, Registration registration) {
        // Find the Registration by id and update its fields, or throw an exception if not found
        return registrationRepository.findById(id)
                .map(existingRegistration -> {
                    existingRegistration.setProductId(registration.getProductId());
                    existingRegistration.setTicketCode(registration.getTicketCode());
                    existingRegistration.setAttendeeName(registration.getAttendeeName());
                    return registrationRepository.save(existingRegistration);
                })
                .orElseThrow(() -> new NoSuchElementException("Registration not found with id " + id));
    }

    // Method to delete a Registration by id
    @Override
    public ResponseEntity<?> deleteRegistration(Integer id) {
        // Find the Registration by id and delete it, or throw an exception if not found
        return registrationRepository.findById(id)
                .map(registration -> {
                    registrationRepository.delete(registration);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new NoSuchElementException("Registration not found with id " + id));
    }
}
