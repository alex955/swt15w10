package kickstart.model;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vincenz on 27.10.15.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByHashcode(int hashcode);

}
