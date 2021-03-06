package pl.kurylek.jsf.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = Client.TABLE_NAME)
public class Client implements Serializable {

    public static final String TABLE_NAME = "CLIENT";
    private static final long serialVersionUID = 1L;
    private static final int FIRST_NAME_MIN_LENGTH = 3;
    private static final int LAST_NAME_MIN_LENGTH = 3;
    @Id
    @GeneratedValue
    private Long id;
    
    @NotBlank(message = "Please enter first name")
    @Length(min = FIRST_NAME_MIN_LENGTH, message = "Please enter at least " + FIRST_NAME_MIN_LENGTH + " charaters as first name")
    private String firstName;
    
    @NotBlank(message = "Please enter last name")
    @Length(min = LAST_NAME_MIN_LENGTH, message = "Please enter at least " + LAST_NAME_MIN_LENGTH + " charaters as last name")
    private String lastName;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true, cascade = {CascadeType.ALL})
    private Address address;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Title title;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}