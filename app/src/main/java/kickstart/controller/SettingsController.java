package kickstart.controller;

import kickstart.model.*;
import kickstart.utilities.CategoryMethods;

import org.hibernate.validator.constraints.Email;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import javax.validation.constraints.Null;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Vincenz on 25.11.15.
 */

@Controller
public class SettingsController {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SettingsRepo settingsRepo;

    @Autowired
    private ValidatorRepository validatorRepository;

    private UserAccountManager userAccountManager;
    
	@Autowired private final CategoryMethods categoryMethods;
	
	@Autowired
	private final ArticleRepo articleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

	protected LinkedList<CategoryFirstTierObject> processedCategories; 

    @Autowired
    public SettingsController(ArticleRepo articleRepo, UserRepository userRepository, UserAccountManager userAccountManager, PasswordEncoder passwordEncoder, CategoryMethods categoryMethods, ValidatorRepository validatorRepository, SettingsRepo settingsRepo){
        this.userRepository = userRepository;
        this.userAccountManager= userAccountManager;
        this.passwordEncoder = passwordEncoder;
        this.categoryMethods = categoryMethods;
        this.articleRepo = articleRepo;
        this.validatorRepository = validatorRepository;
        this.settingsRepo = settingsRepo;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ="/usersettings")
    public String changeSettings(@ModelAttribute("UserSettings") @Valid UserSettings userSettings, @LoggedIn Optional<UserAccount> userAccount, Model model){

        User user = userRepository.findByUserAccount(userAccount.get());

        this.processedCategories = categoryMethods.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);
        model.addAttribute("user", user);

        return "usersettings";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/usersettings", method = RequestMethod.POST)
    public String saveSettings(@LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("UserSettings") @Valid UserSettings userSettings, BindingResult result, Model model, SettingsRepo settingsRepo) throws AddressException, MessagingException {

        User user = userRepository.findByUserAccount(userAccount.get());

        userSettings.setUserId(user.getId());

        this.processedCategories = categoryMethods.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);
        model.addAttribute("user", user);

        if(result.hasErrors())
            return "usersettings";

        // System.out.println(user);

        //Adressänderung
        if(!userSettings.getNewCity().isEmpty())
        user.setCity(userSettings.getNewCity());

        if(!userSettings.getNewZip().isEmpty())
        user.setZip(userSettings.getNewZip());

        if(!userSettings.getNewStreetName().isEmpty())
        user.setStreetName(userSettings.getNewStreetName());

        if(!userSettings.getNewHouseNumber().isEmpty())
        user.setHouseNumber(userSettings.getNewHouseNumber());

        if(!userSettings.getNewAddressAddition().isEmpty())
        user.setAddressAddition(userSettings.getNewAddressAddition());

        //Email-Änderung
        if(!userSettings.getNewEmail().isEmpty()) {

            Validator validator = new Validator(user, 3);
            validatorRepository.save(validator);
            EMailController.sendEmail(user.getEmail(), validator.getToken(), 3);

            user.setEmail(userSettings.getNewEmail());
        }

        //Passwort-Änderung
        if(!userSettings.getNewPassword().isEmpty()) {

            if (passwordEncoder.matches(userSettings.getOldPassword(), userAccount.get().getPassword().toString()) && userSettings.getNewPassword().equals(userSettings.getConfirmPW())) {
                userAccountManager.changePassword(userAccount.get(), userSettings.getNewPassword());

            } else {
                return "usersettings";
            }
        }

        //Sprachenänderung
        if(userSettings.getNewLanguage1().equals("null")){
            user.setLanguage1(null);
        } else {
            user.setLanguage1(userSettings.getNewLanguage1());
        }

        if(userSettings.getNewLanguage2().equals("null")){
            user.setLanguage2(null);
        } else {
            user.setLanguage2(userSettings.getNewLanguage2());
        }

        if(userSettings.getNewLanguage3().equals("null")){
            user.setLanguage3(null);
        } else {
            user.setLanguage3(userSettings.getNewLanguage3());
        }

        userAccountManager.save(user.getUserAccount());
        userRepository.save(user);
        settingsRepo.save(userSettings);

        return "redirect:/search";
    }

    @RequestMapping(value = "/deleteuser")
    public String deleteUser(@LoggedIn Optional<UserAccount> userAccount, ModelMap modelMap) throws AddressException, MessagingException {
        User user = userRepository.findByUserAccount(userAccount.get());

        Validator validator = new Validator(user, 2);
        validatorRepository.save(validator);

        EMailController.sendEmail(user.getEmail(), validator.getToken(), validator.getUsage());
        return "redirect:/logout";
    }
    


}
