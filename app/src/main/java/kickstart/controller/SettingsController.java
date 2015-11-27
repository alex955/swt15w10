package kickstart.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;
import java.util.List;
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

    @RequestMapping(value ="/usersettings")
    public String firstView(@ModelAttribute("UserSettings") UserSettings userSettings) {
        return ("usersettings");
    }

    @RequestMapping(value = "/usersettings", method = RequestMethod.GET)
    public String changeSettings(@LoggedIn Optional<UserAccount> userAccount, @ModelAttribute("UserSettings") @Valid UserSettings userSettings, BindingResult result) throws AddressException, MessagingException {


        if(result.hasErrors())
            return "usersettings";

        List<User> userList = userRepository.findAll();

        User changedUser = null;

        for(User oldUser: userList){
            if (oldUser.getUserAccount().getUsername().equals(userAccount.get().getUsername())){
                changedUser = oldUser;
                System.out.println(changedUser);
            }
        }

        if(changedUser == null) {
            return "redirect:/";
        }

        //Adressänderung
        if(!userSettings.getNewCity().isEmpty())
        changedUser.setCity(userSettings.getNewCity());

        if(!userSettings.getNewZip().isEmpty())
        changedUser.setZip(userSettings.getNewZip());

        if(!userSettings.getNewStreetName().isEmpty())
        changedUser.setStreetName(userSettings.getNewStreetName());

        if(!userSettings.getNewHouseNumber().isEmpty())
        changedUser.setHouseNumber(userSettings.getNewHouseNumber());

        if(!userSettings.getNewAddressAddition().isEmpty())
        changedUser.setAddressAddition(userSettings.getNewAddressAddition());

        //Email-Änderung

        //TODO: Email Confirmation

        if(!userSettings.getNewEmail().isEmpty())
        changedUser.setEmail(userSettings.getNewEmail());

        //Passwort-Änderung
        if(!userSettings.getNewPassword().isEmpty())
        if(changedUser.getPassword().equals(userSettings.getNewPassword()) && changedUser.getPassword().equals(userSettings.getConfirmPW())){
            userAccountManager.changePassword(changedUser.getUserAccount(), userSettings.getNewPassword());
        }

        //Sprachenänderung
        if(!userSettings.getNewLanguage1().isEmpty())
        changedUser.setLanguage1(userSettings.getNewLanguage1());

        if(!userSettings.getNewLanguage2().isEmpty())
        changedUser.setLanguage2(userSettings.getNewLanguage2());

        if(!userSettings.getNewLanguage3().isEmpty())
        changedUser.setLanguage3(userSettings.getNewLanguage3());

        userAccountManager.save(changedUser.getUserAccount());
        userRepository.save(changedUser);



        return "redirect:/search";
    }
}
