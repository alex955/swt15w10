package kickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kickstart.model.CategoryRepo;
import kickstart.model.UserRepository;

@Controller
public class CommonVariables {
    @Autowired
    protected UserRepository userRepository;
    
	@Autowired
	protected CategoryRepo categories;
}
