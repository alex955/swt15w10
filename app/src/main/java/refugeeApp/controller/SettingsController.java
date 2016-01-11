package refugeeApp.controller;

import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import refugeeApp.model.*;
import refugeeApp.utilities.CategoryMethods;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;



@Controller
public class SettingsController {
    
    /** The user repository. */
    @Autowired private final UserRepository userRepository;
    
    /** The validator repository. */
    @Autowired private final ValidatorRepository validatorRepository;

    /** The user account manager. */
    private UserAccountManager userAccountManager;

    /** The category methods. */
    @Autowired
    private final CategoryMethods categoryMethods;

    /** The password encoder. */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /** The user settings repository. */
    @Autowired
    private final UserSettingsRepository userSettingsRepository;

    /** The language repository. */
    @Autowired
    private final LanguageRepository languageRepository;

    /** The processed categories. */
    protected LinkedList<CategoryFirstTierObject> processedCategories;

    /**
     * autowired constructor.
     *
     * @param articleRepo the article repo
     * @param userRepository the user repository
     * @param userAccountManager the user account manager
     * @param passwordEncoder the password encoder
     * @param categoryMethods the category methods
     * @param validatorRepository the validator repository
     * @param userSettingsRepository the user settings repository
     * @param languageRepository the language repository
     */
    @Autowired
    public SettingsController(ArticleRepo articleRepo, UserRepository userRepository, UserAccountManager userAccountManager, PasswordEncoder passwordEncoder, CategoryMethods categoryMethods, ValidatorRepository validatorRepository, UserSettingsRepository userSettingsRepository, LanguageRepository languageRepository){
        this.userRepository = userRepository;
        this.userAccountManager= userAccountManager;
        this.passwordEncoder = passwordEncoder;
        this.categoryMethods = categoryMethods;
        this.validatorRepository = validatorRepository;
        this.userSettingsRepository = userSettingsRepository;
        this.languageRepository = languageRepository;
    }

    /**
     * changes settings of certain user.
     *
     * @param userSettingsForm form containing change information
     * @param userAccount Optional containnig either null or useraccount
     * @param model MVC model
     * @return usersettings template
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ="/usersettings")
    public String changeSettings(@ModelAttribute("UserSettingsForm") UserSettingsForm userSettingsForm, @LoggedIn Optional<UserAccount> userAccount, Model model){
		Locale locale = LocaleContextHolder.getLocale();
		String browserLanguage = locale.toString().substring(0, 2);
    	if(languageRepository.findByBrowserLanguage(browserLanguage) == null){
			browserLanguage = "de";
		}
		Language language = languageRepository.findByBrowserLanguage(browserLanguage);
		final String deleteUserPopup = language.getDeleteUserPopup();
		model.addAttribute("deleteUserPopup", deleteUserPopup);
    	
        User user = userRepository.findByUserAccount(userAccount.get());
        
        this.processedCategories = categoryMethods.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);
        model.addAttribute("user", user);
        model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));

        return "usersettings";
    }

    /**
     * processes changed information.
     *
     * @param userSettingsForm form containing information
     * @param result validation result
     * @param userAccount Optional ontaining either user account or null
     * @param model MVC model
     * @param modelMap MVC model map
     * @return usersettigns template, either filled with error information or not if no errors
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/usersettings", method = RequestMethod.POST)
    public String saveSettings(@ModelAttribute("UserSettingsForm") @Valid UserSettingsForm userSettingsForm, BindingResult result, @LoggedIn Optional<UserAccount> userAccount,  Model model, ModelMap modelMap) throws AddressException, MessagingException {

        Locale locale = LocaleContextHolder.getLocale();
        String browserLanguage = locale.toString().substring(0, 2);

        if(languageRepository.findByBrowserLanguage(browserLanguage) == null){
            browserLanguage = "en";
        }

        Language language = languageRepository.findByBrowserLanguage(browserLanguage);

        User user = userRepository.findByUserAccount(userAccount.get());
        UserSettings userSettings = new UserSettings();

        model.addAttribute("userSettings", new UserSettings());
        model.addAttribute("user", user);
        model.addAttribute("current_category",new Category("AlleKategorien",1));
		model.addAttribute("current_ort",new Location(""));

        this.processedCategories = categoryMethods.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);

        if(userSettingsRepository.findByUserId(user.getId()) != null){
            userSettingsRepository.delete(userSettingsRepository.findByUserId(user.getId()));
        }

        boolean errors = false;

        if (!userSettingsForm.getOldPassword().isEmpty()) {
            if (!passwordEncoder.matches(userSettingsForm.getOldPassword(), user.getUserAccount().getPassword().toString()))
                errors = true;
        }

        if(result.hasErrors())
            errors = true;

        if(userRepository.findByEmail(userSettingsForm.getNewEmail()) != null)
            errors = true;

        if(errors) {
            if(!userSettingsForm.getNewPassword().equals(userSettingsForm.getConfirmPW())) {
                final String confirmError = language.getPasswordConfirmError();
                modelMap.addAttribute("confirmError", confirmError);
            }

            if(result.hasFieldErrors("newEmail")){
                final String emailError = language.getEmailError();
                modelMap.addAttribute("emailError", emailError);
            }

            if(userRepository.findByEmail(userSettingsForm.getNewEmail()) != null){
                final String emailUsed = language.getEmailUsed();
                modelMap.addAttribute("emailUsed", emailUsed);
            }

            if(result.hasFieldErrors("newPassword")){
                final String passwordError = language.getPasswordError();
                modelMap.addAttribute("passwordError", passwordError);
            }

            if(result.hasFieldErrors("newZip")){
                final String zipError = language.getZipError();
                modelMap.addAttribute("zipError", zipError);
            }

            if(result.hasFieldErrors("newStreetName")){
                final String streetError = language.getStreetError();
                modelMap.addAttribute("streetError", streetError);
            }

            if (!userSettingsForm.getOldPassword().isEmpty()) {
                if (!passwordEncoder.matches(userSettingsForm.getOldPassword(), user.getUserAccount().getPassword().toString())) {
                    final String oldPwError = language.getOldPwError();
                    modelMap.addAttribute("oldPwError", oldPwError);
                }
            }
            return "usersettings";
        }

        userSettings.setUserId(user.getId());

        //Adressänderung
        if(!userSettingsForm.getNewCity().isEmpty())
            user.setCity(userSettingsForm.getNewCity());

        if(!userSettingsForm.getNewZip().isEmpty())
            user.setZip(userSettingsForm.getNewZip());

        if(!userSettingsForm.getNewStreetName().isEmpty())
            user.setStreetName(userSettingsForm.getNewStreetName());

        if(!userSettingsForm.getNewAddressAddition().isEmpty())
            user.setAddressAddition(userSettingsForm.getNewAddressAddition());

        //Email-Änderung
        if(!userSettingsForm.getNewEmail().isEmpty()) {

            Validator validator = new Validator(user, 3);
            validatorRepository.save(validator);
            userSettings.setNewEmail(userSettingsForm.getNewEmail());

            EMailController.sendEmail(user.getEmail(), validator.getToken(), validator.getUsage());

            final String emailConfirm = language.getEmailConfirm();
            modelMap.addAttribute("emailConfirm", emailConfirm);

        }

        //Passwort-Änderung
        if(!userSettingsForm.getNewPassword().isEmpty()) {

            if (passwordEncoder.matches(userSettingsForm.getOldPassword(), userAccount.get().getPassword().toString()) && userSettingsForm.getNewPassword().equals(userSettingsForm.getConfirmPW())) {
                userAccountManager.changePassword(userAccount.get(), userSettingsForm.getNewPassword());

            } else {
                return "usersettings";
            }
        }

        //Sprachenänderung
        if(userSettingsForm.getNewLanguage1().equals("null")){
            user.setLanguage1(null);
        } else {
            user.setLanguage1(userSettingsForm.getNewLanguage1());
        }

        if(userSettingsForm.getNewLanguage2().equals("null")){
            user.setLanguage2(null);
        } else {
            user.setLanguage2(userSettingsForm.getNewLanguage2());
        }

        if(userSettingsForm.getNewLanguage3().equals("null")){
            user.setLanguage3(null);
        } else {
            user.setLanguage3(userSettingsForm.getNewLanguage3());
        }

        userAccountManager.save(user.getUserAccount());
        userSettingsRepository.save(userSettings);
        userRepository.save(user);

        return "usersettings";
    }

    /**
     * lets a user request a deletion/deactivation of him/herself.
     *
     * @param userAccount userAccount which is to be deleted/deactivated
     * @param modelMap MVC model map
     * @return redirect to logout
     * @throws AddressException the address exception
     * @throws MessagingException the messaging exception
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/deleteuser")
    public String deleteUser(@LoggedIn Optional<UserAccount> userAccount, ModelMap modelMap) throws AddressException, MessagingException {
        User user = userRepository.findByUserAccount(userAccount.get());

        Validator validator = new Validator(user, 2);
        validatorRepository.save(validator);

        EMailController.sendEmail(user.getEmail(), validator.getToken(), 2);

        return "redirect:/logout";
    }



}
