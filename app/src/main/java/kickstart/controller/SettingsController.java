package kickstart.controller;

import kickstart.model.User;
import kickstart.model.UserRepository;
import kickstart.model.UserSettings;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Vincenz on 25.11.15.
 */

@Controller
public class SettingsController extends CommonVariables {

    @Autowired
    private UserAccountManager userAccountManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SettingsController(UserRepository userRepository, UserAccountManager userAccountManager, PasswordEncoder passwordEncoderkönn){
        this.userRepository = userRepository;
        this.userAccountManager= userAccountManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ="/usersettings")
    public String changeSettings(@ModelAttribute("UserSettings") @Valid UserSettings userSettings, @LoggedIn Optional<UserAccount> userAccount, Model model){

        User user = userRepository.findByUserAccount(userAccount.get());

        this.processedCategories = this.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);
        model=this.getCurrent_cat(model);
        model.addAttribute("user", user);

        return "usersettings";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/usersettings", method = RequestMethod.POST)
    public String saveSettings(@LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("UserSettings") @Valid UserSettings userSettings, BindingResult result, Model model) throws AddressException, MessagingException {

        User user = userRepository.findByUserAccount(userAccount.get());

        this.processedCategories = this.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);
        model=this.getCurrent_cat(model);
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

        //TODO: Email Confirmation

        if(!userSettings.getNewEmail().isEmpty())
        user.setEmail(userSettings.getNewEmail());

        //Passwort-Änderung
        if(!userSettings.getNewPassword().isEmpty()) {

            /* CONTROL OUTPUT
            System.out.println();
            System.out.println("Altes PW Eingabe: " + userSettings.getOldPassword());
            System.out.println("Altes PW verschlüsselt: " + passwordEncoder.encode(userSettings.getOldPassword()));
            System.out.println("Altes PW von UserAccount:  " + user.getUserAccount().getPassword().toString());
            System.out.println("Altes PW = UserAccount altes PW? " + passwordEncoder.matches(userSettings.getOldPassword(), user.getUserAccount().getPassword().toString()));
            System.out.println();
            System.out.println("Neues PW: " + userSettings.getNewPassword());
            System.out.println("ConfirmPW: " + userSettings.getConfirmPW());
            System.out.println("Neues PW = confirmPW? " + userSettings.getNewPassword().equals(userSettings.getConfirmPW()));
            System.out.println();
            */

            if (passwordEncoder.matches(userSettings.getOldPassword(), userAccount.get().getPassword().toString()) && userSettings.getNewPassword().equals(userSettings.getConfirmPW())) {
                userAccountManager.changePassword(userAccount.get(), userSettings.getNewPassword());
                //System.out.println("PW geändert");
                //System.out.println();

            } else {

               // System.out.println("PW nicht geändert");
               // System.out.println();

                return "usersettings";
            }
        }

        //Sprachenänderung
        if(!userSettings.getNewLanguage1().isEmpty())
        user.setLanguage1(userSettings.getNewLanguage1());

        if(!userSettings.getNewLanguage2().isEmpty())
        user.setLanguage2(userSettings.getNewLanguage2());

        if(!userSettings.getNewLanguage3().isEmpty())
        user.setLanguage3(userSettings.getNewLanguage3());

        userAccountManager.save(user.getUserAccount());
        userRepository.save(user);

        // System.out.println(user);

        return "redirect:/search";
    }
}
