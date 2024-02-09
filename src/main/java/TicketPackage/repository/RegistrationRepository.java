// This is the package name
package TicketPackage.repository;

// Importing necessary libraries and classes
import TicketPackage.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This is an interface for the Registration repository
// It extends JpaRepository which provides JPA related methods like save(), findOne(), findAll(), count(), delete() etc.
// The JpaRepository takes two parameters: the model class it will be handling and the type of the primary key of the model
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    // This method declaration is to find the first Registration by its ticket code
    // Spring Data JPA will automatically implement this method
    Optional<Registration> findFirstByTicketCode(String ticketCode);

    // This commented out method declaration is to delete a Registration by its ticket code
    // Spring Data JPA would automatically implement this method if it were uncommented
    // void deleteByTicketCode(String ticketCode);
}
