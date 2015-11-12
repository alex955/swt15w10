package kickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;

import kickstart.model.CategoryRepo;
import kickstart.model.UserRepository;

public class CommonVariables {
    @Autowired
    protected UserRepository userRepository;
    
	@Autowired
	protected CategoryRepo categories;
}
