package kickstart.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@PreAuthorize("isAuthenticated()")
public class SettingsController extends CommonVariables {

    @Autowired
    private UserAccountManager userAccountManager;

    @Autowired
    public SettingsController(UserRepository userRepository, UserAccountManager userAccountManager){
        this.userRepository = userRepository;
        this.userAccountManager= userAccountManager;
    }

    @RequestMapping(value = "/usersettings", method = RequestMethod.GET)
    public String changeSettings(@LoggedIn Optional<UserAccount> userAccount, @Valid UserSettings userSettings, BindingResult result) throws AddressException, MessagingException {


        if(result.hasErrors())
            return "usersettings";

        //returning null for whatever reason
        User user = userRepository.findByUsername(userAccount.get().getUsername()); //  Bekommt theoretisch den User; praktisch null

        // DEBUGGING
        System.out.println(userAccount.get().getUsername());    //  Gibt Username aus
        System.out.println(userRepository.findAll().toString(); //  Gibt UserRepo aus
        System.out.println(user);                               //  Gibt null aus
        System.out.println(user.toString());                    // NullPointer

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
        if(!userSettings.getNewPassword().isEmpty())
        if(user.getPassword().equals(userSettings.getNewPassword()) && user.getPassword().equals(userSettings.getConfirmPW())){
            userAccountManager.changePassword(user.getUserAccount(), userSettings.getNewPassword());
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




        return "redirect:/search";
    }
}
