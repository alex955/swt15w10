package kickstart.controller;

import kickstart.model.*;
import kickstart.utilities.CategoryMethods;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import java.util.LinkedList;
import java.util.Optional;

/**
 * Created by Vincenz on 25.11.15.
 */

@Controller
public class SettingsController {
    @Autowired private final UserRepository userRepository;
    @Autowired private final ValidatorRepository validatorRepository;
    private UserAccountManager userAccountManager;
    @Autowired private final CategoryMethods categoryMethods;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private final UserSettingsRepository userSettingsRepository;
    protected LinkedList<CategoryFirstTierObject> processedCategories;

    @Autowired
    public SettingsController(ArticleRepo articleRepo, UserRepository userRepository, UserAccountManager userAccountManager, PasswordEncoder passwordEncoder, CategoryMethods categoryMethods, ValidatorRepository validatorRepository, UserSettingsRepository userSettingsRepository){
        this.userRepository = userRepository;
        this.userAccountManager= userAccountManager;
        this.passwordEncoder = passwordEncoder;
        this.categoryMethods = categoryMethods;
        this.validatorRepository = validatorRepository;
        this.userSettingsRepository = userSettingsRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ="/usersettings")
    public String changeSettings(@ModelAttribute("UserSettingsForm") UserSettingsForm userSettingsForm, @LoggedIn Optional<UserAccount> userAccount, Model model){

        User user = userRepository.findByUserAccount(userAccount.get());
        
        this.processedCategories = categoryMethods.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);
        model.addAttribute("user", user);
        model.addAttribute("current_category",new Category("Alle Kategorien",1));
		model.addAttribute("current_ort",new Ort(""));

        return "usersettings";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/usersettings", method = RequestMethod.POST)
    public String saveSettings(@ModelAttribute("UserSettingsForm") @Valid UserSettingsForm userSettingsForm, BindingResult result, @LoggedIn Optional<UserAccount> userAccount,  Model model, ModelMap modelMap) throws AddressException, MessagingException {

        User user = userRepository.findByUserAccount(userAccount.get());
        UserSettings userSettings = new UserSettings();

        model.addAttribute("userSettings", new UserSettings());
        model.addAttribute("user", user);
        model.addAttribute("current_category",new Category("Alle Kategorien",1));
		model.addAttribute("current_ort",new Ort(""));

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

        if(errors) {
            if(!userSettingsForm.getNewPassword().equals(userSettingsForm.getConfirmPW())) {
                final String confirmError = "Die Passwörter stimmen nicht überein.";
                modelMap.addAttribute("confirmError", confirmError);
            }
            if(!passwordEncoder.matches(userSettingsForm.getOldPassword(), user.getUserAccount().getPassword().toString())) {
                final String oldPwError = "Das alte Passwort wurde falsch eingegeben.";
                modelMap.addAttribute("oldPwError", oldPwError);
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

            final String emailConfirm = "Zum Bestätigen der Änderung Ihrer EMailadresse wird eine EMail an Ihre alte Adresse geschickt.";
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

    @RequestMapping(value = "/deleteuser")
    public String deleteUser(@LoggedIn Optional<UserAccount> userAccount, ModelMap modelMap) throws AddressException, MessagingException {
        User user = userRepository.findByUserAccount(userAccount.get());

        Validator validator = new Validator(user, 2);
        validatorRepository.save(validator);

        EMailController.sendEmail(user.getEmail(), validator.getToken(), 2);

        return "redirect:/logout";
    }



}
