package TicketPackage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// Entity annotation indicates that this class is a JPA entity
@Entity
// Specifies the primary table for the annotated entity
@Table(name = "registrations")
public class Registration {

    // Specifies the primary key of an entity
    @Id
    // Provides for the specification of generation strategies for the values of primary keys
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Asserts that a property must not be null
    @NotNull(message = "Product id is required")
    // Specifies the mapped column for a persistent property or field
    @Column(name = "product_id", nullable = false)
    public Integer productId;

    // Specifies the mapped column for a persistent property or field
    @Column(name = "ticket_code")
    private String ticketCode;

    // Asserts that a property must not be null nor empty
    @NotBlank(message = "Attendee name is required")
    // Specifies the mapped column for a persistent property or field
    @Column(name = "attendee_name", nullable = false)
    public String attendeeName;

    // Getter for id
    public Integer getId() {
        return id;
    }

    // Setter for id
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter for productId
    public Integer getProductId() {
        return productId;
    }

    // Setter for productId
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    // Getter for ticketCode
    public String getTicketCode() {
        return ticketCode;
    }

    // Setter for ticketCode
    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    // Getter for attendeeName
    public String getAttendeeName() {
        return attendeeName;
    }

    // Setter for attendeeName
    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }
}
