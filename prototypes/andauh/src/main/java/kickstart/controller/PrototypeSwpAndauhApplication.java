package kickstart.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class PrototypeSwpAndauhApplication {
	
	@Autowired Categories categories;

    public static void main(String[] args) {
        SpringApplication.run(PrototypeSwpAndauhApplication.class, args);
    }
    
    @PostConstruct
    void initialize(){
    	System.out.println("wird initialize aufgerufen");;
    	categories.save(new Category("Möbel", -1));
    	categories.save(new Category("Badmöbel", 1));
    	categories.save(new Category("Küchenmöbel", 1));
    	categories.save(new Category("Bücher", -1));
    	categories.save(new Category("gute Bücher", 4));
    	categories.save(new Category("schlechte Bücher", 4));
    	categories.save(new Category("Blablub", 4));
    	categories.save(new Category("Weapons of math instruction", -1));
    	categories.save(new Category("Gruppenterrorie", 8));
    	categories.save(new Category("Sisisnus und Cosisisnus", 8));
    	categories.save(new Category("nochmal Möbel", -1));
    	categories.save(new Category("nochmal nochmal Möbel", -1));
    }
}