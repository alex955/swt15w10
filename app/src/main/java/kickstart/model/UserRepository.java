package kickstart.model;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountIdentifier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Vincenz on 27.10.15.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);
    User findByHashcode(int hashcode);
    User findById(long id);
    List<User> findAll();
}
