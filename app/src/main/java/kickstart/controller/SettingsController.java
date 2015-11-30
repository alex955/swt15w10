package kickstart.controller;

import com.sun.istack.internal.NotNull;
import kickstart.model.RegistrationForm;
import kickstart.model.User;
import kickstart.model.UserRepository;
import kickstart.model.UserSettings;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountIdentifier;
import org.salespointframework.useraccount.UserAccountManager;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
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
    public SettingsController(UserRepository userRepository, UserAccountManager userAccountManager, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userAccountManager= userAccountManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ="/usersettings")
    public String changeSettings(@ModelAttribute("UserSettings") @Valid UserSettings userSettings, @LoggedIn Optional<UserAccount> userAccount, Model model){

        User user = userRepository.findByUserAccount(userAccount.get());

        model.addAttribute("user", user);

        this.processedCategories = this.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);
        model=this.getCurrent_cat(model);


        return "usersettings";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/usersettings", method = RequestMethod.POST)
    public String saveSettings(@LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("UserSettings") @Valid UserSettings userSettings, BindingResult result, Model model) throws AddressException, MessagingException {


        this.processedCategories = this.getProcessedCategories();
        model.addAttribute("categories", this.processedCategories);


        if(result.hasErrors())
            return "usersettings";

        User user = userRepository.findByUserAccount(userAccount.get());

        System.out.println(user);

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
        if (!userSettings.getNewPassword().isEmpty())
        if(passwordEncoder.matches(userSettings.getOldPassword(), user.getUserAccount().getPassword().toString()) && userSettings.getNewPassword().equals(userSettings.getConfirmPW())){
            userAccountManager.changePassword(userAccount.get(), userSettings.getNewPassword());
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

        System.out.println(user);

        model=this.getCurrent_cat(model);


        return "redirect:/search";
    }
}
