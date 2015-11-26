package kickstart.controller;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountIdentifier;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import kickstart.model.RegistrationForm;
import kickstart.model.User;
import kickstart.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;


@Controller
public class RegistrationController extends CommonVariables {


    private Model model;
    private UserAccountManager userAccountManager;

    @Autowired
    public RegistrationController(UserAccountManager userAccountManager, UserRepository userRepository){
        this.userAccountManager = userAccountManager;
        this.userRepository = userRepository;
    }

    @RequestMapping(value ="/registration")
    public String firstView(@ModelAttribute("RegistrationForm") RegistrationForm registrationForm) {
        return ("registration");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String newRegistration(@ModelAttribute("RegistrationForm") @Valid RegistrationForm registrationForm, BindingResult result) throws AddressException, MessagingException {

        if(result.hasErrors())
            return "registration";

        UserAccount userAccount = userAccountManager.create(registrationForm.getUsername(), registrationForm.getPassword(), registrationForm.getRole());
        userAccountManager.save(userAccount);
        
        User user = new User(registrationForm.getId(), userAccount, registrationForm.getLastName(), registrationForm.getFirstName(), registrationForm.getEmail(), registrationForm.getCity(), registrationForm.getZip(), registrationForm.getStreetName(), registrationForm.getHouseNumber(),registrationForm.getAddressAddition(), registrationForm.getLanguage1(), registrationForm.getLanguage2(), registrationForm.getLanguage3());
        userRepository.save(user);
        System.out.println(user);
        System.out.println(user.getHashcode());
        
        EMailController.SendEmail(user.getEmail(), user.getHashcode());
        
        return ("redirect:/");
    }


}
