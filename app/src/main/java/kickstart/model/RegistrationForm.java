package kickstart.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.salespointframework.useraccount.Role;
import javax.validation.constraints.*;

/**
 * Created by Vincenz on 30.10.15.
 */
public class RegistrationForm {

    //@Autowired LanguageRepository languageRepository;

    //Language language = languageRepository.findByBrowserLanguage("SPRACHE HIER EINFÜGEN");

    private long id;

    @NotNull(message = /* language.getRoleError*/ "Es wurde keine Rolle ausgewählt.")
    private Role role;

    @Size(min=2, max=30, message = /* language.getNameError*/ "Der Nachname muss zwischen 2 und 30 Zeichen lang sein.")
    private String lastName;

    @Size(min=2, max=30, message = /* language.getNameError*/ "Der Vorname muss zwischen 2 und 30 Zeichen lang sein.")
    private String firstName;

    @NotNull(message = /* language.getCountryError*/ "Es wurde keine Herkunft gewählt")
    private String country;

	@Size(min=6, max=30, message = /* language.getUsernameError*/ "Der Benutzername muss zwischen 6 und 30 Zeichen lang sein.")
    private String username;
    
    @NotNull(message = /* language.getEmailError*/ "Sie müssen eine Email Adresse angeben")
    @Email(message = /* language.getEmailError*/ "Die eingegebene E-Mail-Adresse hat kein zugelassenes Format.")
    private String email;

    //Password must contain at least: 8 characters, 1 Number, 1 lowercase Letter, 1 upercase Letter, no whitespace
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = /* language.getPasswordError*/ "Das Passwort muss mindestens 8 Zeichen lang sein und muss mindestens eine Zahl, einen Klein- und einen Großbuchstaben beinhalten.")
    private String password;

    @NotEmpty
    private String confirmPW;

    //Check if password == confirmPW
    @AssertTrue(message = /* language.getPasswordConfirmError*/ "Die Passwörter stimmen nicht überein.")
    private boolean isValid(){
        return this.password.equals(this.confirmPW);
    }

    @NotEmpty(message = /* language.getCityError*/ "Geben Sie einen Stadtnamen ein")
    private String city;

    //Zipcode must be 5 characters long and contain only digits
    @Pattern(regexp="^\\d{5}$", message = /* language.getZipError*/ "Die Postleitzahl muss aus exakt 5 Ziffern bestehen.")
    private String zip;

    @Size(min=5, max=50, message = /* language.getStrretError*/ "Geben Sie einen Straßennamen ein.")
    private String streetName;

    //Must start with at least one digit. "1a", "12b", etc. possible
    @Pattern(regexp = "^\\d+[a-zA-Z]*$", message = "Geben Sie eine gültige Hausnummer ein.")
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
