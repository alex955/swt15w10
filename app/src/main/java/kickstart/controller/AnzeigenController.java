package kickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kickstart.model.goodREPO;
import kickstart.model.Good;
import kickstart.model.activityREPO;

@Controller
public class AnzeigenController extends CommonVariables {


	
	@Autowired
	public AnzeigenController(goodREPO grepo, activityREPO arepo){
		this.activityREPO=arepo;
		this.goodREPO=grepo;
		System.out.println("GOOD REPO IST ANGELEGT UND CONTROLLER ZUGEWIESEN");
    	System.out.println(goodREPO.findAll().size());
    	System.out.println("ACT REPO IST ANGELEGT UND CONTROLLER ZUGEWIESEN");
    	System.out.println(activityREPO.findAll().size());
	}
	
	
	
	
	
}