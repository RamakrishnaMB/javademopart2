package TicketPackage.service;

import TicketPackage.model.Registration;
import org.springframework.http.ResponseEntity;

public interface IRegistrationService {
    Registration create(Registration registration);

    Registration get(String ticketCode);

    Registration updateRegistration(Integer id, Registration registration);

    ResponseEntity<?> deleteRegistration(Integer id);
}
