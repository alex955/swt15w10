package kickstart.controller;

import kickstart.model.User;
import kickstart.model.UserRepository;
import kickstart.model.UserSettings;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

/**
 * Created by Vincenz on 25.11.15.
 */

@PreAuthorize("hasRole('ROLE_ADMIN, ROLE_REFUGEE, ROLE_VOLUNTEER')")
public class SettingsController {

    @RequestMapping(value = "/usersettings", method = RequestMethod.POST)
    public String changeSettings(@ModelAttribute("UserSettings") @Valid UserSettings userSettings, BindingResult result) throws AddressException, MessagingException {

        if(result.hasErrors())
            return "usersettings";

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAccountManager userAccountManager = (UserAccountManager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserRepository userRepository = (UserRepository) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAccount userAccount = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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
            userAccountManager.changePassword(userAccount, userSettings.getNewPassword());
        }

        //Sprachenänderung
        if(!userSettings.getNewLanguage1().isEmpty())
        user.setLanguage1(userSettings.getNewLanguage1());

        if(!userSettings.getNewLanguage2().isEmpty())
        user.setLanguage2(userSettings.getNewLanguage2());

        if(!userSettings.getNewLanguage3().isEmpty())
        user.setLanguage3(userSettings.getNewLanguage3());

        userAccountManager.save(userAccount);
        userRepository.save(user);

        return "redirect:/";
    }
}
