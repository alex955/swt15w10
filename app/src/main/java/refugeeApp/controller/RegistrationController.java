package refugeeApp.controller;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import refugeeApp.model.*;
import refugeeApp.utilities.CategoryMethods;
import refugeeApp.utilities.email.EmailUsage;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.Locale;


/**
 * The Class RegistrationController.
 */
@Controller
public class RegistrationController {

    /** The user repository. */
    private final UserRepository userRepository;
    /** The language repository. */
    private final LanguageRepository languageRepository;
    /**
     * The user account manager.
     */
    private final UserAccountManager userAccountManager;
    /** The processed categories. */
    protected LinkedList<CategoryFirstTierObject> processedCategories;
    /**
     * The validator repository.
     */
    private ValidatorRepository validatorRepository;

    /**
     * autowired constructor.
     *
     * @param userAccountManager the user account manager
     * @param userRepository the user repository
     * @param categoryMethods the category methods
     * @param categories the categories
     * @param validatorRepository the validator repository
     * @param languageRepository the language repository
     */
    @Autowired
    public RegistrationController(UserAccountManager userAccountManager, UserRepository userRepository, CategoryMethods categoryMethods, CategoryRepo categories, ValidatorRepository validatorRepository, LanguageRepository languageRepository){
        this.userAccountManager = userAccountManager;
        this.userRepository = userRepository;
        this.validatorRepository = validatorRepository;
        this.languageRepository = languageRepository;
    }

    /**
     * registration form.
     *
     * @param registrationForm form object for new registration
     * @param model MVC model
     * @return template for registration
     */
    @RequestMapping(value ="/registration")
    public String firstView(@ModelAttribute("RegistrationForm") RegistrationForm registrationForm, Model model) {
    	model.addAttribute("current_category",new Category("AlleKategorien",1));
 		model.addAttribute("current_ort",new Location(""));
    	return ("registration");
    }

    /**
     * processing of data passed by new registration.
     *
     * @param registrationForm registration form objet
     * @param result validation result
     * @param modelMap MVC model map
     * @param model MVC model
     * @return registration template
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String newRegistration(@ModelAttribute("RegistrationForm") @Valid RegistrationForm registrationForm, BindingResult result, ModelMap modelMap, Model model) throws MessagingException {

        boolean errors = false;
        Locale locale = LocaleContextHolder.getLocale();
        String browserLanguage = locale.toString().substring(0, 2);

        if(languageRepository.findByBrowserLanguage(browserLanguage) == null){
            browserLanguage = "en";
        }

        Language language = languageRepository.findByBrowserLanguage(browserLanguage);

        if(result.hasErrors())
            errors = true;

        if(userRepository.findByUsernameLowercase(registrationForm.getUsername().toLowerCase()) != null)
            errors = true;

        if(userRepository.findByEmail(registrationForm.getEmail()) != null)
            errors = true;

        if(errors) {

            if(result.hasFieldErrors("role")){
                final String roleError = language.getRoleError();
                modelMap.addAttribute("roleError", roleError);
            }

            if(result.hasFieldErrors("lastName") || result.hasFieldErrors("firstName")){
                final String nameError = language.getNameError();
                modelMap.addAttribute("nameError", nameError);
            }

            if(result.hasFieldErrors("country")){
                final String countryError = language.getCountryError();
                modelMap.addAttribute("countryError", countryError);
            }

            if(result.hasFieldErrors("username")){
                final String usernameError = language.getUsernameError();
                modelMap.addAttribute("usernameError", usernameError);
            }

            if(userRepository.findByUsernameLowercase(registrationForm.getUsername().toLowerCase()) != null){
                final String usernameUsed = language.getUsernameUsedError();
                modelMap.addAttribute("usernameUsed", usernameUsed);
            }

            if(result.hasFieldErrors("email")){
                final String emailError = language.getEmailError();
                modelMap.addAttribute("emailError", emailError);
            }

            if(userRepository.findByEmail(registrationForm.getEmail()) != null){
                final String emailUsed = language.getEmailUsed();
                modelMap.addAttribute("emailUsed", emailUsed);
            }

            if(result.hasFieldErrors("password")){
                final String passwordError = language.getPasswordError();
                modelMap.addAttribute("passwordError", passwordError);
            }

            if(result.hasFieldErrors("confirmPW") || !(registrationForm.getPassword().equals(registrationForm.getConfirmPW()))){
                final String passwordConfirmError = language.getPasswordConfirmError();
                modelMap.addAttribute("passwordConfirmError", passwordConfirmError);
            }

            if(result.hasFieldErrors("city")){
                final String cityError = language.getCityError();
                modelMap.addAttribute("cityError", cityError);
            }

            if(result.hasFieldErrors("zip")){
                final String zipError = language.getZipError();
                modelMap.addAttribute("zipError", zipError);
            }

            if(result.hasFieldErrors("streetName")){
                final String streetError = language.getStreetError();
                modelMap.addAttribute("streetError", streetError);
            }
            model.addAttribute("current_category",new Category("AlleKategorien",1));
            model.addAttribute("current_ort",new Location(""));
            
            return "registration";
        }

        UserAccount userAccount = userAccountManager.create(registrationForm.getUsername(), registrationForm.getPassword(), registrationForm.getRole());
        userAccountManager.save(userAccount);

        User user = new User(registrationForm.getId(), userAccount, registrationForm.getLastName(), registrationForm.getFirstName(), registrationForm.getCountry(), registrationForm.getEmail(), registrationForm.getCity(), registrationForm.getZip(), registrationForm.getStreetName(), registrationForm.getAddressAddition(), registrationForm.getLanguage1(), registrationForm.getLanguage2(), registrationForm.getLanguage3());
        userRepository.save(user);

        userAccountManager.disable(userAccount.getIdentifier());

        Validator validator = new Validator(user, EmailUsage.Registration);
        validatorRepository.save(validator);

        EMailController.sendEmail(user.getEmail(),validator.getToken(),validator.getUsage());

        final String emailConfirm = language.getRegistrationConfirm();
        modelMap.addAttribute("emailConfirm", emailConfirm);
        model.addAttribute("current_category",new Category("AlleKategorien",1));
        model.addAttribute("current_ort",new Location(""));
        
        return ("registration");
    }


}