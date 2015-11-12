package kickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kickstart.model.goodREPO;
import kickstart.model.activityREPO;

@Controller
public class AnzeigenController {

	@Autowired
	private final goodREPO goodREPO;
	@Autowired
	private final activityREPO activityREPO;
	
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