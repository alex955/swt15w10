package refugeeApp.model;


import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;



public class UserSettingsForm {

    /** The id. */
    private long id;
    
    /** The user id. */
    private long userId;
    
    /** The new last name. */
    private String newLastName;
    
    /** The new first name. */
    private String newFirstName;

    /** The new email. */
    @Email(message = "")
    private String newEmail;

    /** The new password. */
    @Pattern(regexp="(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$|^$|)", message = "")
    private String newPassword;
    
    /** The old password. */
    private String oldPassword;
    
    /** The confirm pw. */
    private String confirmPW;

    /**
     * Checks if is valid.
     *
     * @return true, if is valid
     */
    //newPW == confirmPW?
    @AssertTrue(message = "")
    private boolean isValid(){
        return this.newPassword.equals(this.confirmPW);
    }

    /** The new city. */
    private String newCity;

    /** The new zip. */
    @Pattern(regexp="(^$| |^(\\d{5})$)", message = "")
    private String newZip;

    /** The new street name. */
    @Pattern(regexp = "^([A-ZÄÖÜ][a-zäöüß]+(([.] )|( )|([-])))+[1-9][0-9]{0,3}[a-z]?$", message = "")
    private String newStreetName;

    /** The new address addition. */
    private String newAddressAddition;
    
    /** The new language1. */
    private String newLanguage1;
    
    /** The new language2. */
    private String newLanguage2;
    
    /** The new language3. */
    private String newLanguage3;


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
     * Gets the user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets the new last name.
     *
     * @return the new last name
     */
    public String getNewLastName() {
        return this.newLastName;
    }

    /**
     * Sets the new last name.
     *
     * @param newLastName the new new last name
     */
    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    /**
     * Gets the new first name.
     *
     * @return the new first name
     */
    public String getNewFirstName() {
        return this.newFirstName;
    }

    /**
     * Sets the new first name.
     *
     * @param newFirstName the new new first name
     */
    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    /**
     * Gets the new email.
     *
     * @return the new email
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * Sets the new email.
     *
     * @param newEmail the new new email
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    /**
     * Gets the new password.
     *
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the new password.
     *
     * @param newPassword the new new password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Gets the old password.
     *
     * @return the old password
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Sets the old password.
     *
     * @param oldPassword the new old password
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
     * Sets the confirm pw.
     *
     * @param confirmPW the new confirm pw
     */
    public void setConfirmPW(String confirmPW) {
        this.confirmPW = confirmPW;
    }

    /**
     * Gets the new city.
     *
     * @return the new city
     */
    public String getNewCity() {
        return newCity;
    }

    /**
     * Sets the new city.
     *
     * @param newCity the new new city
     */
    public void setNewCity(String newCity) {
        this.newCity = newCity;
    }

    /**
     * Gets the new zip.
     *
     * @return the new zip
     */
    public String getNewZip() {
        return newZip;
    }

    /**
     * Sets the new zip.
     *
     * @param newZip the new new zip
     */
    public void setNewZip(String newZip) {
        this.newZip = newZip;
    }

    /**
     * Gets the new street name.
     *
     * @return the new street name
     */
    public String getNewStreetName() {
        return newStreetName;
    }

    /**
     * Sets the new street name.
     *
     * @param newStreetName the new new street name
     */
    public void setNewStreetName(String newStreetName) {
        this.newStreetName = newStreetName;
    }

    /**
     * Gets the new address addition.
     *
     * @return the new address addition
     */
    public String getNewAddressAddition() {
        return newAddressAddition;
    }

    /**
     * Sets the new address addition.
     *
     * @param newAddressAddition the new new address addition
     */
    public void setNewAddressAddition(String newAddressAddition) {
        this.newAddressAddition = newAddressAddition;
    }

    /**
     * Gets the new language1.
     *
     * @return the new language1
     */
    public String getNewLanguage1() {
        return newLanguage1;
    }

    /**
     * Sets the new language1.
     *
     * @param newLanguage1 the new new language1
     */
    public void setNewLanguage1(String newLanguage1) {
        this.newLanguage1 = newLanguage1;
    }

    /**
     * Gets the new language2.
     *
     * @return the new language2
     */
    public String getNewLanguage2() {
        return newLanguage2;
    }

    /**
     * Sets the new language2.
     *
     * @param newLanguage2 the new new language2
     */
    public void setNewLanguage2(String newLanguage2) {
        this.newLanguage2 = newLanguage2;
    }

    /**
     * Gets the new language3.
     *
     * @return the new language3
     */
    public String getNewLanguage3() {
        return newLanguage3;
    }

    /**
     * Sets the new language3.
     *
     * @param newLanguage3 the new new language3
     */
    public void setNewLanguage3(String newLanguage3) {
        this.newLanguage3 = newLanguage3;
    }
}
