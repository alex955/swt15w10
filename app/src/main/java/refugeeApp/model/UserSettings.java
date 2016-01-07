package refugeeApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Vincenz on 14.12.15.
 */
@Entity
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String newLastName;
    private String newFirstName;
    private String newEmail;
    private String newPassword;
    private String oldPassword;
    private String confirmPW;
    private String newCity;
    private String newZip;
    private String newStreetName;
    private String newAddressAddition;
    private String newLanguage1;
    private String newLanguage2;
    private String newLanguage3;

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

    public UserSettings() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPW() {
        return confirmPW;
    }

    public void setConfirmPW(String confirmPW) {
        this.confirmPW = confirmPW;
    }

    public String getNewCity() {
        return newCity;
    }

    public void setNewCity(String newCity) {
        this.newCity = newCity;
    }

    public String getNewZip() {
        return newZip;
    }

    public void setNewZip(String newZip) {
        this.newZip = newZip;
    }

    public String getNewStreetName() {
        return newStreetName;
    }

    public void setNewStreetName(String newStreetName) {
        this.newStreetName = newStreetName;
    }

    public String getNewAddressAddition() {
        return newAddressAddition;
    }

    public void setNewAddressAddition(String newAddressAddition) {
        this.newAddressAddition = newAddressAddition;
    }

    public String getNewLanguage1() {
        return newLanguage1;
    }

    public void setNewLanguage1(String newLanguage1) {
        this.newLanguage1 = newLanguage1;
    }

    public String getNewLanguage2() {
        return newLanguage2;
    }

    public void setNewLanguage2(String newLanguage2) {
        this.newLanguage2 = newLanguage2;
    }

    public String getNewLanguage3() {
        return newLanguage3;
    }

    public void setNewLanguage3(String newLanguage3) {
        this.newLanguage3 = newLanguage3;
    }
}
