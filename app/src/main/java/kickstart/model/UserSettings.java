package kickstart.model;


import org.hibernate.validator.constraints.Email;
import org.salespointframework.useraccount.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Vincenz on 25.11.15.
 */
@Entity
public class UserSettings{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;

    private String newLastName;
	private String newFirstName;

    @Email(message = "Die eingegebene E-Mail-Adresse hat kein zugelassenes Format.")
    private String newEmail;

    @Pattern(regexp="(^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$|^$|)", message ="Das Passwort muss mindestens 8 Zeichen lang sein und muss mindestens eine Zahl, einen Klein- und einen Großbuchstaben beinhalten.")
    private String newPassword;

    private String oldPassword;
    private String confirmPW;
    private String newCity;

    @Pattern(regexp="(^$| |^(\\d{5})$)", message="Die Postleitzahl muss aus exakt 5 Ziffern bestehen.")
    private String newZip;

    private String newStreetName;

    @Pattern(regexp = "(^$|^(\\d+[a-zA-Z]*)$)", message="Geben Sie eine gültige Hausnummer ein.")
    private String newHouseNumber;
    private String newAddressAddition;
    private String newLanguage1;
    private String newLanguage2;
    private String newLanguage3;


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
        return this.newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }


    public String getNewFirstName() {
        return this.newFirstName;
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

    public String getNewHouseNumber() {
        return newHouseNumber;
    }

    public void setNewHouseNumber(String newHouseNumber) {
        this.newHouseNumber = newHouseNumber;
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
