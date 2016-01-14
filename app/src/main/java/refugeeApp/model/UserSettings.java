package refugeeApp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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

}
