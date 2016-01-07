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
    private final LanguageRepository languageRepository;

    final Role refugee = new Role("ROLE_REFUGEE");
    final Role volunteer = new Role("ROLE_VOLUNTEER");
    final Role admin = new Role("ROLE_ADMIN");



    @Autowired
    public RefugeeDataInitializer(UserAccountManager userAccountManager, UserRepository userRepository, CategoryRepo categories, kickstart.model.ArticleRepo goodREPO, ChatConversationRepo chatRepo, ChatMessageRepo msgRepo, SettingsRepository settingsRepo, UserSettingsRepository userSettingsRepository, ValidatorRepository validatorRepository, LanguageRepository languageRepository) {

        Assert.notNull(userAccountManager, "UserManagerAccount must not be null!");
        Assert.notNull(userRepository, "UserRepository must not be null!");
        Assert.notNull(categories, "CategoryRepo must not be null!");
        Assert.notNull(goodREPO, "goodREPO must not be null!");
        Assert.notNull(settingsRepo, "SettingsRepo must not be null!");
        Assert.notNull(userSettingsRepository, "userSettingsRepository must not be null!");
        Assert.notNull(validatorRepository, "validatorRepository must not be null!");
        Assert.notNull(languageRepository, "languageRepository must not be null");

        this.userAccountManager = userAccountManager;
        this.userRepository = userRepository;
        this.categories = categories;
        this.goodREPO = goodREPO;
        this.settingsRepo = settingsRepo;
        this.userSettingsRepository = userSettingsRepository;
        this.validatorRepository = validatorRepository;
        this.languageRepository = languageRepository;

    }

    @Override
    public void initialize() {
    	Settings();
        initializeLanguages();
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
        userRepository.save(new User (1, admin1, "Admin", "Erster", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user1 = userAccountManager.create("user1", "user1", refugee);
        userAccountManager.save(user1);
        userRepository.save(new User (2, user1, "1", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user2 = userAccountManager.create("user2", "user2", refugee);
        userAccountManager.save(user2);
        userRepository.save(new User (3, user2, "2", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user3 = userAccountManager.create("user3", "user3", refugee);
        userAccountManager.save(user3);
        userRepository.save(new User (4, user3, "3", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user4 = userAccountManager.create("user4", "user4", volunteer);
        userAccountManager.save(user4);
        userRepository.save(new User (5, user4, "4", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user5 = userAccountManager.create("user5", "user5", volunteer);
        userAccountManager.save(user5);
        userRepository.save(new User (6, user5, "5", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user6 = userAccountManager.create("user6", "user6", volunteer);
        userAccountManager.save(user6);
        userRepository.save(new User (7, user6, "6", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

    }

    public void initializeCategories(){ 
    	Category cat = new Category("Möbel", -1);
    	LinkedList<String> tags1 = new LinkedList<String>();
        tags1.add("");  tags1.add("Gebraucht");  tags1.add("Neu");  
    	Attribute att = new Attribute("Zustand",tags1);
    	LinkedList<String> tags2 = new LinkedList<String>();
    	tags2.add(""); tags2.add("Blau"); tags2.add("Gelb"); tags2.add("Rot");
    	Attribute att2 = new Attribute("Farbe",tags2);
    	cat.addAttribute(att);
    	cat.addAttribute(att2);
        categories.save(cat);

        categories.save(new Category("Badmöbel", 1));
        categories.save(new Category("Küchenmöbel", 1));
        categories.save(new Category("Bücher", -1));
        categories.save(new Category("Sprachkurs", 4));
        categories.save(new Category("Romane", 4));
        categories.save(new Category("Survivalguides", 4));
        categories.save(new Category("Multimedia & Elektronik", -1));
        categories.save(new Category("Handy", 8));
        categories.save(new Category("Computer", 8));
        categories.save(new Category("Mode", -1));
        categories.save(new Category("Kurse", -1));
    }

    public void initializeGoods(UserAccountManager userAccountManager, UserRepository userRepository){
    	LinkedList<String> tags1 = new LinkedList<String>();
        tags1.add("XXL");
    	Attribute att = new Attribute("Größe",tags1);
    	LinkedList<String> tags2 = new LinkedList<String>();
    	tags2.add("Männlich");
    	Attribute att2 = new Attribute("Geschlecht",tags2);
    	
    	
    	Article g1 = new Article("Spiegelschrank", "Dieser Spiegelschrank ist 60 cm breit", "Dresden - Zschernitz", "Bergstraße 5", 2, "17", "01217", userRepository.findOne((long) 1), "article");
    	 g1.addAttribute(att);
         g1.addAttribute(att2);
    	Article g2 = new Article("Sofa", "Einladender Blickfang! Das stylishe Schlafsofa mit dem zweifarbigen Look lädt zum Entspannen und Träumen ein.", "Dresden - Südvorstadt", "straße", 1, "number", "01067", userRepository.findOne((long) 2), "article");
        Article g3 = new Article("Stuhl", "Schöner Bürostuhl mit einem Bezug aus hochwertigem Kunstleder, kombiniert mit atmungsaktivem Netzstoff im Rückenausschnitt in Schwarz", "Pirna", "straße", 1, "number", "01067", userRepository.findOne((long) 2), "article");
        Article g4 = new Article("Spiegel", "Aus Metall mit aufwendigen Verzierungen", "Dresden - Seidnitz", "straße", 2, "number", "01067", userRepository.findOne((long) 3), "article");
        Article g5 = new Article("Messerblock", "EINFACH GUT! Eine rundum gute Entscheidung wenn es etwas preiswerter sein soll und trotzdem zuverlässig und praktisch. TWIN Point überzeugt durch eine scharfe Klinge.", "Ottendorf Orkrilla", "straße", 3, "number", "01067", userRepository.findOne((long) 1), "article");
        Article g6 = new Article("Buch", "bestens erhalten ohne Eselsohren", "Leipzig", "straße", 4, "number", "01067", userRepository.findOne((long) 1), "article");
        Article g7 = new Article("Deutsch für Anfänger", "Gutes Buch zum lernen", "ort", "straße", 5, "number", "01067", userRepository.findOne((long) 1), "article");
        Article g8 = new Article("Harry Potter", "Harry Potter (* 24.12.0 um 12:30 Uhr) ist ein kleiner Zauberer aus England. Harry lernte in Hogwarts das Zauberstabwedeln", "Hoyerswerda", "straße", 6, "number", "01067", userRepository.findOne((long) 2), "article");
        Article g9 = new Article("Das Survival Duo", "Survival-Duo (Mehrzahl: Survival-Quartett) ist eine satirische und oscarprämierte TV-Sendung auf DMAX.", "Dresden - Prohlis", "straße", 7, "number", "01067", userRepository.findOne((long) 1), "article");
        Article g10 = new Article("Motorola Razor", "Perfektes Handy. Wirkt sogar als Boomerang", "Dresden - Gorbitz", "straße", 9, "number", "01067", userRepository.findOne((long) 1), "article");
        Article g11 = new Article("Jeans", "Beschreibung", "ort", "straße",11, "number", "01067", userRepository.findOne((long) 3), "article");
        Article g12 = new Article("Deutschkurs", "Beschreibung", "ort", "straße", 12, "number", "01067", userRepository.findOne((long) 1), "article");

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


    public void initializeLanguages(){

        final Language german = new Language();

        german.setBrowserLanguage("de");
        german.setRoleError("Es wurde keine Rolle ausgewählt.");
        german.setNameError("Der Name muss zwischen 2 und 30 Zeichen lang sein.");
        german.setCountryError("Es wurde keine Herkunft gewählt");
        german.setUsernameError("Der Benutzername muss zwischen 6 und 30 Zeichen lang sein.");
        german.setEmailError("Die eingegebene E-Mail-Adresse hat kein zugelassenes Format.");
        german.setPasswordError("Das Passwort muss mindestens 8 Zeichen lang sein und muss mindestens eine Zahl, einen Klein- und einen Großbuchstaben beinhalten.");
        german.setPasswordConfirmError("Die Passwörter stimmen nicht überein.");
        german.setCityError("Geben Sie einen Stadtnamen ein");
        german.setZipError("Die Postleitzahl muss aus exakt 5 Ziffern bestehen.");
        german.setStreetError("Geben Sie einen Straßennamen ein.");
        german.setTitleError("Geben Sie ihrem Angebot einen Titel.");
        german.setKindError("Wählen Sie die Art des Angebots.");
        german.setOldPwError("Das alte Passwort wurde falsch eingegeben.");
        german.setRegistrationConfirm("Registrierung erfolgreich. Zur Bestätigung der Registrierung wurde Ihnen eine EMail geschickt.");
        german.setUsernameUsedError("Der Username ist bereits vergeben.");
        german.setEmailConfirm("Zum Bestätigen der Änderung Ihrer EMailadresse wird eine EMail an Ihre alte Adresse geschickt.");

        languageRepository.save(german);


        final Language english = new Language();

        english.setBrowserLanguage("en");
        english.setRoleError("You didn't chose a role.");
        english.setNameError("Your name must be between 2 and 30 characters long.");
        english.setCountryError("You didn't chose your country.");
        english.setUsernameError("The username must be between 6 and 30 characters long.");
        english.setEmailError("Your email has a wrong format.");
        english.setPasswordError("Your password must be at least 8 characters and consist of at least one number, one capital and one lowercase letter.");
        english.setPasswordConfirmError("Passwords did not match.");
        english.setCityError("Enter a city");
        english.setZipError("Your zip code must contain of exactly five digits.");
        english.setStreetError("Enter a street.");
        english.setTitleError("Give your offer a title.");
        english.setKindError("Choose your offers kind.");
        english.setOldPwError("Your old password was incorrect.");
        english.setRegistrationConfirm("Registration successful. An email for confirmation has been sent.");
        english.setUsernameUsedError("The username is already taken.");
        english.setEmailConfirm("To confirm your new email address, an email has been sent to your former email address");

        languageRepository.save(english);
    }
}
