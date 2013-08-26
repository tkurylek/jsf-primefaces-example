package pl.kurylek.jsf.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = Address.TABLE_NAME)
public class Address implements Serializable {

    public static final String TABLE_NAME = "ADDRESS";
    private static final long serialVersionUID = 1L;
    private static final String POSTAL_CODE_REGEXP = "^\\d{2}-\\d{3}$"; // 41-907
    private static final int CITY_MIN_LENGTH = 2;
    @Id
    @GeneratedValue
    private Long id;
    
    @NotBlank(message = "Please enter street name")
    private String street;
    
    @Pattern(regexp = POSTAL_CODE_REGEXP, message = "Please enter a valid postal code, i.e. 41-907")
    private String postalCode;
    
    @NotBlank(message = "Please enter city name")
    @Length(min = CITY_MIN_LENGTH, message = "Please enter at least " + CITY_MIN_LENGTH + " charaters as city name")
    private String city;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}