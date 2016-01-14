package refugeeApp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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

}
