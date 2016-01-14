package refugeeApp.model;


import lombok.Data;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;


@Data
public class UserSettingsForm {

    /**
     * The id.
     */
    private long id;

    /**
     * The user id.
     */
    private long userId;

    /**
     * The new last name.
     */
    private String newLastName;

    /**
     * The new first name.
     */
    private String newFirstName;

    /**
     * The new email.
     */
    @Email(message = "")
    private String newEmail;

    /**
     * The new password.
     */
    @Pattern(regexp = "(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$|^$|)", message = "")
    private String newPassword;

    /**
     * The old password.
     */
    private String oldPassword;

    /**
     * The confirm pw.
     */
    private String confirmPW;

    /**
     * Checks if is valid.
     *
     * @return true, if is valid
     */
    //newPW == confirmPW?
    @AssertTrue(message = "")
    private boolean isValid() {
        return this.newPassword.equals(this.confirmPW);
    }

    /**
     * The new city.
     */
    private String newCity;

    /**
     * The new zip.
     */
    @Pattern(regexp = "(^$| |^(\\d{5})$)", message = "")
    private String newZip;

    /**
     * The new street name.
     */
    @Pattern(regexp = "^([A-ZÄÖÜ][a-zäöüß]+(([.] )|( )|([-])))+[1-9][0-9]{0,3}[a-z]?$", message = "")
    private String newStreetName;

    /**
     * The new address addition.
     */
    private String newAddressAddition;

    /**
     * The new language1.
     */
    private String newLanguage1;

    /**
     * The new language2.
     */
    private String newLanguage2;

    /**
     * The new language3.
     */
    private String newLanguage3;


}
