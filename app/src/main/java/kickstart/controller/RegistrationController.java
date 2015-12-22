package kickstart.controller;
import kickstart.model.*;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;

import kickstart.utilities.CategoryMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;


@Controller
public class RegistrationController {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final CategoryRepo categories;

    @Autowired
    private ValidatorRepository validatorRepository;

    @Autowired private final CategoryMethods categoryMethods;

    protected LinkedList<CategoryFirstTierObject> processedCategories;

    private UserAccountManager userAccountManager;

    @Autowired
    public RegistrationController(UserAccountManager userAccountManager, UserRepository userRepository, CategoryMethods categoryMethods, CategoryRepo categories, ValidatorRepository validatorRepository){
        this.userAccountManager = userAccountManager;
        this.userRepository = userRepository;
        this.categoryMethods = categoryMethods;
        this.categories = categories;
        this.validatorRepository = validatorRepository;
    }

    @RequestMapping(value ="/registration")
    public String firstView(@ModelAttribute("RegistrationForm") RegistrationForm registrationForm, Model model) {
        return ("registration");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String newRegistration(@ModelAttribute("RegistrationForm") @Valid RegistrationForm registrationForm, BindingResult result,ModelMap modelMap, Model model) throws AddressException, MessagingException {

        if(result.hasErrors()) {
            if(!registrationForm.getPassword().equals(registrationForm.getConfirmPW())){
                final String confirmError = "Die Passwörter stimmen nicht überein.";
                modelMap.addAttribute("confirmError", confirmError);
            }
            return "registration";
        }


        UserAccount userAccount = userAccountManager.create(registrationForm.getUsername(), registrationForm.getPassword(), registrationForm.getRole());
        userAccountManager.save(userAccount);

        User user = new User(registrationForm.getId(), userAccount, registrationForm.getLastName(), registrationForm.getFirstName(), registrationForm.getCountry(), registrationForm.getEmail(), registrationForm.getCity(), registrationForm.getZip(), registrationForm.getStreetName(), registrationForm.getHouseNumber(),registrationForm.getAddressAddition(), registrationForm.getLanguage1(), registrationForm.getLanguage2(), registrationForm.getLanguage3());
        userRepository.save(user);

        userAccountManager.disable(userAccount.getIdentifier());

        Validator validator = new Validator(user, 1);
        validatorRepository.save(validator);

        EMailController.sendEmail(user.getEmail(),validator.getToken(),validator.getUsage());


        return ("registration");
    }


}