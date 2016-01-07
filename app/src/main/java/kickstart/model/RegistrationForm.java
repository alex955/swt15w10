package kickstart.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.salespointframework.useraccount.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;

/**
 * Created by Vincenz on 30.10.15.
 */
public class RegistrationForm {

    private long id;

    @NotNull(message = "")
    private Role role;

    @Size(min=2, max=30, message = "")
    private String lastName;

    @Size(min=2, max=30, message = "")
    private String firstName;

    @NotNull(message = "")
    private String country;

	@Size(min=6, max=30, message = "")
    private String username;
    
    @NotNull(message = "")
    @Email(message = "")
    private String email;

    //Password must contain at least: 8 characters, 1 Number, 1 lowercase Letter, 1 upercase Letter, no whitespace
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = "")
    private String password;

    @NotEmpty
    private String confirmPW;

    //Check if password == confirmPW
    @AssertTrue(message = "")
    private boolean isValid(){
        return this.password.equals(this.confirmPW);
    }

    @NotEmpty(message = "")
    private String city;

    //Zipcode must be 5 characters long and contain only digits
    @Pattern(regexp="^\\d{5}$", message = "")
    private String zip;

    @Size(min=5, max=50, message = "")
    private String streetName;

    //Must start with at least one digit. "1a", "12b", etc. possible
    @Pattern(regexp = "^\\d+[a-zA-Z]*$", message = "")
    private String houseNumber;

    private String addressAddition;


    private String language1;

    private String language2;

    private String language3;
    
    public String getLanguage2() {
        return language2;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public String getLanguage3() {
        return language3;
    }

    public void setLanguage3(String language3) {
        this.language3 = language3;
    }

    public String getLanguage1() {
        return language1;
    }

    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getConfirmPW() {
        return confirmPW;
    }

    public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
    public void setConfirmPW(String confirmPW) {
        this.confirmPW = confirmPW;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddressAddition() {
        return addressAddition;
    }

    public void setAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
    }
}
