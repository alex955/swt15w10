package refugeeApp.model;

import org.springframework.data.repository.CrudRepository;

public interface ValidatorRepository extends CrudRepository<Validator, Long> {
    
    /**
     * Find by token.
     *
     * @param token the token
     * @return the validator
     */
    Validator findByToken(String token);
}