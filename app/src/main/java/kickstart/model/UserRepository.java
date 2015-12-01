package kickstart.model;


import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Vincenz on 27.10.15.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserAccount(UserAccount userAccount);
    User findByHashcode(int hashcode);
}
