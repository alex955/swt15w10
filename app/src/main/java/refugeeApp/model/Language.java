package refugeeApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Vincenz on 04.01.16.
 */
@Entity
public class Language {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    private String browserLanguage;

    //Registration + Usersettings
    private String roleError;
    private String nameError;
    private String countryError;
    private String usernameError;
    private String emailError;
    private String passwordError;
    private String passwordConfirmError;
    private String cityError;
    private String zipError;
    private String streetError;
    private String numberError;
    private String oldPwError;
    private String emailConfirm;
    private String usernameUsedError;
    private String registrationConfirm;

    //Article
    private String titleError;
    private String kindError;

    //EMails
    private String registrationEmail;
    private String registrationEmailTopic;
    private String deleteEmail;
    private String deleteEmailTopic;
    private String changeEmail;
    private String changeEmailTopic;


    public Language() {}

    public String getBrowserLanguage() {
        return browserLanguage;
    }

    public void setBrowserLanguage(String browserLanguage) {
        this.browserLanguage = browserLanguage;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getCountryError() {
        return countryError;
    }

    public void setCountryError(String countryError) {
        this.countryError = countryError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPasswordConfirmError() {
        return passwordConfirmError;
    }

    public void setPasswordConfirmError(String passwordConfirmError) {
        this.passwordConfirmError = passwordConfirmError;
    }

    public String getCityError() {
        return cityError;
    }

    public void setCityError(String cityError) {
        this.cityError = cityError;
    }

    public String getZipError() {
        return zipError;
    }

    public void setZipError(String zipError) {
        this.zipError = zipError;
    }

    public String getStreetError() {
        return streetError;
    }

    public void setStreetError(String streetError) {
        this.streetError = streetError;
    }

    public String getNumberError() {
        return numberError;
    }

    public void setNumberError(String numberError) {
        this.numberError = numberError;
    }

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getKindError() {
        return kindError;
    }

    public void setKindError(String kindError) {
        this.kindError = kindError;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOldPwError() {
        return oldPwError;
    }

    public void setOldPwError(String oldPwError) {
        this.oldPwError = oldPwError;
    }

    public String getEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

    public String getUsernameUsedError() {
        return usernameUsedError;
    }

    public void setUsernameUsedError(String usernameUsedError) {
        this.usernameUsedError = usernameUsedError;
    }

    public String getRegistrationConfirm() {
        return registrationConfirm;
    }

    public void setRegistrationConfirm(String registrationConfirm) {
        this.registrationConfirm = registrationConfirm;
    }

    public String getRegistrationEmail() {
        return registrationEmail;
    }

    public void setRegistrationEmail(String registrationEmail) {
        this.registrationEmail = registrationEmail;
    }

    public String getDeleteEmail() {
        return deleteEmail;
    }

    public void setDeleteEmail(String deleteEmail) {
        this.deleteEmail = deleteEmail;
    }

    public String getChangeEmail() {
        return changeEmail;
    }

    public void setChangeEmail(String changeEmail) {
        this.changeEmail = changeEmail;
    }

    public String getRegistrationEmailTopic() {
        return registrationEmailTopic;
    }

    public void setRegistrationEmailTopic(String registrationEmailTopic) {
        this.registrationEmailTopic = registrationEmailTopic;
    }

    public String getDeleteEmailTopic() {
        return deleteEmailTopic;
    }

    public void setDeleteEmailTopic(String deleteEmailTopic) {
        this.deleteEmailTopic = deleteEmailTopic;
    }

    public String getChangeEmailTopic() {
        return changeEmailTopic;
    }

    public void setChangeEmailTopic(String changeEmailTopic) {
        this.changeEmailTopic = changeEmailTopic;
    }
}
