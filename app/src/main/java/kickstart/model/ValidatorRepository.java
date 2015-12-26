package kickstart.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vincenz on 07.12.15.
 */
public interface ValidatorRepository extends CrudRepository<Validator, Long> {
    Validator findByToken(String token);
}