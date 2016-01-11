package refugeeApp.model;

import org.springframework.data.repository.CrudRepository;


public interface UserSettingsRepository extends CrudRepository <UserSettings, Long> {
    
    /**
     * Find by user id.
     *
     * @param userId the user id
     * @return the user settings
     */
    UserSettings findByUserId(long userId);
}