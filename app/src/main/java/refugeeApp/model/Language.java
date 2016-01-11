package refugeeApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Language {

    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    /** The browser language. */
    private String browserLanguage;

    /** The role error. */
    //Registration + Usersettings
    private String roleError;
    
    /** The name error. */
    private String nameError;
    
    /** The country error. */
    private String countryError;
    
    /** The username error. */
    private String usernameError;
    
    /** The email error. */
    private String emailError;
    
    /** The email used. */
    private String emailUsed;
    
    /** The password error. */
    private String passwordError;
    
    /** The password confirm error. */
    private String passwordConfirmError;
    
    /** The city error. */
    private String cityError;
    
    /** The zip error. */
    private String zipError;
    
    /** The street error. */
    private String streetError;
    
    /** The number error. */
    private String numberError;
    
    /** The old pw error. */
    private String oldPwError;
    
    /** The email confirm. */
    private String emailConfirm;
    
    /** The username used error. */
    private String usernameUsedError;
    
    /** The registration confirm. */
    private String registrationConfirm;

    /** The title error. */
    //Article
    private String titleError;
    
    /** The kind error. */
    private String kindError;
    
    /** The date error. */
    private String dateError;

    /** The registration email. */
    //EMails
    private String registrationEmail;
    
    /** The registration email topic. */
    private String registrationEmailTopic;
    
    /** The delete email. */
    private String deleteEmail;
    
    /** The delete email topic. */
    private String deleteEmailTopic;
    
    /** The change email. */
    private String changeEmail;
    
    /** The change email topic. */
    private String changeEmailTopic;

    private String resetPw;
    private String resetPwTopic;

    /** The delete user popup. */
    private String deleteUserPopup;
    
    /** The delete chat. */
    //chat
    private String deleteChat;

    //reset PW
    private String noAccountError;
    private String resetConfirm;


    /**
     * Instantiates a new language.
     */
    public Language() {}

    /**
     * Gets the browser language.
     *
     * @return the browser language
     */
    public String getBrowserLanguage() {
        return browserLanguage;
    }

    /**
     * Sets the browser language.
     *
     * @param browserLanguage the new browser language
     */
    public void setBrowserLanguage(String browserLanguage) {
        this.browserLanguage = browserLanguage;
    }

    /**
     * Gets the role error.
     *
     * @return the role error
     */
    public String getRoleError() {
        return roleError;
    }

    /**
     * Sets the role error.
     *
     * @param roleError the new role error
     */
    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    /**
     * Gets the name error.
     *
     * @return the name error
     */
    public String getNameError() {
        return nameError;
    }

    /**
     * Sets the name error.
     *
     * @param nameError the new name error
     */
    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    /**
     * Gets the country error.
     *
     * @return the country error
     */
    public String getCountryError() {
        return countryError;
    }

    /**
     * Sets the country error.
     *
     * @param countryError the new country error
     */
    public void setCountryError(String countryError) {
        this.countryError = countryError;
    }

    /**
     * Gets the username error.
     *
     * @return the username error
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Sets the username error.
     *
     * @param usernameError the new username error
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Gets the email error.
     *
     * @return the email error
     */
    public String getEmailError() {
        return emailError;
    }

    /**
     * Sets the email error.
     *
     * @param emailError the new email error
     */
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    /**
     * Gets the email used.
     *
     * @return the email used
     */
    public String getEmailUsed() {
        return emailUsed;
    }

    /**
     * Sets the email used.
     *
     * @param emailUsed the new email used
     */
    public void setEmailUsed(String emailUsed) {
        this.emailUsed = emailUsed;
    }

    /**
     * Gets the password error.
     *
     * @return the password error
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Sets the password error.
     *
     * @param passwordError the new password error
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Gets the password confirm error.
     *
     * @return the password confirm error
     */
    public String getPasswordConfirmError() {
        return passwordConfirmError;
    }

    /**
     * Sets the password confirm error.
     *
     * @param passwordConfirmError the new password confirm error
     */
    public void setPasswordConfirmError(String passwordConfirmError) {
        this.passwordConfirmError = passwordConfirmError;
    }

    /**
     * Gets the city error.
     *
     * @return the city error
     */
    public String getCityError() {
        return cityError;
    }

    /**
     * Sets the city error.
     *
     * @param cityError the new city error
     */
    public void setCityError(String cityError) {
        this.cityError = cityError;
    }

    /**
     * Gets the zip error.
     *
     * @return the zip error
     */
    public String getZipError() {
        return zipError;
    }

    /**
     * Sets the zip error.
     *
     * @param zipError the new zip error
     */
    public void setZipError(String zipError) {
        this.zipError = zipError;
    }

    /**
     * Gets the street error.
     *
     * @return the street error
     */
    public String getStreetError() {
        return streetError;
    }

    /**
     * Sets the street error.
     *
     * @param streetError the new street error
     */
    public void setStreetError(String streetError) {
        this.streetError = streetError;
    }

    /**
     * Gets the number error.
     *
     * @return the number error
     */
    public String getNumberError() {
        return numberError;
    }

    /**
     * Sets the number error.
     *
     * @param numberError the new number error
     */
    public void setNumberError(String numberError) {
        this.numberError = numberError;
    }

    /**
     * Gets the title error.
     *
     * @return the title error
     */
    public String getTitleError() {
        return titleError;
    }

    /**
     * Sets the title error.
     *
     * @param titleError the new title error
     */
    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    /**
     * Gets the kind error.
     *
     * @return the kind error
     */
    public String getKindError() {
        return kindError;
    }

    /**
     * Sets the kind error.
     *
     * @param kindError the new kind error
     */
    public void setKindError(String kindError) {
        this.kindError = kindError;
    }

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
     * Gets the old pw error.
     *
     * @return the old pw error
     */
    public String getOldPwError() {
        return oldPwError;
    }

    /**
     * Sets the old pw error.
     *
     * @param oldPwError the new old pw error
     */
    public void setOldPwError(String oldPwError) {
        this.oldPwError = oldPwError;
    }

    /**
     * Gets the email confirm.
     *
     * @return the email confirm
     */
    public String getEmailConfirm() {
        return emailConfirm;
    }

    /**
     * Sets the email confirm.
     *
     * @param emailConfirm the new email confirm
     */
    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

    /**
     * Gets the username used error.
     *
     * @return the username used error
     */
    public String getUsernameUsedError() {
        return usernameUsedError;
    }

    /**
     * Sets the username used error.
     *
     * @param usernameUsedError the new username used error
     */
    public void setUsernameUsedError(String usernameUsedError) {
        this.usernameUsedError = usernameUsedError;
    }

    /**
     * Gets the registration confirm.
     *
     * @return the registration confirm
     */
    public String getRegistrationConfirm() {
        return registrationConfirm;
    }

    /**
     * Sets the registration confirm.
     *
     * @param registrationConfirm the new registration confirm
     */
    public void setRegistrationConfirm(String registrationConfirm) {
        this.registrationConfirm = registrationConfirm;
    }

    /**
     * Gets the registration email.
     *
     * @return the registration email
     */
    public String getRegistrationEmail() {
        return registrationEmail;
    }

    /**
     * Sets the registration email.
     *
     * @param registrationEmail the new registration email
     */
    public void setRegistrationEmail(String registrationEmail) {
        this.registrationEmail = registrationEmail;
    }

    /**
     * Gets the delete email.
     *
     * @return the delete email
     */
    public String getDeleteEmail() {
        return deleteEmail;
    }

    /**
     * Sets the delete email.
     *
     * @param deleteEmail the new delete email
     */
    public void setDeleteEmail(String deleteEmail) {
        this.deleteEmail = deleteEmail;
    }

    /**
     * Gets the change email.
     *
     * @return the change email
     */
    public String getChangeEmail() {
        return changeEmail;
    }

    /**
     * Sets the change email.
     *
     * @param changeEmail the new change email
     */
    public void setChangeEmail(String changeEmail) {
        this.changeEmail = changeEmail;
    }

    /**
     * Gets the registration email topic.
     *
     * @return the registration email topic
     */
    public String getRegistrationEmailTopic() {
        return registrationEmailTopic;
    }

    /**
     * Sets the registration email topic.
     *
     * @param registrationEmailTopic the new registration email topic
     */
    public void setRegistrationEmailTopic(String registrationEmailTopic) {
        this.registrationEmailTopic = registrationEmailTopic;
    }

    /**
     * Gets the delete email topic.
     *
     * @return the delete email topic
     */
    public String getDeleteEmailTopic() {
        return deleteEmailTopic;
    }

    /**
     * Sets the delete email topic.
     *
     * @param deleteEmailTopic the new delete email topic
     */
    public void setDeleteEmailTopic(String deleteEmailTopic) {
        this.deleteEmailTopic = deleteEmailTopic;
    }

    /**
     * Gets the change email topic.
     *
     * @return the change email topic
     */
    public String getChangeEmailTopic() {
        return changeEmailTopic;
    }

    /**
     * Sets the change email topic.
     *
     * @param changeEmailTopic the new change email topic
     */
    public void setChangeEmailTopic(String changeEmailTopic) {
        this.changeEmailTopic = changeEmailTopic;
    }




    public String getResetPw() {
        return resetPw;
    }

    public void setResetPw(String resetPw) {
        this.resetPw = resetPw;
    }

    public String getResetPwTopic() {
        return resetPwTopic;
    }

    public void setResetPwTopic(String resetPwTopic) {
        this.resetPwTopic = resetPwTopic;
    }
    /**
     * Gets the delete user popup.
     *
     * @return the delete user popup
     */
    public String getDeleteUserPopup() {

		return deleteUserPopup;
	}

	/**
	 * Sets the delete user popup.
	 *
	 * @param deleteUserPopup the new delete user popup
	 */
	public void setDeleteUserPopup(String deleteUserPopup) {
		this.deleteUserPopup = deleteUserPopup;
	}

	/**
	 * Gets the date error.
	 *
	 * @return the date error
	 */
	public String getDateError() {
		return dateError;
	}

	/**
	 * Sets the date error.
	 *
	 * @param dateError the new date error
	 */
	public void setDateError(String dateError) {
		this.dateError = dateError;
	}

	/**
	 * Gets the delete chat.
	 *
	 * @return the delete chat
	 */
	public String getDeleteChat() {
		return deleteChat;
	}

	/**
	 * Sets the delete chat.
	 *
	 * @param deleteChat the new delete chat
	 */
	public void setDeleteChat(String deleteChat) {
		this.deleteChat = deleteChat;
	}

    public String getNoAccountError() {
        return noAccountError;
    }

    public void setNoAccountError(String noAccountError) {
        this.noAccountError = noAccountError;
    }

    public String getResetConfirm() {
        return resetConfirm;
    }

    public void setResetConfirm(String resetConfirm) {
        this.resetConfirm = resetConfirm;
    }
}
