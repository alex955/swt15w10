package anzeigen;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AnzeigenverwaltungApplication {
	

    public static void main(String[] args) {
        SpringApplication.run(AnzeigenverwaltungApplication.class, args);
    }
    
    private static final Logger log = LoggerFactory.getLogger(AnzeigenverwaltungApplication.class);


	@Bean
	public CommandLineRunner demo(goodREPO repository,activityREPO arepository) {
		return (args) -> {
			// save a couple of articles
			repository.save(new Good("Jack Daniels","Beste Ware","Dresden","img/jacky.jpg"));
			repository.save(new Good("Laserschwert","Heiße Sache","Dresden","img/laser.jpg"));
			repository.save(new Good("Portmonee","Nicht geklaut","Bagdad","img/port.jpg"));
			repository.save(new Good("Bier","Erfrischungsgetränk, alkoholhaltig","Dresden","img/bier.jpg"));
			repository.save(new Good("Raubtier","Gefährliches Tier","Pirna","img/tiger.jpg"));
			repository.save(new Good("Fernseher","Riss über ganzen Display","Pirna","img/fernseher.jpg"));
	
			arepository.save(new Activity("Deutsch lernen","Unterricht beim Lehrer","Pirna",new Date(2015,10,10,15,40,0),new Date(2015,10,10,15,50,0)));
			arepository.save(new Activity("Schwertkampf","Werde der Jon Snow unter den Schwertkämpfern","Pirna",new Date(2015,10,10,15,40,0),new Date(2015,10,10,15,55,0)));
			arepository.save(new Activity("Schwertkampf","Werde der Jon Snow unter den Schwertkämpfern","Pirna",new Date(2015,10,10,15,40,0),new Date(2015,10,10,15,55,0)));
			arepository.save(new Activity("Taschenrechnerumgang","Get know all about calc","Dresden",new Date(2015,10,10,15,40,0),new Date(2015,10,10,15,55,0)));			
			arepository.save(new Activity("Programmierung","Java wird dir das Leben retten","Dresden",new Date(2015,10,10,15,40,0),new Date(2015,10,10,15,55,0)));
			
			

			// alle Artikel anzeigen
			log.info("Alle Artikel:");
			log.info("-------------------------------");
			for (Good good : repository.findAll()) {
				log.info(good.toString());
			}  
            log.info("");

			// Einen Artikel suchen mit ID
			Good good = repository.findOne(1L);
			log.info("Artikel mit der ID 1L:");
			log.info("--------------------------------");
			log.info(good.toString());
            log.info("");

			// Alle Güter in Dresden
			log.info("Alle Güter in Dresden");
			log.info("--------------------------------------------");
			for (Good good1 : repository.findByLocation("Dresden")) {
				log.info(good1.toString());
			}
			log.info("");
			log.info("");
			log.info("");
			log.info("");
            log.info("");// alle Activities anzeigen
			log.info("Alle Activities:");
			log.info("-------------------------------");
			for (Activity activity : arepository.findAll()) {
				log.info(activity.toString());
			}  
            log.info("");

			// Eine Activity suchen mit ID
			Activity activity3 = arepository.findOne(7L);
			log.info("Activities mit der ID 7L:");
			log.info("--------------------------------");
			log.info(activity3.toString());
            log.info("");

			// Alle Activitys in Dresden
			log.info("Alle Aktivitäten in Dresden");
			log.info("--------------------------------------------");
			for (Activity activity1 : arepository.findByLocation("Dresden")) {
				log.info(activity1.toString());
			}
            log.info("");
		};
	}

}


