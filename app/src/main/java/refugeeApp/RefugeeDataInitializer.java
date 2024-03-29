package refugeeApp;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import refugeeApp.model.*;

import java.io.IOException;
import java.util.LinkedList;

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
    private final LanguageRepository languageRepository;

    private final Role refugee = new Role("ROLE_REFUGEE");
    private final Role volunteer = new Role("ROLE_VOLUNTEER");
    private final Role admin = new Role("ROLE_ADMIN");

    /**
     * autowiring constructor
     *
     * @param userAccountManager
     * @param userRepository
     * @param categories
     * @param goodREPO
     * @param settingsRepo
     * @param userSettingsRepository
     * @param validatorRepository
     * @param languageRepository
     */

    @Autowired
    public RefugeeDataInitializer(UserAccountManager userAccountManager, UserRepository userRepository, CategoryRepo categories, refugeeApp.model.ArticleRepo goodREPO, SettingsRepository settingsRepo, UserSettingsRepository userSettingsRepository, ValidatorRepository validatorRepository, LanguageRepository languageRepository) {

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
        this.languageRepository = languageRepository;

    }

    /**
     * call of all subinitializations
     */
    @Override
    public void initialize() {
        Settings();
        initializeLanguages();
        initializeUsers(userAccountManager, userRepository);
        initializeCategories();
        initializeGoods(userRepository);
    }

    /**
     * general settings of the application
     *
     * @throws IOException
     */
    public void Settings() {

        //Image settings

        //Image width
        settingsRepo.save(new Setting("imageWidth", 400, ""));
        //Image height
        settingsRepo.save(new Setting("imageHeight", 400, ""));

        //Alexander
        //settingsRepo.save(new Setting("noUploadedPicturePath", "/Users/Alexander/Documents/Studium/swt15w10/app/src/main/resources/static/resources/img/keinbild.png.png", "The Path to the Application and the img folder in resources, where a standard picture is for the uploaded offers without one"));

        //Sascha
        settingsRepo.save(new Setting("noUploadedPicturePath", "/Users/sasch/Documents/swt15w10/app/src/main/resources/static/resources/img/keinbild.png.png", "The Path to the Application and the img folder in resources, where a standard picture is for the uploaded offers without one"));

        //Lukas
        //settingsRepo.save(new Setting("noUploadedPicturePath", "/Users/lukas/Desktop/homework.PNG", "The Path to the Application and the img folder in resources, where a standard picture is for the uploaded offers without one"));
        
        //resizes the standard picture
//    	if(ImageIO.read(new File(settingsRepo.findOne("noUploadedPicturePath").getStringValue()))!= null){
//	    	BufferedImage originalImage = ImageIO.read(new File(settingsRepo.findOne("noUploadedPicturePath").getStringValue()));
//			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
//			BufferedImage resizedImage = new BufferedImage(settingsRepo.findOne("imageWidth").getIntValue(), settingsRepo.findOne("imageHeight").getIntValue(), type);
//			Graphics2D g = resizedImage.createGraphics();
//			g.drawImage(originalImage, 0, 0, settingsRepo.findOne("imageWidth").getIntValue(), settingsRepo.findOne("imageHeight").getIntValue(), null);
//			g.dispose();
//			ImageIO.write(resizedImage, "png", new File(settingsRepo.findOne("noUploadedPicturePath").getStringValue()+".png"));
//			//delete old file
//			Files.delete(Paths.get(settingsRepo.findOne("noUploadedPicturePath").getStringValue()));
//			settingsRepo.findOne("noUploadedPicturePath").setStringValue(settingsRepo.findOne("noUploadedPicturePath").getStringValue()+".png");
//    	}

        //settingsRepo.save(new Setting("UploadedPicturePath", "C:/", "The Path, where the uploaded Pictures are saved"));

    }

    /**
     * initialisation of users
     *
     * @param userAccountManager
     * @param userRepository
     */
    public void initializeUsers(UserAccountManager userAccountManager, UserRepository userRepository) {

        if (userAccountManager.findByUsername("admin1").isPresent()) return;

        UserAccount admin1 = userAccountManager.create("admin1", "admin1", admin);
        userAccountManager.save(admin1);
        userRepository.save(new User(1, admin1, "Admin", "Erster", "DataInitializer", "test1@test.test", "Stadt", "01234", "Straße 1", "", "Deutsch (German)", "Español (Spanish)", "عربي (Arabic)"));

        UserAccount user1 = userAccountManager.create("user1", "user1", refugee);
        user1.setEmail("ref@gmx.de");
        userAccountManager.save(user1);
        userRepository.save(new User(2, user1, "1", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1", "", "Deutsch (German)", "Español (Spanish)", "عربي (Arabic)"));

        UserAccount user2 = userAccountManager.create("user2", "user2", refugee);
        userAccountManager.save(user2);
        userRepository.save(new User(3, user2, "2", "User", "DataInitializer", "test2@test.test", "Stadt", "01234", "Straße 1", "", "Deutsch (German)", "Español (Spanish)", "عربي (Arabic)"));

        UserAccount user3 = userAccountManager.create("user3", "user3", refugee);
        userAccountManager.save(user3);
        userRepository.save(new User(4, user3, "3", "User", "DataInitializer", "test3@test.test", "Stadt", "01234", "Straße 1", "", "Deutsch (German)", "Español (Spanish)", "عربي (Arabic)"));

        UserAccount user4 = userAccountManager.create("user4", "user4", volunteer);
        userAccountManager.save(user4);
        userRepository.save(new User(5, user4, "4", "User", "DataInitializer", "test4@test.test", "Stadt", "01234", "Straße 1", "", "Deutsch (German)", "Español (Spanish)", "عربي (Arabic)"));

        UserAccount user5 = userAccountManager.create("user5", "user5", volunteer);
        userAccountManager.save(user5);
        userRepository.save(new User(6, user5, "5", "User", "DataInitializer", "test5@test.test", "Stadt", "01234", "Straße 1", "", "Deutsch (German)", "Español (Spanish)", "عربي (Arabic)"));

        UserAccount user6 = userAccountManager.create("user6", "user6", volunteer);
        userAccountManager.save(user6);
        userRepository.save(new User(7, user6, "6", "User", "DataInitializer", "test6@test.test", "Stadt", "01234", "Straße 1", "", "Deutsch (German)", "Español (Spanish)", "عربي (Arabic)"));

        UserAccount user7 = userAccountManager.create("NeuerNameUnseresUsers", "NeuerNameUnseresUsers", volunteer);
        userAccountManager.save(user7);
        userRepository.save(new User(7, user7, "6", "User", "DataInitializer", "NeuerNameUnseresUsers7@test.test", "Stadt", "01234", "Straße 1", "", "Deutsch (German)", "Español (Spanish)", "عربي (Arabic)"));
        
    }

    /**
     * terribly hardcoded initialisation of categories
     */
    public void initializeCategories() {
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
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            cat1.addAttribute(attState);
            cat1.addAttribute(attColor);
        }
        categories.save(cat1);

        Category cat2 = new Category("FamilieKindWohnung", -1);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            cat2.addAttribute(attColor);
            cat2.addAttribute(attState);
        }

        Category cat2_1 = new Category("Babybekleidung", 2);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            cat2_1.addAttribute(attColor);
            cat2_1.addAttribute(attState);
        }


        Category cat2_2 = new Category("Kinderwagen", 2);
        {
            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.stroller.for.1child");
            tempList.add("att.stroller.for.2childs");
            tempList.add("att.other");
            Attribute attStroller = new Attribute("att.stroller.for", tempList);

            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            cat2_2.addAttribute(attColor);
            cat2_2.addAttribute(attState);
            cat2_2.addAttribute(attStroller);
        }


        Category cat2_3 = new Category("Kinderbekleidung", 2);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.childrenClothing.for.baby");
            tempList.add("att.childrenClothing.for.toddler");
            tempList.add("att.childrenClothing.for.schoolchild");
            Attribute newAtt = new Attribute("att.childrenClothing.for", tempList);

            cat2_3.addAttribute(attColor);
            cat2_3.addAttribute(attState);
            cat2_3.addAttribute(newAtt);
        }

        Category cat2_4 = new Category("Möbel", 2);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.furniture.for.bath");
            tempList.add("att.furniture.for.kitchen");
            tempList.add("att.furniture.for.livingRoom");
            tempList.add("att.furniture.for.diningRoom");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.furniture.for", tempList);

            cat2_4.addAttribute(attColor);
            cat2_4.addAttribute(attState);
            cat2_4.addAttribute(newAtt);
        }
        Category cat2_5 = new Category("SpielzeugSpiele", 2);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.baby");
            tempList.add("att.children");
            tempList.add("att.adult");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.games.for", tempList);

            cat2_5.addAttribute(attColor);
            cat2_5.addAttribute(attState);
            cat2_5.addAttribute(newAtt);
        }
        categories.save(cat2);
        categories.save(cat2_1);
        categories.save(cat2_2);
        categories.save(cat2_3);
        categories.save(cat2_4);
        categories.save(cat2_5);

        Category cat3 = new Category("Hobby", -1);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.musicArt");
            tempList.add("att.sports");
            tempList.add("att.culture");
            tempList.add("att.entertainment");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.hobby", tempList);

            cat3.addAttribute(attState);
            cat3.addAttribute(newAtt);
        }
        categories.save(cat3);

        Category cat4 = new Category("HausGartenAccesoires", -1);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            cat4.addAttribute(attColor);
            cat4.addAttribute(attState);
        }
        Category cat4_1 = new Category("Dekoration", 9);
        {
            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.forLocation.inside");
            tempList.add("att.forLocation.outside");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.forLocation", tempList);

            cat4_1.addAttribute(attColor);
            cat4_1.addAttribute(newAtt);
        }
        Category cat4_2 = new Category("GartenPflanzen", 9);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.kind.plant");
            tempList.add("att.kind.decoration");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            cat4_2.addAttribute(attState);
            cat4_2.addAttribute(newAtt);
        }
        Category cat4_3 = new Category("Heimwerken", 9);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.diy.tools");
            tempList.add("att.diy.material");
            tempList.add("att.diy.instructions");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            cat4_3.addAttribute(attState);
            cat4_3.addAttribute(newAtt);
        }
        Category cat4_4 = new Category("BadKücheEsszimmer", 9);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.bathKitchenDining.furnite");
            tempList.add("att.bathKitchenDining.cutlery");
            tempList.add("att.bathKitchenDining.toiletries");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            cat4_4.addAttribute(attState);
            cat4_4.addAttribute(newAtt);
        }
        Category cat4_5 = new Category("Wohnzimmer", 9);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            cat4_5.addAttribute(attColor);
            cat4_5.addAttribute(attState);
        }
        categories.save(cat4);
        categories.save(cat4_1);
        categories.save(cat4_2);
        categories.save(cat4_3);
        categories.save(cat4_4);
        categories.save(cat4_5);


        Category cat5 = new Category("KleidungModeBeauty", -1);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            cat5.addAttribute(attColor);
            cat5.addAttribute(attState);
        }
        Category cat5_1 = new Category("Damenbekleidung", 15);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.size.xs");
            tempList.add("att.size.s");
            tempList.add("att.size.m");
            tempList.add("att.size.l");
            tempList.add("att.size.xl");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.size", tempList);

            LinkedList<String> tempList2 = new LinkedList<String>();
            tempList2.add("att.womenClothing.upperBody");
            tempList2.add("att.womenClothing.pantsSkirts");
            tempList2.add("att.womenClothing.underwear");
            tempList2.add("att.other");
            Attribute newAtt2 = new Attribute("att.kind", tempList2);

            cat5_1.addAttribute(attColor);
            cat5_1.addAttribute(attState);
            cat5_1.addAttribute(newAtt);
            cat5_1.addAttribute(newAtt2);
        }
        Category cat5_2 = new Category("Damenschuhe", 15);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.shoeSize.30");
            tempList.add("att.shoeSize.31");
            tempList.add("att.shoeSize.32");
            tempList.add("att.shoeSize.33");
            tempList.add("att.shoeSize.34");
            tempList.add("att.shoeSize.35");
            tempList.add("att.shoeSize.36");
            tempList.add("att.shoeSize.37");
            tempList.add("att.shoeSize.38");
            tempList.add("att.shoeSize.39");
            tempList.add("att.shoeSize.40");
            tempList.add("att.shoeSize.41");
            tempList.add("att.shoeSize.42");
            tempList.add("att.shoeSize.43");
            tempList.add("att.shoeSize.44");
            tempList.add("att.shoeSize.45");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.shoeSize", tempList);

            cat5_2.addAttribute(attColor);
            cat5_2.addAttribute(attState);
            cat5_2.addAttribute(newAtt);
        }
        Category cat5_3 = new Category("Herrenbekleidung", 15);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.size.xs");
            tempList.add("att.size.s");
            tempList.add("att.size.m");
            tempList.add("att.size.l");
            tempList.add("att.size.xl");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.size", tempList);

            LinkedList<String> tempList2 = new LinkedList<String>();
            tempList2.add("att.menClothing.upperBody");
            tempList2.add("att.menClothing.pants");
            tempList2.add("att.menClothing.underwear");
            tempList2.add("att.other");
            Attribute newAtt2 = new Attribute("att.kind", tempList2);

            cat5_3.addAttribute(attColor);
            cat5_3.addAttribute(attState);
            cat5_3.addAttribute(newAtt);
            cat5_3.addAttribute(newAtt2);
        }
        Category cat5_4 = new Category("Herrenschuhe", 15);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.shoeSize.30");
            tempList.add("att.shoeSize.31");
            tempList.add("att.shoeSize.32");
            tempList.add("att.shoeSize.33");
            tempList.add("att.shoeSize.34");
            tempList.add("att.shoeSize.35");
            tempList.add("att.shoeSize.36");
            tempList.add("att.shoeSize.37");
            tempList.add("att.shoeSize.38");
            tempList.add("att.shoeSize.39");
            tempList.add("att.shoeSize.40");
            tempList.add("att.shoeSize.41");
            tempList.add("att.shoeSize.42");
            tempList.add("att.shoeSize.43");
            tempList.add("att.shoeSize.44");
            tempList.add("att.shoeSize.45");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.shoeSize", tempList);

            cat5_4.addAttribute(attColor);
            cat5_4.addAttribute(attState);
            cat5_4.addAttribute(newAtt);
        }
        Category cat5_5 = new Category("SchmuckAccessoires", 15);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            cat5_5.addAttribute(attColor);
            cat5_5.addAttribute(attState);
        }
        Category cat5_6 = new Category("Taschen", 15);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tagsColor = new LinkedList<String>();
            tagsColor.add("att.color.blue");
            tagsColor.add("att.color.red");
            tagsColor.add("att.color.green");
            tagsColor.add("att.color.black");
            tagsColor.add("att.color.yellow");
            tagsColor.add("att.other");
            Attribute attColor = new Attribute("att.color", tagsColor);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.forGender.men");
            tempList.add("att.forGender.women");
            tempList.add("att.forGender.both");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.forGender", tempList);

            cat5_6.addAttribute(attColor);
            cat5_6.addAttribute(attState);
            cat5_6.addAttribute(newAtt);
        }
        categories.save(cat5);
        categories.save(cat5_1);
        categories.save(cat5_2);
        categories.save(cat5_3);
        categories.save(cat5_4);
        categories.save(cat5_5);
        categories.save(cat5_6);

        Category cat6 = new Category("MultimediaElektronik", -1);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            cat6.addAttribute(attState);
        }
        Category cat6_1 = new Category("AudioHifi", 22);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.audioHifi.device");
            tempList.add("att.audioHifi.cdDvd");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            cat6_1.addAttribute(attState);
            cat6_1.addAttribute(newAtt);
        }
        Category cat6_2 = new Category("Telefon", 22);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.phone.cellphone");
            tempList.add("att.phone.tablet");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            cat6_2.addAttribute(attState);
            cat6_2.addAttribute(newAtt);
        }
        Category cat6_3 = new Category("Foto", 22);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.photo.device");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            cat6_3.addAttribute(attState);
            cat6_3.addAttribute(newAtt);
        }
        Category cat6_4 = new Category("TV", 22);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.tv.device");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            cat6_4.addAttribute(attState);
            cat6_4.addAttribute(newAtt);
        }
        categories.save(cat6);
        categories.save(cat6_1);
        categories.save(cat6_2);
        categories.save(cat6_3);
        categories.save(cat6_4);

        Category cat7 = new Category("MusikFilmBücher", -1);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            cat6_2.addAttribute(attState);
        }
        Category cat7_1 = new Category("Bücher", 27);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.book.book");
            tempList.add("att.book.lexicon");
            tempList.add("att.book.dictionary");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            LinkedList<String> tempList2 = new LinkedList<String>();
            tempList2.add("att.book.content.education");
            tempList2.add("att.book.content.entertainment");
            tempList2.add("att.book.content.thriller");
            tempList2.add("att.book.content.novel");
            tempList2.add("att.other");
            Attribute newAtt2 = new Attribute("att.book.content", tempList2);

            LinkedList<String> tempList3 = new LinkedList<String>();
            tempList3.add("att.forAge.adults");
            tempList3.add("att.forAge.children");
            tempList3.add("att.forAge.both");
            tempList3.add("att.other");
            Attribute newAtt3 = new Attribute("att.forAge", tempList3);

            cat7_1.addAttribute(attState);
            cat7_1.addAttribute(newAtt);
            cat7_1.addAttribute(newAtt2);
            cat7_1.addAttribute(newAtt3);
        }
        Category cat7_2 = new Category("Comics", 27);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            LinkedList<String> tempList3 = new LinkedList<String>();
            tempList3.add("att.forAge.adults");
            tempList3.add("att.forAge.children");
            tempList3.add("att.forAge.both");
            tempList3.add("att.other");
            Attribute newAtt = new Attribute("att.forAge", tempList3);

            cat7_1.addAttribute(attState);
            cat7_1.addAttribute(newAtt);
        }
        Category cat7_3 = new Category("Fachliteratur", 27);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            cat7_3.addAttribute(attState);
        }
        Category cat7_4 = new Category("Filme", 27);
        {
            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.movies.medium.vhs");
            tempList.add("att.movies.medium.dvd");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.movies.medium", tempList);

            LinkedList<String> tempList2 = new LinkedList<String>();
            tempList2.add("att.movies.content.drama");
            tempList2.add("att.movies.content.thriller");
            tempList2.add("att.movies.content.action");
            tempList2.add("att.movies.content.childrenMovie");
            tempList2.add("att.other");
            Attribute newAtt2 = new Attribute("att.movies.content", tempList2);

            LinkedList<String> tempList3 = new LinkedList<String>();
            tempList3.add("att.forAge.adults");
            tempList3.add("att.forAge.children");
            tempList3.add("att.forAge.both");

            cat7_4.addAttribute(newAtt);
            cat7_4.addAttribute(newAtt2);
        }
        Category cat7_5 = new Category("MusikMusikinstrumente", 27);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            cat7_5.addAttribute(attState);
        }
        Category cat7_6 = new Category("Zeitschriften", 27);
        {
            LinkedList<String> tags = new LinkedList<String>();
            tags.add("att.magazine.education");
            tags.add("att.magazine.journal");
            tags.add("att.magazine.entertainment");
            Attribute attState = new Attribute("att.kind", tags);

            cat7_5.addAttribute(attState);
        }
        Category cat7_7 = new Category("Verschiedenes", 27);
        {
            LinkedList<String> tagsState = new LinkedList<String>();
            tagsState.add("att.state.new");
            tagsState.add("att.state.used");
            tagsState.add("att.other");
            Attribute attState = new Attribute("att.state", tagsState);

            cat7_7.addAttribute(attState);
        }
        categories.save(cat7);
        categories.save(cat7_1);
        categories.save(cat7_2);
        categories.save(cat7_3);
        categories.save(cat7_4);
        categories.save(cat7_5);
        categories.save(cat7_6);
        categories.save(cat7_7);

        Category cat8 = new Category("UnterrichtKurseAktivitäten", -1);
        {
            LinkedList<String> tempList = new LinkedList<String>();
            tempList.add("att.activity.education");
            tempList.add("att.activity.languageLearning");
            tempList.add("att.activity.cooking");
            tempList.add("att.activity.culture");
            tempList.add("att.activity.dancing");
            tempList.add("att.activity.music");
            tempList.add("att.activity.cityTour");
            tempList.add("att.other");
            Attribute newAtt = new Attribute("att.kind", tempList);

            cat8.addAttribute(newAtt);
        }
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

    /**
     * initialisation fo goods
     *
     * @param userRepository
     */
    public void initializeGoods(UserRepository userRepository) {
    	 Location ort = new Location("Michelangelostraße 11 01217 Dresden");
         ort = ort.GetCoordinates(ort);


         Article g1 = new Article("Herrenrad", "Gutes Fahrrad Radgröße 30", "Dresden", "Michelangelostraße 11", 1, "01217", userRepository.findOne((long) 1), "article");
         g1.setLatitude(ort.getLatitude());
         g1.setLongitude(ort.getLongitude());
        
         Article g2 = new Article("Frauenrad", "Gutes Fahrrad Radgröße 30", "Dresden", "Michelangelostraß 11", 1, "01217", userRepository.findOne((long) 1), "article");
         g2.setLatitude(ort.getLatitude());
         g2.setLongitude(ort.getLongitude());
         
         ort = ort.GetCoordinates(new Location("Bergstraße 1 Dresden"));
         Article g3 = new Article("Mütze", "Babymütze nur einmal getragen", "Dresden", "Bergstraße 1", 3, "01217", userRepository.findOne((long) 1), "article");
         g3.setLatitude(ort.getLatitude());
         g3.setLongitude(ort.getLongitude());
         
        
         Article g4 = new Article("Kinderwagen", "alter Kinderwagen aus der DDR", "Dresden", "Bergstraße 1", 4, "01217", userRepository.findOne((long) 1), "article");
         g4.setLatitude(ort.getLatitude());
         g4.setLongitude(ort.getLongitude());
         
         ort = ort.GetCoordinates(new Location("Berliner Straße 1 Leipzig"));
         Article g5 = new Article("Kinderbett", "altes Kinderbett aus der DDR", "Leipzig", "Berliner Straße 1", 6, "XXXXX", userRepository.findOne((long) 1), "article");
         g5.setLatitude(ort.getLatitude());
         g5.setLongitude(ort.getLongitude());
         
         Article g6 = new Article("Angel", "Angel mit allem Zubehör", "Leipzig", "Berliner Straße 1", 8, "XXXXX", userRepository.findOne((long) 1), "article");
         g6.setLatitude(ort.getLatitude());
         g6.setLongitude(ort.getLongitude());
         
         
        
          
         goodREPO.save(g1);
         goodREPO.save(g2);
         goodREPO.save(g3);
         goodREPO.save(g4);
         goodREPO.save(g5);
         goodREPO.save(g6);
//         goodREPO.save(g7);
//         goodREPO.save(g8);
//         goodREPO.save(g9);
//         goodREPO.save(g10);
//         goodREPO.save(g11);
//         goodREPO.save(g12);
//         goodREPO.save(g13);
    }

    /**
     * initialisation of languages
     */
    public void initializeLanguages() {
    	//1. browser language must me ISO 639 conform [first 2 letters of variable (e.g. german) MUST equal the two first two letters of iso]
        //2. every attribute of Language MUST be set, otherwise null pointe exception
    	
    	final Language german = new Language();

        german.setBrowserLanguage("de");
        german.setRoleError("Es wurde keine Rolle ausgewählt.");
        german.setNameError("Der Name muss zwischen 2 und 30 Zeichen lang sein.");
        german.setCountryError("Es wurde keine Herkunft gewählt");
        german.setUsernameError("Der Benutzername muss zwischen 6 und 30 Zeichen lang sein.");
        german.setEmailError("Die eingegebene E-Mail-Adresse hat kein zugelassenes Format.");
        german.setEmailUsed("Die eingegebene E-Mail-Adresse wird bereits verwendet.");
        german.setPasswordError("Das Passwort muss mindestens 8 Zeichen lang sein und muss mindestens eine Zahl, einen Klein- und einen Großbuchstaben beinhalten.");
        german.setPasswordConfirmError("Die Passwörter stimmen nicht überein.");
        german.setCityError("Geben Sie einen Stadtnamen ein");
        german.setZipError("Die Postleitzahl muss aus exakt 5 Ziffern bestehen.");
        german.setStreetError("Geben Sie einen Straßennamen ein. \n(Bsp.: Teststr. 1b, Baumweg 12, etc.)");
        german.setTitleError("Geben Sie ihrem Angebot einen Titel.");
        german.setKindError("Wählen Sie die Art des Angebots.");
        german.setOldPwError("Das alte Passwort wurde falsch eingegeben.");
        german.setRegistrationConfirm("Registrierung erfolgreich. Zur Bestätigung der Registrierung wurde Ihnen eine EMail geschickt.");
        german.setUsernameUsedError("Der Username ist bereits vergeben.");
        german.setEmailConfirm("Zum Bestätigen der Änderung Ihrer EMailadresse wird eine EMail an Ihre alte Adresse geschickt.");
        german.setRegistrationEmailTopic("Refugee-App: Registrierung");
        german.setRegistrationEmail("Zum Registrieren Ihres Accounts klicken Sie auf den Link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s \n\n Lokal: localhost:8080/validate?id=%s");
        german.setDeleteEmailTopic("Refugee-App: Account deaktivieren");
        german.setDeleteEmail("Zum Deaktivieren Ihres Accounts klicken Sie auf den Link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
        german.setChangeEmailTopic("Refugee-App: EMail ändern");
        german.setChangeEmail("Zum Ändern Ihrer Mailadresse klicken Sie auf den Link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
        german.setResetPwTopic("Refugee-App: Passwort zurücksetzen");
        german.setResetPw("Zum Zurücksetzen ihres Passworts klicken Sie auf den Link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s \n\n Lokal: localhost:8080/validate?id=%s");
        german.setDeleteUserPopup("Eine Email wurde an Ihr Postfach gesendet. Bitte folgen Sie den dort beschriebenen Schritten um Ihren Useraccount zu deaktivieren.");
        german.setDateError("Bitte wählen Sie Datum und Uhrzeit für die Aktivität indem Sie auf das Eingabefeld klicken.");
        german.setDeleteChat("Wollen Sie das gesamte Gespräch wirklich löschen? Die bisher in diesem Gespräch geschriebenen Nachrichten werden unwiderruflich für Sie und Ihren Gesprächspartner gelöscht");
        german.setResetConfirm("Bestätigen Sie das Zurücksetzen Ihres Passworts durch den Link der an die angegebene Email-Adresse gesendet wurde.");
        german.setNoAccountError("Zu der angegeben EMail Adresse wurde kein Account gefunden.");
        german.setRecaptchaError("Bitte das recaptcha validieren bzw. JavaScript aktivieren.");
        	
        languageRepository.save(german);


        final Language english = new Language();

        english.setBrowserLanguage("en");
        english.setRoleError("You didn't chose a role.");
        english.setNameError("Your name must be between 2 and 30 characters long.");
        english.setCountryError("You didn't chose your country.");
        english.setUsernameError("The username must be between 6 and 30 characters long.");
        english.setEmailError("Your email has a wrong format.");
        english.setEmailUsed("The entered email has already been used");
        english.setPasswordError("Your password must be at least 8 characters and consist of at least one number, one capital and one lowercase letter.");
        english.setPasswordConfirmError("Passwords do not match.");
        english.setCityError("Enter a city");
        english.setZipError("Your zip code must consist of exactly five digits.");
        english.setStreetError("Enter a street.\n(e.g.: Teststr. 1b, Baumweg 12, etc.)");
        english.setTitleError("Give your offer a title.");
        english.setKindError("Choose your offers kind.");
        english.setOldPwError("Your old password was incorrect.");
        english.setRegistrationConfirm("Registration successful. An email for confirmation has been sent.");
        english.setUsernameUsedError("The username is already taken.");
        english.setEmailConfirm("To confirm your new email address, an email has been sent to your former email address");
        english.setRegistrationEmailTopic("Refugee-App: Registration");
        english.setRegistrationEmail("To verify your registration click the following link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s \n\n Lokal: localhost:8080/validate?id=%s");
        english.setDeleteEmailTopic("Refugee-App: Deactivate your Account");
        english.setDeleteEmail("To deactivate your account click the following link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
        english.setChangeEmailTopic("Refugee-App: Email change");
        english.setChangeEmail("To change your email click the following link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
        english.setResetPwTopic("Refugee-App: Reset password");
        english.setResetPw("To reset your password click the following link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s \n\n Lokal: localhost:8080/validate?id=%s");
        english.setDeleteUserPopup("An email has been sent your mailbox. Please follow the instructions in there to confirm the deletion of your user account.");
        english.setDateError("Please choose date and time for the activity by clicking the input field.");
        english.setDeleteChat("Do you really want to delete this chat conversation completely? All in this conversation sent and received messages will be deleted for you and your chat partner");
        english.setResetConfirm("Please confirm that you really want to reset your password.");
        english.setNoAccountError("No account found for this email.");
        english.setRecaptchaError("Please verify the recaptcha or activate JavaScript.");

        languageRepository.save(english);

        final Language arab = new Language();

        //TODO: Arabisch übersetzen
        arab.setBrowserLanguage("ar");
        arab.setRoleError("لم يتم تحديد الدور");
        arab.setNameError("يجب أن يترواح طول الاسم بين حرفين إلى 30 حرفًا");
        arab.setCountryError("لم يتم اختيار الدولة");
        arab.setUsernameError("يجب أن يتراوح طول اسم المستخدم بين 6 إلى 30 حرفًا");
        arab.setEmailError("تنسيق البريد الإلكتروني غير صحيح");
        arab.setEmailUsed("تم استخدام عنوان البريد الإلكتروني الذي تم إدخاله من قبل");
        arab.setPasswordError("يجب ألا يقل طول كلمة المرور عن 8 حروف وأن يحتوي على رقم واحد وحرف صغير وحرف كبير من الحروف اللاتينية على الأقل");
        arab.setPasswordConfirmError("كلماتا المرور غير متطابقتين");
        arab.setCityError("أدخل اسم المدينة");
        arab.setZipError("يجب أن يتكون الرمز البريدي من 5 أرقام فقط");
        arab.setStreetError("أدخل اسم الشارع ثم رقم المنزل (مثال: شارع المحطة 1ب، شارع المدينة 12، الخ)");
        arab.setTitleError("ضع  عنوانًا للعرض");
        arab.setKindError("اختر نوع العرض");
        arab.setOldPwError("كلمة المرور القديمة غير صحيحة");
        arab.setRegistrationConfirm("تم التسجيل بنجاح. ستجد رسالة إلكترونية في بريدك لتأكيد تسجيلك");
        arab.setUsernameUsedError("اسم المستخدم موجود أصلًا");
        arab.setEmailConfirm("تم إرسال رسالة إلى عناون بريدك الإلكتروني السابق لتأكيد عنوان بريدك الإلكتروني الجديدة");
        arab.setRegistrationEmailTopic("Refugee-App: التسجيل");
        arab.setRegistrationEmail("للتحقق من التسجيل، اضغط على الرابط التالي:\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s \n\n Lokal: localhost:8080/validate?id=%s");
        arab.setDeleteEmailTopic("Refugee-App: تعطيل الحساب");
        arab.setDeleteEmail("لتعطيل حسابك، اضغط على الرابط التالي: \n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
        arab.setChangeEmailTopic("Refugee-App: تغيير البريد الإلكتروني");
        arab.setChangeEmail("لتغيير عنوان البريد الإلكتروني، اضغط على العنوان التالي: \n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
        arab.setResetPwTopic("Refugee-App: إعادة تعيين كلمة السر");
        arab.setResetPw("لإعادة تعيين كلمة السر، اضغط على الرابط التالي: \n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s \n\n Lokal: localhost:8080/validate?id=%s");
        arab.setDeleteUserPopup("تم إرسال رسالة إلى بريدك الإلكتروني. يرجى اتباع التعليمات المكتوبة لتأكيد حذف حساب المستخدم الخاص بك");
        arab.setDateError("يرجى اختيار تاريخ ووقت النشاط بالنقر على حقل الإدخال");
        arab.setDeleteChat("هل أنت متأكد من رغبتك في حذف هذه المحادثة كليًا؟ سيتم حذف جميع الرسائل الصادرة والواردة لديك ولدى الشخص الذي تتحدث معه.");
        arab.setResetConfirm("هل أنت متأكد من رغبتك في إعادة تعيين كلمة المرور الخاصة بك؟");
        arab.setNoAccountError("لا يوجد حساب مسجل لهذا البريد الإلكتروني");
        arab.setRecaptchaError("يرجى التحقق من نظام ري كابتشا أو تفعيل جافا سكربت");
        languageRepository.save(arab);
    }
}
