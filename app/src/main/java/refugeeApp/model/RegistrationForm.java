package refugeeApp.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.salespointframework.useraccount.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;


public class RegistrationForm {

    /** The id. */
    private long id;

    /** The role. */
    @NotNull(message = "")
    private Role role;

    /** The last name. */
    @Size(min=2, max=30, message = "")
    private String lastName;

    /** The first name. */
    @Size(min=2, max=30, message = "")
    private String firstName;

    /** The country. */
    @NotNull(message = "")
    private String country;

	/** The username. */
	@Size(min=6, max=30, message = "")
    private String username;
    
    /** The email. */
    @NotNull(message = "")
    @Email(message = "")
    private String email;

    /** The password. */
    //Password must contain at least: 8 characters, 1 Number, 1 lowercase Letter, 1 upercase Letter, no whitespace
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = "")
    private String password;

    /** The confirm pw. */
    @NotEmpty
    private String confirmPW;

    /**
     * Checks if is valid.
     *
     * @return true, if is valid
     */
    //Check if password == confirmPW
    @AssertTrue(message = "")
    private boolean isValid(){
        return this.password.equals(this.confirmPW);
    }

    /** The city. */
    @NotEmpty(message = "")
    private String city;

    /** The zip. */
    //Zipcode must be 5 characters long and contain only digits
    @Pattern(regexp="^\\d{5}$", message = "")
    private String zip;

    /** The street name. */
    @Pattern(regexp = "^([A-ZÄÖÜ][a-zäöüß]+(([.] )|( )|([-])))+[1-9][0-9]{0,3}[a-z]?$", message = "")
    private String streetName;

    /** The address addition. */
    private String addressAddition;

    /** The language1. */
    private String language1;

    /** The language2. */
    private String language2;

    /** The language3. */
    private String language3;
    
    /**
     * Gets the language2.
     *
     * @return the language2
     */
    public String getLanguage2() {
        return language2;
    }

    /**
     * Sets the language2.
     *
     * @param language2 the new language2
     */
    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    /**
     * Gets the language3.
     *
     * @return the language3
     */
    public String getLanguage3() {
        return language3;
    }

    /**
     * Sets the language3.
     *
     * @param language3 the new language3
     */
    public void setLanguage3(String language3) {
        this.language3 = language3;
    }

    /**
     * Gets the language1.
     *
     * @return the language1
     */
    public String getLanguage1() {
        return language1;
    }

    /**
     * Sets the language1.
     *
     * @param language1 the new language1
     */
    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the confirm pw.
     *
     * @return the confirm pw
     */
    public String getConfirmPW() {
        return confirmPW;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
    
    /**
     * Sets the confirm pw.
     *
     * @param confirmPW the new confirm pw
     */
    public void setConfirmPW(String confirmPW) {
        this.confirmPW = confirmPW;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the zip.
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the zip.
     *
     * @param zip the new zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the street name.
     *
     * @return the street name
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the street name.
     *
     * @param streetName the new street name
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the address addition.
     *
     * @return the address addition
     */
    public String getAddressAddition() {
        return addressAddition;
    }

    /**
     * Sets the address addition.
     *
     * @param addressAddition the new address addition
     */
    public void setAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
    }
}
