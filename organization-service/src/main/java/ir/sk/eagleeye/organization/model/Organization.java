package ir.sk.eagleeye.organization.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @Column(name = "organization_id", nullable = false)
    String id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    @NotBlank(message="Name is required")
    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "contact_name", nullable = false)
    String contactName;

    @Column(name = "contact_email", nullable = false)
    String contactEmail;

    @Column(name = "contact_phone", nullable = false)
    String contactPhone;
}
