package refugeeApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UserSettings {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /** The user id. */
    private long userId;
    
    /** The new last name. */
    private String newLastName;
    
    /** The new first name. */
    private String newFirstName;
    
    /** The new email. */
    private String newEmail;
    
    /** The new password. */
    private String newPassword;
    
    /** The old password. */
    private String oldPassword;
    
    /** The confirm pw. */
    private String confirmPW;
    
    /** The new city. */
    private String newCity;
    
    /** The new zip. */
    private String newZip;
    
    /** The new street name. */
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
     * Instantiates a new user settings.
     *
     * @param id the id
     * @param userId the user id
     * @param newLastName the new last name
     * @param newFirstName the new first name
     * @param newEmail the new email
     * @param newPassword the new password
     * @param oldPassword the old password
     * @param confirmPW the confirm pw
     * @param newCity the new city
     * @param newZip the new zip
     * @param newStreetName the new street name
     * @param newAddressAddition the new address addition
     * @param newLanguage1 the new language1
     * @param newLanguage2 the new language2
     * @param newLanguage3 the new language3
     */
    public UserSettings(long id, long userId, String newLastName, String newFirstName, String newEmail, String newPassword, String oldPassword, String confirmPW, String newCity, String newZip, String newStreetName, String newAddressAddition, String newLanguage1, String newLanguage2, String newLanguage3) {

        this.userId = userId;
        this.newLastName = newLastName;
        this.newFirstName = newFirstName;
        this.newEmail = newEmail;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.confirmPW = confirmPW;
        this.newCity = newCity;
        this.newZip = newZip;
        this.newStreetName = newStreetName;
        this.newAddressAddition = newAddressAddition;
        this.newLanguage1 = newLanguage1;
        this.newLanguage2 = newLanguage2;
        this.newLanguage3 = newLanguage3;
    }

    /**
     * Instantiates a new user settings.
     *
     * @param id the id
     * @param userId the user id
     * @param newEmail the new email
     * @param newPassword the new password
     * @param confirmPW the confirm pw
     * @param oldPassword the old password
     * @param newCity the new city
     * @param newZip the new zip
     * @param newAddressAddition the new address addition
     * @param newStreetName the new street name
     * @param newLanguage1 the new language1
     * @param newLanguage2 the new language2
     * @param newLanguage3 the new language3
     */
    public UserSettings(long id, long userId, String newEmail, String newPassword, String confirmPW, String oldPassword, String newCity, String newZip,  String newAddressAddition, String newStreetName, String newLanguage1, String newLanguage2, String newLanguage3) {

        this.userId = userId;
        this.newEmail = newEmail;
        this.newPassword = newPassword;
        this.confirmPW = confirmPW;
        this.oldPassword = oldPassword;
        this.newCity = newCity;
        this.newZip = newZip;
        this.newAddressAddition = newAddressAddition;
        this.newStreetName = newStreetName;
        this.newLanguage1 = newLanguage1;
        this.newLanguage2 = newLanguage2;
        this.newLanguage3 = newLanguage3;
    }

    /**
     * Instantiates a new user settings.
     */
    public UserSettings() {}

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
        return newLastName;
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
        return newFirstName;
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
