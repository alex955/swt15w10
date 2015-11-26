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
    private final ArticleRepo goodREPO;

    final Role refugee = new Role("ROLE_REFUGEE");
    final Role volunteer = new Role("ROLE_VOLUNTEER");
    final Role admin = new Role("ROLE_ADMIN");

    @Autowired
    public RefugeeDataInitializer(UserAccountManager userAccountManager, UserRepository userRepository, CategoryRepo categories, kickstart.model.ArticleRepo goodREPO) {

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
        Article g1 = new Article("in Möbel1", "Beschreibung", "picPath", "ort", "straße", 1, "number", 01067,"25.11.2015");
        Article g2 = new Article("in Möbel2", "Beschreibung", "picPath", "ort", "straße", 1, "number", 01067,"02.11.2015");
        Article g3 = new Article("in Badmöbel1", "Beschreibung", "picPath", "ort", "straße", 2, "number", 01067,"12.11.2015");
        Article g4 = new Article("in Badmöbel2", "Beschreibung", "picPath", "ort", "straße", 2, "number", 01067,"14.11.2015");
        Article g5 = new Article("in Küchenmöbel", "Beschreibung", "picPath", "ort", "straße", 3, "number", 01067,"02.11.2015");
        Article g6 = new Article("in Bücher", "Beschreibung", "picPath", "ort", "straße", 4, "number", 01067,"25.11.2015");
        Article g7 = new Article("in gute Bücher", "Beschreibung", "picPath", "ort", "straße", 5, "number", 01067,"25.11.2015");
        Article g8 = new Article("in schlechte Bücher", "Beschreibung", "picPath", "ort", "straße", 6, "number", 01067,"25.11.2015");
        Article g9 = new Article("in Blablub", "Beschreibung", "picPath", "ort", "straße", 7, "number", 01067,"25.11.2015");
        Article g10 = new Article("in weapons", "Beschreibung", "picPath", "ort", "straße", 8, "number", 01067,"25.11.2015");
        Article g11 = new Article("in gruppenter", "Beschreibung", "picPath", "ort", "straße", 9, "number", 01067,"25.11.2015");
        Article g12 = new Article("in nochmal möbel", "Beschreibung", "picPath", "ort", "straße", 11, "number", 01067,"25.11.2015");

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
