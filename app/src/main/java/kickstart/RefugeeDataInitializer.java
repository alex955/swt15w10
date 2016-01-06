package kickstart;

import kickstart.model.*;
import kickstart.utilities.Setting;
import kickstart.utilities.SettingsRepository;

import java.util.LinkedList;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by Vincenz on 24.11.15.
 */
@Component
public class RefugeeDataInitializer implements DataInitializer {

    private final UserAccountManager userAccountManager;
    private final UserRepository userRepository;
    private final CategoryRepo categories;
    private final ArticleRepo goodREPO;
    private final SettingsRepository settingsRepo;
    private final UserSettingsRepository userSettingsRepository;
    private final ValidatorRepository validatorRepository;
    
    private final ChatConversationRepo chatRepo; 
    private final ChatMessageRepo msgRepo;

    final Role refugee = new Role("ROLE_REFUGEE");
    final Role volunteer = new Role("ROLE_VOLUNTEER");
    final Role admin = new Role("ROLE_ADMIN");



    @Autowired
    public RefugeeDataInitializer(UserAccountManager userAccountManager, UserRepository userRepository, CategoryRepo categories, kickstart.model.ArticleRepo goodREPO, ChatConversationRepo chatRepo, ChatMessageRepo msgRepo, SettingsRepository settingsRepo, UserSettingsRepository userSettingsRepository, ValidatorRepository validatorRepository) {

        Assert.notNull(userAccountManager, "UserManagerAccount must not be null!");
        Assert.notNull(userRepository, "UserRepository must not be null!");
        Assert.notNull(categories, "CategoryRepo must not be null!");
        Assert.notNull(goodREPO, "goodREPO must not be null!");
        Assert.notNull(settingsRepo, "SettingsRepo must not be null!");
        Assert.notNull(userSettingsRepository, "userSettingsRepository must not be null!");
        Assert.notNull(validatorRepository, "validatorRepository must not be null!");

        this.userAccountManager = userAccountManager;
        this.userRepository = userRepository;
        this.categories = categories;
        this.goodREPO = goodREPO;
        this.settingsRepo = settingsRepo;
        this.userSettingsRepository = userSettingsRepository;
        this.validatorRepository = validatorRepository;
        
        this.chatRepo = chatRepo;
        this.msgRepo = msgRepo;

    }

    @Override
    public void initialize() {
    	Settings();
    	initializeUsers(userAccountManager, userRepository);
        initializeCategories();
        initializeGoods(userAccountManager, userRepository);
        initializeActivities();
    }
    
    //configure the right Paths for the Server
    public void Settings(){
    	settingsRepo.save(new Setting("noUploadedPicturePath", "C:/Users/sasch/Documents/swt15w10/app/src/main/resources/static/resources/img/keinbild.png", "The Path to the Application and the img folder in resources, where a standard picture is for the uploaded articles without one"));
    	//settingsRepo.save(new Setting("UploadedPicturePath", "C:/", "The Path, where the uploaded Pictures are saved"));
    }
    
    public void initializeUsers(UserAccountManager userAccountManager, UserRepository userRepository){
    	
        if (userAccountManager.findByUsername("admin1").isPresent()) return;

        UserAccount admin1 = userAccountManager.create("admin1", "admin1", admin);
        userAccountManager.save(admin1);
        userRepository.save(new User (1, admin1, "Admin", "Erster", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user1 = userAccountManager.create("user1", "user1", refugee);
        user1.setEmail("ref@gmx.de");
        userAccountManager.save(user1);
        userRepository.save(new User (2, user1, "1", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user2 = userAccountManager.create("user2", "user2", refugee);
        userAccountManager.save(user2);
        userRepository.save(new User (3, user2, "2", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user3 = userAccountManager.create("user3", "user3", refugee);
        userAccountManager.save(user3);
        userRepository.save(new User (4, user3, "3", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user4 = userAccountManager.create("user4", "user4", volunteer);
        userAccountManager.save(user4);
        userRepository.save(new User (5, user4, "4", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user5 = userAccountManager.create("user5", "user5", volunteer);
        userAccountManager.save(user5);
        userRepository.save(new User (6, user5, "5", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1",  "", "german", "english", "arab"));

        UserAccount user6 = userAccountManager.create("user6", "user6", volunteer);
        userAccountManager.save(user6);
        userRepository.save(new User (7, user6, "6", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1",  "", "german", "english", "arab"));

    }

    public void initializeCategories(){ 
//    	Category cat = new Category("Möbel", -1);
//    	LinkedList<String> tags1 = new LinkedList<String>();
//        tags1.add("");  tags1.add("Gebraucht");  tags1.add("Neu");  
//    	Attribute att = new Attribute("Zustand",tags1);
//    	LinkedList<String> tags2 = new LinkedList<String>();
//    	tags2.add(""); tags2.add("Blau"); tags2.add("Gelb"); tags2.add("Rot");
//    	Attribute att2 = new Attribute("Farbe",tags2);
//    	cat.addAttribute(att);
//    	cat.addAttribute(att2);
//        categories.save(cat);
//
//        categories.save(new Category("Badmöbel", 1));
//        categories.save(new Category("Küchenmöbel", 1));
//        categories.save(new Category("Bücher", -1));
//        categories.save(new Category("Sprachkurs", 4));
//        categories.save(new Category("Romane", 4));
//        categories.save(new Category("Survivalguides", 4));
//        categories.save(new Category("Multimedia & Elektronik", -1));
//        categories.save(new Category("Handy", 8));
//        categories.save(new Category("Computer", 8));
//        categories.save(new Category("Mode", -1));
//        categories.save(new Category("Kurse", -1));
    	
        Category cat1 = new Category("Fahrrad", -1);
        categories.save(cat1);
        
        Category cat2 = new Category("Familie, Kind & Wohnung", -1); 
        		Category cat2_1 = new Category("Babybekleidung", 2);
        		Category cat2_2 = new Category("Kinderwagen", 2);
        		Category cat2_3 = new Category("Kinderbekleidung", 2);
        		Category cat2_4 = new Category("Möbel", 2);
        		Category cat2_5 = new Category("Spielzeug & Spiele", 2);
        categories.save(cat2);
        categories.save(cat2_1);	
        categories.save(cat2_2);	
        categories.save(cat2_3);	
        categories.save(cat2_4);	
        categories.save(cat2_5);	

        Category cat3 = new Category("Hobby", -1);
        categories.save(cat3);

    	Category cat4 = new Category("Haus & Garten Accesoires", -1);
        		Category cat4_1 = new Category("Dekoration", 9);
        		Category cat4_2 = new Category("Garten & Pflanzen", 9);
        		Category cat4_3 = new Category("Heimwerken", 9);
        		Category cat4_4 = new Category("Bad, Küche & Esszimmer", 9);
        		Category cat4_5 = new Category("Wohnzimmer", 9);
        categories.save(cat4);
        categories.save(cat4_1);
        categories.save(cat4_2);
        categories.save(cat4_3);
        categories.save(cat4_4);
        categories.save(cat4_5);
        		
        		
        Category cat5 = new Category("Kleidung, Mode & Beauty", -1);
        		Category cat5_1 = new Category("Damenbekleidung", 15);
        		Category cat5_2 = new Category("Damenschuhe", 15);
        		Category cat5_3 = new Category("Herrenbekleidung", 15);
        		Category cat5_4 = new Category("Herrenschuhe", 15);
        		Category cat5_5 = new Category("Schmuck & Accessoires", 15);
        		Category cat5_6 = new Category("Taschen", 15);
        categories.save(cat5);		
        categories.save(cat5_1);
        categories.save(cat5_2);
        categories.save(cat5_3);
        categories.save(cat5_4);
        categories.save(cat5_5);
        categories.save(cat5_6);
        		
        Category cat6 = new Category("Multimedia & Elektronik", -1);
        		Category cat6_1 = new Category("Audio & Hifi", 22);
        		Category cat6_2 = new Category("Telefon", 22);
        		Category cat6_3 = new Category("Foto", 22);
        		Category cat6_4 = new Category("TV", 22);
        categories.save(cat6);
        categories.save(cat6_1);
        categories.save(cat6_2);
        categories.save(cat6_3);
        categories.save(cat6_4);
        		
        Category cat7 = new Category("Musik, Film & Bücher ", -1);
        		Category cat7_1 = new Category("Bücher", 27);
        		Category cat7_2 = new Category("Comics", 27);
        		Category cat7_3 = new Category("Fachliteratur", 27);
        		Category cat7_4 = new Category("Filme", 27);
        		Category cat7_5 = new Category("Musik & Musikinstrumente", 27);
        		Category cat7_6 = new Category("Zeitschriften", 27);
        		Category cat7_7 = new Category("Verschiedenes", 27);
        categories.save(cat7);
        categories.save(cat7_1);
        categories.save(cat7_2);
        categories.save(cat7_3);
        categories.save(cat7_4);
        categories.save(cat7_5);
        categories.save(cat7_6);
        categories.save(cat7_7);
        		
        Category cat8 = new Category("Unterricht, Kurse & Aktivitäten", -1);
//        		Category cat8_1 = new Category("Backen & Kochen", 35);
//        		Category cat8_2 = new Category("Computer", 35);
//        		Category cat8_3 = new Category("Sprachen", 35);
//        		Category cat8_4 = new Category("Kunst & Gestalten", 35);
//        		Category cat8_5 = new Category("Musik & Gesang", 35);
//        		Category cat8_6 = new Category("Sport, Tanzen & Fitness", 35);
//        		Category cat8_7 = new Category("Anderes", 35);
        categories.save(cat8);	
//        categories.save(cat8_1);
//        categories.save(cat8_2);
//        categories.save(cat8_3);
//        categories.save(cat8_4);
//        categories.save(cat8_5);
//        categories.save(cat8_6);
//        categories.save(cat8_7);
    }

    public void initializeGoods(UserAccountManager userAccountManager, UserRepository userRepository){
    	LinkedList<String> tags1 = new LinkedList<String>();
        tags1.add("XXL");
    	Attribute att = new Attribute("Größe",tags1);
    	LinkedList<String> tags2 = new LinkedList<String>();
    	tags2.add("Männlich");
    	Attribute att2 = new Attribute("Geschlecht",tags2);
    	
    	
    	Article g1 = new Article("Spiegelschrank", "Dieser Spiegelschrank ist 60 cm breit", "Dresden - Zschernitz", "Bergstraße 5", 2, "01217", userRepository.findOne((long) 1), "article");
    	 g1.addAttribute(att);
         g1.addAttribute(att2);
    	Article g2 = new Article("Sofa", "Einladender Blickfang! Das stylishe Schlafsofa mit dem zweifarbigen Look lädt zum Entspannen und Träumen ein.", "Dresden - Südvorstadt", "straße", 1,  "01067", userRepository.findOne((long) 2), "article");
        Article g3 = new Article("Stuhl", "Schöner Bürostuhl mit einem Bezug aus hochwertigem Kunstleder, kombiniert mit atmungsaktivem Netzstoff im Rückenausschnitt in Schwarz", "Pirna", "straße", 1,  "01067", userRepository.findOne((long) 2), "article");
        Article g4 = new Article("Spiegel", "Aus Metall mit aufwendigen Verzierungen", "Dresden - Seidnitz", "straße", 2, "01067", userRepository.findOne((long) 3), "article");
        Article g5 = new Article("Messerblock", "EINFACH GUT! Eine rundum gute Entscheidung wenn es etwas preiswerter sein soll und trotzdem zuverlässig und praktisch. TWIN Point überzeugt durch eine scharfe Klinge.", "Ottendorf Orkrilla", "straße", 3, "01067", userRepository.findOne((long) 1), "article");
        Article g6 = new Article("Buch", "bestens erhalten ohne Eselsohren", "Leipzig", "straße", 4, "01067", userRepository.findOne((long) 1), "article");
        Article g7 = new Article("Deutsch für Anfänger", "Gutes Buch zum lernen", "ort", "straße", 5, "01067", userRepository.findOne((long) 1), "article");
        Article g8 = new Article("Harry Potter", "Harry Potter (* 24.12.0 um 12:30 Uhr) ist ein kleiner Zauberer aus England. Harry lernte in Hogwarts das Zauberstabwedeln", "Hoyerswerda", "straße", 6, "01067", userRepository.findOne((long) 2), "article");
        Article g9 = new Article("Das Survival Duo", "Survival-Duo (Mehrzahl: Survival-Quartett) ist eine satirische und oscarprämierte TV-Sendung auf DMAX.", "Dresden - Prohlis", "straße", 7, "01067", userRepository.findOne((long) 1), "article");
        Article g10 = new Article("Motorola Razor", "Perfektes Handy. Wirkt sogar als Boomerang", "Dresden - Gorbitz", "straße", 9,  "01067", userRepository.findOne((long) 1), "article");
        Article g11 = new Article("Jeans", "Beschreibung", "ort", "straße",11,  "01067", userRepository.findOne((long) 3), "article");
        Article g12 = new Article("Deutschkurs", "Beschreibung", "ort", "straße", 12, "01067", userRepository.findOne((long) 1), "article");

        goodREPO.save(g1);
        goodREPO.save(g2);
        goodREPO.save(g3);
        goodREPO.save(g4);
        goodREPO.save(g5);
        goodREPO.save(g6);
        goodREPO.save(g7);
        goodREPO.save(g8);
        goodREPO.save(g9);
        goodREPO.save(g10);
        goodREPO.save(g11);
        goodREPO.save(g12);
    }

    public void initializeActivities(){
    }
}
