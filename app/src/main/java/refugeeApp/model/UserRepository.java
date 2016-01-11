package refugeeApp.model;


import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Long> {
    
    /**
     * Find by user account.
     *
     * @param userAccount the user account
     * @return the user
     */
    User findByUserAccount(UserAccount userAccount);
    
    /**
     * Find by username.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
    
    /**
     * Find by email.
     *
     * @param email the email
     * @return the user
     */
    User findByEmail(String email);
   
}
