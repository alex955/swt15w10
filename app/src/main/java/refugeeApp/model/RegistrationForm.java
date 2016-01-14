package refugeeApp.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.salespointframework.useraccount.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;

@Data
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
    
    @AssertTrue(message = "")
    private boolean isRecaptchaValidated(){
    	return this.getRecaptchaValidated().equals("true");
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
    
    /** checks whether recaptcha is validated to encounter deactivated javascript */
    private String recaptchaValidated;

}
