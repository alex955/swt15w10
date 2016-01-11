package refugeeApp;

import java.io.IOException;

import java.util.LinkedList;


import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import refugeeApp.model.*;

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

    final Role refugee = new Role("ROLE_REFUGEE");
    final Role volunteer = new Role("ROLE_VOLUNTEER");
    final Role admin = new Role("ROLE_ADMIN");

    /**
     * autowiring constructor
     * @param userAccountManager
     * @param userRepository
     * @param categories
     * @param goodREPO
     * @param chatRepo
     * @param msgRepo
     * @param settingsRepo
     * @param userSettingsRepository
     * @param validatorRepository
     * @param languageRepository
     */
    @Autowired
    public RefugeeDataInitializer(UserAccountManager userAccountManager, UserRepository userRepository, CategoryRepo categories, refugeeApp.model.ArticleRepo goodREPO, ChatConversationRepo chatRepo, ChatMessageRepo msgRepo, SettingsRepository settingsRepo, UserSettingsRepository userSettingsRepository, ValidatorRepository validatorRepository, LanguageRepository languageRepository) {

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
        initializeGoods(userAccountManager, userRepository);
    }
    
    /**
     * general settings of the application
     * @throws IOException 
     */
    public void Settings(){
    	
    	//Image settings
    	
    	//Image width
    	settingsRepo.save(new Setting("imageWidth", 400, ""));
    	//Image height
    	settingsRepo.save(new Setting("imageHeight", 400, ""));
    	
    	//Alexander
    	//settingsRepo.save(new Setting("noUploadedPicturePath", "/Users/Alexander/Documents/Studium/swt15w10/app/src/main/resources/static/resources/img/keinbild.png.png", "The Path to the Application and the img folder in resources, where a standard picture is for the uploaded offers without one"));
    	
    	//Sascha
    	settingsRepo.save(new Setting("noUploadedPicturePath", "/Users/sasch/Documents/swt15w10/app/src/main/resources/static/resources/img/keinbild.png.png", "The Path to the Application and the img folder in resources, where a standard picture is for the uploaded offers without one"));    	
    	
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
     * @param userAccountManager
     * @param userRepository
     */
    public void initializeUsers(UserAccountManager userAccountManager, UserRepository userRepository){
    	
        if (userAccountManager.findByUsername("admin1").isPresent()) return;

        UserAccount admin1 = userAccountManager.create("admin1", "admin1", admin);
        userAccountManager.save(admin1);
        userRepository.save(new User (1, admin1, "Admin", "Erster", "DataInitializer", "test1@test.test", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user1 = userAccountManager.create("user1", "user1", refugee);
        user1.setEmail("ref@gmx.de");
        userAccountManager.save(user1);
        userRepository.save(new User (2, user1, "1", "User", "DataInitializer", "ref@gmx.de", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user2 = userAccountManager.create("user2", "user2", refugee);
        userAccountManager.save(user2);
        userRepository.save(new User (3, user2, "2", "User", "DataInitializer", "test2@test.test", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user3 = userAccountManager.create("user3", "user3", refugee);
        userAccountManager.save(user3);
        userRepository.save(new User (4, user3, "3", "User", "DataInitializer", "test3@test.test", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user4 = userAccountManager.create("user4", "user4", volunteer);
        userAccountManager.save(user4);
        userRepository.save(new User (5, user4, "4", "User", "DataInitializer", "test4@test.test", "Stadt", "01234", "Straße 1", "", "german", "english", "arab"));

        UserAccount user5 = userAccountManager.create("user5", "user5", volunteer);
        userAccountManager.save(user5);
        userRepository.save(new User (6, user5, "5", "User", "DataInitializer", "test5@test.test", "Stadt", "01234", "Straße 1",  "", "german", "english", "arab"));

        UserAccount user6 = userAccountManager.create("user6", "user6", volunteer);
        userAccountManager.save(user6);
        userRepository.save(new User (7, user6, "6", "User", "DataInitializer", "test6@test.test", "Stadt", "01234", "Straße 1",  "", "german", "english", "arab"));

    }

    /**
     * terribly hardcoded initialisation of categories
     */
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
    	{
    	LinkedList<String> tagsState = new LinkedList<String>();
    	tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
    	Attribute attState = new Attribute("att.state", tagsState);
    	
    	LinkedList<String> tagsColor = new LinkedList<String>();
    	tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
    	Attribute attColor = new Attribute("att.color", tagsColor);
        
        cat1.addAttribute(attState);
        cat1.addAttribute(attColor);
    	}
        categories.save(cat1);
        
        Category cat2 = new Category("FamilieKindWohnung", -1); 
        {
        	LinkedList<String> tagsState = new LinkedList<String>();
        	tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        	Attribute attState = new Attribute("att.state", tagsState);
        	
        	LinkedList<String> tagsColor = new LinkedList<String>();
        	tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        	Attribute attColor = new Attribute("att.color", tagsColor);
        	
        	cat2.addAttribute(attColor);
            cat2.addAttribute(attState);
        }
        
        		Category cat2_1 = new Category("Babybekleidung", 2);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
                	tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
                	Attribute attState = new Attribute("att.state", tagsState);
                	
                	LinkedList<String> tagsColor = new LinkedList<String>();
                	tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
                	Attribute attColor = new Attribute("att.color", tagsColor);
        			
        			cat2_1.addAttribute(attColor);
            		cat2_1.addAttribute(attState);
        		}
        		
        		
        		Category cat2_2 = new Category("Kinderwagen", 2);
        		{
        			LinkedList<String> tempList = new LinkedList<String>();
            		tempList.add("att.stroller.for.1child");tempList.add("att.stroller.for.2childs");tempList.add("att.other");
                	Attribute attStroller = new Attribute("att.stroller.for", tempList);
                	
                	LinkedList<String> tagsState = new LinkedList<String>();
                	tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
                	Attribute attState = new Attribute("att.state", tagsState);
                	
                	LinkedList<String> tagsColor = new LinkedList<String>();
                	tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
                	Attribute attColor = new Attribute("att.color", tagsColor);
                	
                	cat2_2.addAttribute(attColor);
                	cat2_2.addAttribute(attState);
                	cat2_2.addAttribute(attStroller);
        		}
        		
         	
        		Category cat2_3 = new Category("Kinderbekleidung", 2);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.childrenClothing.for.baby");tempList.add("att.childrenClothing.for.toddler");tempList.add("att.childrenClothing.for.schoolchild");
        			Attribute newAtt = new Attribute("att.childrenClothing.for", tempList);

        			cat2_3.addAttribute(attColor);
        			cat2_3.addAttribute(attState);
        			cat2_3.addAttribute(newAtt);
        		}
            	
        		Category cat2_4 = new Category("Möbel", 2);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.furniture.for.bath");tempList.add("att.furniture.for.kitchen");tempList.add("att.furniture.for.livingRoom");tempList.add("att.furniture.for.diningRoom");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.furniture.for", tempList);

        			cat2_4.addAttribute(attColor);
        			cat2_4.addAttribute(attState);
        			cat2_4.addAttribute(newAtt);
        		}
        		Category cat2_5 = new Category("SpielzeugSpiele", 2);{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.baby");tempList.add("att.children");tempList.add("att.adult");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.games.for", tempList);

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
        	tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        	Attribute attState = new Attribute("att.state", tagsState);

        	LinkedList<String> tempList = new LinkedList<String>();
        	tempList.add("att.musicArt");tempList.add("att.sports");tempList.add("att.culture");tempList.add("att.entertainment");
        	tempList.add("att.other");
        	Attribute newAtt= new Attribute("att.hobby", tempList);
        	
        	cat3.addAttribute(attState);
        	cat3.addAttribute(newAtt);
        }
        categories.save(cat3);

    	Category cat4 = new Category("HausGartenAccesoires", -1);
    	{
    		LinkedList<String> tagsState = new LinkedList<String>();
    		tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
    		Attribute attState = new Attribute("att.state", tagsState);
    		                	
    		LinkedList<String> tagsColor = new LinkedList<String>();
    		tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
    		Attribute attColor = new Attribute("att.color", tagsColor);

    		cat4.addAttribute(attColor);
    		cat4.addAttribute(attState);
    	}
        		Category cat4_1 = new Category("Dekoration", 9);
        		{
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.forLocation.inside");tempList.add("att.forLocation.outside");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.forLocation", tempList);

        			cat4_1.addAttribute(attColor);
        			cat4_1.addAttribute(newAtt);
        		}
        		Category cat4_2 = new Category("GartenPflanzen", 9);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.kind.plant");tempList.add("att.kind.decoration");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.kind", tempList);

        			cat4_2.addAttribute(attState);
        			cat4_2.addAttribute(newAtt);
        		}
        		Category cat4_3 = new Category("Heimwerken", 9);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.diy.tools");tempList.add("att.diy.material");tempList.add("att.diy.instructions");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.kind", tempList);

        			cat4_3.addAttribute(attState);
        			cat4_3.addAttribute(newAtt);
        		}
        		Category cat4_4 = new Category("BadKücheEsszimmer", 9);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.bathKitchenDining.furnite");tempList.add("att.bathKitchenDining.cutlery");tempList.add("att.bathKitchenDining.toiletries");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.kind", tempList);

        			cat4_4.addAttribute(attState);
        			cat4_4.addAttribute(newAtt);
        		}
        		Category cat4_5 = new Category("Wohnzimmer", 9);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
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
			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
			Attribute attState = new Attribute("att.state", tagsState);
			                	
			LinkedList<String> tagsColor = new LinkedList<String>();
			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
			Attribute attColor = new Attribute("att.color", tagsColor);

			cat5.addAttribute(attColor);
			cat5.addAttribute(attState);
        }
        		Category cat5_1 = new Category("Damenbekleidung", 15);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.size.xs");tempList.add("att.size.s");tempList.add("att.size.m");tempList.add("att.size.l");tempList.add("att.size.xl");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.size", tempList);
        			
        			LinkedList<String> tempList2 = new LinkedList<String>();
        			tempList2.add("att.womenClothing.upperBody");tempList2.add("att.womenClothing.pantsSkirts");tempList2.add("att.womenClothing.underwear");
        			tempList2.add("att.other");
        			Attribute newAtt2= new Attribute("att.kind", tempList2);

        			cat5_1.addAttribute(attColor);
        			cat5_1.addAttribute(attState);
        			cat5_1.addAttribute(newAtt);
        			cat5_1.addAttribute(newAtt2);
        		}
        		Category cat5_2 = new Category("Damenschuhe", 15);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.shoeSize.30");tempList.add("att.shoeSize.31");tempList.add("att.shoeSize.32");tempList.add("att.shoeSize.33");tempList.add("att.shoeSize.34");tempList.add("att.shoeSize.35");tempList.add("att.shoeSize.36");tempList.add("att.shoeSize.37");tempList.add("att.shoeSize.38");tempList.add("att.shoeSize.39");tempList.add("att.shoeSize.40");tempList.add("att.shoeSize.41");tempList.add("att.shoeSize.42");tempList.add("att.shoeSize.43");tempList.add("att.shoeSize.44");tempList.add("att.shoeSize.45");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.shoeSize", tempList);

        			cat5_2.addAttribute(attColor);
        			cat5_2.addAttribute(attState);
        			cat5_2.addAttribute(newAtt);
        		}
        		Category cat5_3 = new Category("Herrenbekleidung", 15);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.size.xs");tempList.add("att.size.s");tempList.add("att.size.m");tempList.add("att.size.l");tempList.add("att.size.xl");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.size", tempList);
        			
        			LinkedList<String> tempList2 = new LinkedList<String>();
        			tempList2.add("att.menClothing.upperBody");tempList2.add("att.menClothing.pants");tempList2.add("att.menClothing.underwear");
        			tempList2.add("att.other");
        			Attribute newAtt2= new Attribute("att.kind", tempList2);

        			cat5_3.addAttribute(attColor);
        			cat5_3.addAttribute(attState);
        			cat5_3.addAttribute(newAtt);
        			cat5_3.addAttribute(newAtt2);
        		}
        		Category cat5_4 = new Category("Herrenschuhe", 15);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.shoeSize.30");tempList.add("att.shoeSize.31");tempList.add("att.shoeSize.32");tempList.add("att.shoeSize.33");tempList.add("att.shoeSize.34");tempList.add("att.shoeSize.35");tempList.add("att.shoeSize.36");tempList.add("att.shoeSize.37");tempList.add("att.shoeSize.38");tempList.add("att.shoeSize.39");tempList.add("att.shoeSize.40");tempList.add("att.shoeSize.41");tempList.add("att.shoeSize.42");tempList.add("att.shoeSize.43");tempList.add("att.shoeSize.44");tempList.add("att.shoeSize.45");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.shoeSize", tempList);

        			cat5_4.addAttribute(attColor);
        			cat5_4.addAttribute(attState);
        			cat5_4.addAttribute(newAtt);
        		}
        		Category cat5_5 = new Category("SchmuckAccessoires", 15);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			cat5_5.addAttribute(attColor);
        			cat5_5.addAttribute(attState);
        		}
        		Category cat5_6 = new Category("Taschen", 15);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			                	
        			LinkedList<String> tagsColor = new LinkedList<String>();
        			tagsColor.add("att.color.blue");tagsColor.add("att.color.red");tagsColor.add("att.color.green");tagsColor.add("att.color.black");tagsColor.add("att.color.yellow");tagsColor.add("att.other");
        			Attribute attColor = new Attribute("att.color", tagsColor);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.forGender.men");tempList.add("att.forGender.women");tempList.add("att.forGender.both");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.forGender", tempList);

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
        	tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        	Attribute attState = new Attribute("att.state", tagsState);
        	
        	cat6.addAttribute(attState);
        }
        		Category cat6_1 = new Category("AudioHifi", 22);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.audioHifi.device");tempList.add("att.audioHifi.cdDvd");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.kind", tempList);

        			cat6_1.addAttribute(attState);
        			cat6_1.addAttribute(newAtt);
        		}
        		Category cat6_2 = new Category("Telefon", 22);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.phone.cellphone");tempList.add("att.phone.tablet");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.kind", tempList);

        			cat6_2.addAttribute(attState);
        			cat6_2.addAttribute(newAtt);
        		}
        		Category cat6_3 = new Category("Foto", 22);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.photo.device");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.kind", tempList);

        			cat6_3.addAttribute(attState);
        			cat6_3.addAttribute(newAtt);
        		}
        		Category cat6_4 = new Category("TV", 22);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.tv.device");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.kind", tempList);

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
			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
			Attribute attState = new Attribute("att.state", tagsState);

			cat6_2.addAttribute(attState);
        }
        		Category cat7_1 = new Category("Bücher", 27);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.book.book");tempList.add("att.book.lexicon");tempList.add("att.book.dictionary");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.kind", tempList);
        			
        			LinkedList<String> tempList2 = new LinkedList<String>();
        			tempList2.add("att.book.content.education");tempList2.add("att.book.content.entertainment");tempList2.add("att.book.content.thriller");tempList2.add("att.book.content.novel");
        			tempList2.add("att.other");
        			Attribute newAtt2= new Attribute("att.book.content", tempList2);
        			
        			LinkedList<String> tempList3 = new LinkedList<String>();
        			tempList3.add("att.forAge.adults");tempList3.add("att.forAge.children");tempList3.add("att.forAge.both");
        			tempList3.add("att.other");
        			Attribute newAtt3= new Attribute("att.forAge", tempList3);

        			cat7_1.addAttribute(attState);
        			cat7_1.addAttribute(newAtt);
        			cat7_1.addAttribute(newAtt2);
        			cat7_1.addAttribute(newAtt3);
        		}
        		Category cat7_2 = new Category("Comics", 27);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);
        			
        			LinkedList<String> tempList3 = new LinkedList<String>();
        			tempList3.add("att.forAge.adults");tempList3.add("att.forAge.children");tempList3.add("att.forAge.both");
        			tempList3.add("att.other");
        			Attribute newAtt= new Attribute("att.forAge", tempList3);
        			
        			cat7_1.addAttribute(attState);
        			cat7_1.addAttribute(newAtt);
        		}
        		Category cat7_3 = new Category("Fachliteratur", 27);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			cat7_3.addAttribute(attState);
        		}
        		Category cat7_4 = new Category("Filme", 27);
        		{
        			LinkedList<String> tempList = new LinkedList<String>();
        			tempList.add("att.movies.medium.vhs");tempList.add("att.movies.medium.dvd");
        			tempList.add("att.other");
        			Attribute newAtt= new Attribute("att.movies.medium", tempList);
        			
        			LinkedList<String> tempList2 = new LinkedList<String>();
        			tempList2.add("att.movies.content.drama");tempList2.add("att.movies.content.thriller");tempList2.add("att.movies.content.action");tempList2.add("att.movies.content.childrenMovie");
        			tempList2.add("att.other");
        			Attribute newAtt2= new Attribute("att.movies.content", tempList2);
        			
        			LinkedList<String> tempList3 = new LinkedList<String>();
        			tempList3.add("att.forAge.adults");tempList3.add("att.forAge.children");tempList3.add("att.forAge.both");

        			cat7_4.addAttribute(newAtt);
        			cat7_4.addAttribute(newAtt2);
        		}
        		Category cat7_5 = new Category("MusikMusikinstrumente", 27);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
        			Attribute attState = new Attribute("att.state", tagsState);

        			cat7_5.addAttribute(attState);
        		}
        		Category cat7_6 = new Category("Zeitschriften", 27);
        		{
        			LinkedList<String> tags = new LinkedList<String>();
        			tags.add("att.magazine.education");tags.add("att.magazine.journal");tags.add("att.magazine.entertainment");
        			Attribute attState = new Attribute("att.kind", tags);

        			cat7_5.addAttribute(attState);
        		}
        		Category cat7_7 = new Category("Verschiedenes", 27);
        		{
        			LinkedList<String> tagsState = new LinkedList<String>();
        			tagsState.add("att.state.new"); tagsState.add("att.state.used"); tagsState.add("att.other");
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
        	tempList.add("att.activity.education");tempList.add("att.activity.languageLearning");tempList.add("att.activity.cooking");tempList.add("att.activity.culture");tempList.add("att.activity.dancing");tempList.add("att.activity.music");tempList.add("att.activity.cityTour");
        	tempList.add("att.other");
        	Attribute newAtt= new Attribute("att.kind", tempList);
        	
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
     * @param userAccountManager
     * @param userRepository
     */
    public void initializeGoods(UserAccountManager userAccountManager, UserRepository userRepository){
    	LinkedList<String> tags1 = new LinkedList<String>();
        tags1.add("XXL");
    	Attribute att = new Attribute("Größe",tags1);
    	LinkedList<String> tags2 = new LinkedList<String>();
    	tags2.add("Männlich");
    	Attribute att2 = new Attribute("Geschlecht",tags2);
    	
    	Location ort = new Location("Michelangelostraße 11 01217 Dresden");
    	ort = ort.GetCoordinates(ort);
    	
    	
    	Article g1 = new Article("Spiegelschrank", "Dieser Spiegelschrank ist 60 cm breit", "Dresden - Zschernitz", "Bergstraße 5", 2, "01217", userRepository.findOne((long) 1), "article");
    	 g1.addAttribute(att);
         g1.addAttribute(att2);
         g1.setLatitude(ort.getLatitude());
         g1.setLongitude(ort.getLongitude());
    	Article g2 = new Article("Sofa", "Einladender Blickfang! Das stylishe Schlafsofa mit dem zweifarbigen Look lädt zum Entspannen und Träumen ein.", "Dresden - Südvorstadt", "straße", 1,  "01067", userRepository.findOne((long) 2), "article");
    	g2.setLatitude(ort.getLatitude());
        g2.setLongitude(ort.getLongitude());
    	Article g3 = new Article("Stuhl", "Schöner Bürostuhl mit einem Bezug aus hochwertigem Kunstleder, kombiniert mit atmungsaktivem Netzstoff im Rückenausschnitt in Schwarz", "Pirna", "straße", 1,  "01067", userRepository.findOne((long) 2), "article");
    	g3.setLatitude(ort.getLatitude());
        g3.setLongitude(ort.getLongitude());
    	Article g4 = new Article("Spiegel", "Aus Metall mit aufwendigen Verzierungen", "Dresden - Seidnitz", "straße", 2, "01067", userRepository.findOne((long) 3), "article");
    	g4.setLatitude(ort.getLatitude());
        g4.setLongitude(ort.getLongitude());
    	Article g5 = new Article("Messerblock", "EINFACH GUT! Eine rundum gute Entscheidung wenn es etwas preiswerter sein soll und trotzdem zuverlässig und praktisch. TWIN Point überzeugt durch eine scharfe Klinge.", "Ottendorf Orkrilla", "straße", 3, "01067", userRepository.findOne((long) 1), "article");
    	g5.setLatitude(ort.getLatitude());
        g5.setLongitude(ort.getLongitude());
    	Article g6 = new Article("Buch", "bestens erhalten ohne Eselsohren", "Leipzig", "straße", 4, "01067", userRepository.findOne((long) 1), "article");
    	g6.setLatitude(ort.getLatitude());
        g6.setLongitude(ort.getLongitude());
    	Article g7 = new Article("Deutsch für Anfänger", "Gutes Buch zum lernen", "ort", "straße", 5, "01067", userRepository.findOne((long) 1), "article");
    	g7.setLatitude(ort.getLatitude());
        g7.setLongitude(ort.getLongitude());
    	Article g8 = new Article("Harry Potter", "Harry Potter (* 24.12.0 um 12:30 Uhr) ist ein kleiner Zauberer aus England. Harry lernte in Hogwarts das Zauberstabwedeln", "Hoyerswerda", "straße", 6, "01067", userRepository.findOne((long) 2), "article");
    	g8.setLatitude(ort.getLatitude());
        g8.setLongitude(ort.getLongitude());
    	Article g9 = new Article("Das Survival Duo", "Survival-Duo (Mehrzahl: Survival-Quartett) ist eine satirische und oscarprämierte TV-Sendung auf DMAX.", "Dresden - Prohlis", "straße", 7, "01067", userRepository.findOne((long) 1), "article");
    	g9.setLatitude(ort.getLatitude());
    	g9.setLatitude(ort.getLatitude());
      
       
    	Article g10 = new Article("Motorola Razor", "Perfektes Handy. Wirkt sogar als Boomerang", "Dresden - Gorbitz", "straße", 9,  "01067", userRepository.findOne((long) 1), "article");
    	 g10.setLongitude(ort.getLongitude());
         g10.setLongitude(ort.getLongitude());
    	Article g11 = new Article("Jeans", "Beschreibung", "ort", "straße",11,  "01067", userRepository.findOne((long) 3), "article");
    	 g11.setLongitude(ort.getLongitude());
         g11.setLongitude(ort.getLongitude());
    	Article g12 = new Article("Deutschkurs", "Beschreibung", "ort", "straße", 12, "01067", userRepository.findOne((long) 1), "article");
    	g12.setLongitude(ort.getLongitude());
        g12.setLongitude(ort.getLongitude());
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

    /**
     * initialisation of languages
     */
	public void initializeLanguages(){

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
		german.setDeleteEmail("Zum Deaktivieren Ihres Accounts klicken Sie auf den Link.\n\n" +"Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
		german.setChangeEmailTopic("Refugee-App: EMail ändern");
		german.setChangeEmail("Zum Ändern Ihrer Mailadresse klicken Sie auf den Link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
		german.setResetPwTopic("Refugee-App: Passwort zurücksetzen");
		german.setResetPw("Zum Zurücksetzen ihres Passworts klicken Sie auf den Link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s \n\n Lokal: localhost:8080/validate?id=%s");
		german.setDeleteUserPopup("Eine Email wurde an Ihr Postfach gesendet. Bitte folgen Sie den dort beschriebenen Schritten um Ihren Useraccount zu deaktivieren.");
		german.setDateError("Bitte wählen Sie Datum und Uhrzeit für die Aktivität indem Sie auf das Eingabefeld klicken.");
		german.setDeleteChat("Wollen Sie das gesamte Gespräch wirklich löschen? Die bisher in diesem Gespräch geschriebenen Nachrichten werden unwiderruflich für Sie und Ihren Gesprächspartner gelöscht");
		german.setResetConfirm("Bestätigen Sie das Zurücksetzen Ihres Passworts durch den Link der an die angegebene Email-Adresse gesendet wurde.");
		german.setNoAccountError("Zu der angegeben EMail Adresse wurde kein Account gefunden.");

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
		english.setDeleteEmail("To deactivate your account click the following link.\n\n" +"Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
		english.setChangeEmailTopic("Refugee-App: Email change");
		english.setChangeEmail("To change your email click the following link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s\n\n Lokal: localhost:8080/validate?id=%s");
		english.setResetPwTopic("Refugee-App: Reset password");
		english.setResetPw("To reset your password click the following link.\n\n" + "Testserver: http://refugee-app.tk/swt15w10/validate?id=%s \n\n Lokal: localhost:8080/validate?id=%s");
		english.setDeleteUserPopup("An email has been sent your mailbox. Please follow the instructions in there to confirm the deletion of your user account.");
		english.setDateError("Please choose date and time for the activity by clicking the input field.");
		english.setDeleteChat("Do you really want to delete this chat conversation completely? All in this conversation sent and received messages will be deleted for you and your chat partner");
		english.setResetConfirm("RESET CONFIRM");
		english.setNoAccountError("NOACCOUNT ERROR");

		languageRepository.save(english);

		final Language arab = new Language();

		//TODO: Arabisch übersetzen
		arab.setBrowserLanguage("ar");
		arab.setRoleError("");
		arab.setNameError("");
		arab.setCountryError("");
		arab.setUsernameError("");
		arab.setEmailError("");
		arab.setEmailUsed("");
		arab.setPasswordError("");
		arab.setPasswordConfirmError("");
		arab.setCityError("");
		arab.setZipError("");
		arab.setStreetError("");
		arab.setTitleError("");
		arab.setKindError("");
		arab.setOldPwError("");
		arab.setRegistrationConfirm("");
		arab.setUsernameUsedError("");
		arab.setEmailConfirm("");
		arab.setRegistrationEmailTopic("");
		arab.setRegistrationEmail("");
		arab.setDeleteEmailTopic("");
		arab.setDeleteEmail("");
		arab.setChangeEmailTopic("");
		arab.setChangeEmail("");
		arab.setDeleteUserPopup("");
		arab.setDateError("");
		arab.setDeleteChat("");

		languageRepository.save(arab);
	}
}
