package kickstart.controller;
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

import javax.validation.Valid;


@Controller
public class RegistrationController extends CommonVariables {


    private Model model;

    @Autowired
    public RegistrationController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value ="/registration")
    public String firstView(@ModelAttribute("RegistrationForm") RegistrationForm registrationForm) {
        return ("registration");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String newRegistration(@ModelAttribute("RegistrationForm") @Valid RegistrationForm registrationForm, BindingResult result) {

        if(result.hasErrors())
            return "registration";

        User user = new User(registrationForm.getId(), registrationForm.getRole(), registrationForm.getLastName(), registrationForm.getFirstName(), registrationForm.getUsername(), registrationForm.getEmail(), registrationForm.getPassword(), registrationForm.getConfirmPW(), registrationForm.getCity(), registrationForm.getZip(), registrationForm.getStreetName(), registrationForm.getHouseNumber(), registrationForm.getLanguage1(), registrationForm.getLanguage2(), registrationForm.getLanguage3());
        userRepository.save(user);
        System.out.println(user);
        return ("redirect:/");
    }


}
