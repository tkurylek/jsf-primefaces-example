package pl.kurylek.jsf.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = Address.TABLE_NAME)
public class Address implements Serializable {
    public static final String TABLE_NAME = "ADDRESS";
    private static final long serialVersionUID = 1L;
    private static final String POSTAL_CODE_REGEXP = "^\\d{2}-\\d{3}$"; // 41-907
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String street;
    @NotNull
    @Pattern(regexp = POSTAL_CODE_REGEXP, message = "Must be a valid postal code")
    private String postalCode;
    @NotNull
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
