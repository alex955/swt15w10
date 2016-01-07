package refugeeApp.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.salespointframework.useraccount.Role;
import javax.validation.constraints.*;

/**
 * Created by Vincenz on 30.10.15.
 */
public class RegistrationForm {

    private long id;

    @NotNull(message = "Es wurde keine Rolle ausgewählt.")
    private Role role;

    @Size(min=2, max=30, message = "Der Nachname muss zwischen 2 und 30 Zeichen lang sein.")
    private String lastName;

    @Size(min=2, max=30, message = "Der Vorname muss zwischen 2 und 30 Zeichen lang sein.")
    private String firstName;

    @NotNull(message = "Es wurde keine Herkunft gewählt")
    private String country;

	@Size(min=6, max=30, message = "Der Benutzername muss zwischen 6 und 30 Zeichen lang sein.")
    private String username;
    
    @NotNull(message = "Sie müssen eine Email Adresse angeben")
    @Email(message = "Die eingegebene E-Mail-Adresse hat kein zugelassenes Format.")
    private String email;

    //Password must contain at least: 8 characters, 1 Number, 1 lowercase Letter, 1 upercase Letter, no whitespace
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message ="Das Passwort muss mindestens 8 Zeichen lang sein und muss mindestens eine Zahl, einen Klein- und einen Großbuchstaben beinhalten.")
    private String password;

    @NotEmpty
    private String confirmPW;

    //Check if password == confirmPW
    @AssertTrue(message = "Die Passwörter stimmen nicht überein.")
    private boolean isValid(){
        return this.password.equals(this.confirmPW);
    }

    @NotEmpty(message = "Geben Sie einen Stadtnamen ein")
    private String city;

    //Zipcode must be 5 characters long and contain only digits
    @Pattern(regexp="^\\d{5}$", message="Die Postleitzahl muss aus exakt 5 Ziffern bestehen.")
    private String zip;

    @Pattern(regexp = "^([A-ZÄÖÜ][a-zäöüß]+(([.] )|( )|([-])))+[1-9][0-9]{0,3}[a-z]?$", message = "Geben Sie einen Straßennamen ein. \n (Bsp.: Teststr. 1b, Baumweg 12, etc.)")
    private String streetName;

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