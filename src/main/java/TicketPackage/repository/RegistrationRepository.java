package TicketPackage.repository;

import TicketPackage.model.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
    Optional<Registration> findByTicketCode(String ticketCode);

    void deleteByTicketCode(String ticketCode);

}
