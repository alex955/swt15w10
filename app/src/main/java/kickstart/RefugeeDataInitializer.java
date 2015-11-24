package kickstart;

import kickstart.model.*;
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
    private final GoodRepo goodREPO;

    final Role refugee = new Role("ROLE_REFUGEE");
    final Role volunteer = new Role("ROLE_VOLUNTEER");
    final Role admin = new Role("ROLE_ADMIN");

    @Autowired
    public RefugeeDataInitializer(UserAccountManager userAccountManager, UserRepository userRepository, CategoryRepo categories, kickstart.model.GoodRepo goodREPO) {

        Assert.notNull(userAccountManager, "UserManagerAccount must not be null!");
        Assert.notNull(userRepository, "UserRepository must not be null!");
        Assert.notNull(categories, "CategoryRepo must not be null!");
        Assert.notNull(goodREPO, "goodREPO must not be null!");

        this.userAccountManager = userAccountManager;
        this.userRepository = userRepository;
        this.categories = categories;
        this.goodREPO = goodREPO;

    }

    @Override
    public void initialize() {
        initializeCategories();
        initializeGoods();
        initializeActivities();
        initializeUsers(userAccountManager, userRepository);
    }

    public void initializeCategories(){
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

    public void initializeGoods(){
        Good g1 = new Good("in Möbel1", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 1);
        Good g2 = new Good("in Möbel2", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 1);
        Good g3 = new Good("in Badmöbel1", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 2);
        Good g4 = new Good("in Badmöbel2", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 2);
        Good g5 = new Good("in Küchenmöbel", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 3);
        Good g6 = new Good("in Bücher", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 4);
        Good g7 = new Good("in gute Bücher", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 5);
        Good g8 = new Good("in schlechte Bücher", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 6);
        Good g9 = new Good("in Blablub", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 7);
        Good g10 = new Good("in weapons", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 8);
        Good g11 = new Good("in gruppenter", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 9);
        Good g12 = new Good("in nochmal möbel", "Beschreibung", "picPath", "photo", "straße", 01067, "ort", "number", 11);

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

    public void initializeUsers(UserAccountManager userAccountManager, UserRepository userRepository){

        if (userAccountManager.findByUsername("admin1").isPresent()) return;

        UserAccount admin1 = userAccountManager.create("admin1", "admin1PW", admin);
        userAccountManager.save(admin1);
        userRepository.save(new User (1, admin1, "Admin", "Erster", "admin@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user1 = userAccountManager.create("user1", "Test1234", refugee);
        userAccountManager.save(user1);
        userRepository.save(new User (2, user1, "1", "User", "user1@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user2 = userAccountManager.create("user2", "Test1234", refugee);
        userAccountManager.save(user2);
        userRepository.save(new User (3, user1, "2", "User", "user2@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user3 = userAccountManager.create("user3", "Test1234", refugee);
        userAccountManager.save(user3);
        userRepository.save(new User (4, user1, "3", "User", "user3@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user4 = userAccountManager.create("user4", "Test1234", volunteer);
        userAccountManager.save(user4);
        userRepository.save(new User (5, user1, "4", "User", "user4@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user5 = userAccountManager.create("user5", "Test1234", volunteer);
        userAccountManager.save(user5);
        userRepository.save(new User (6, user1, "5", "User", "user5@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

        UserAccount user6 = userAccountManager.create("user6", "Test1234", volunteer);
        userAccountManager.save(user6);
        userRepository.save(new User (7, user1, "6", "User", "user6@test.de", "Stadt", "01234", "Straße", "1", "", "german", "english", "arab"));

    }


}
