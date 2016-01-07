package refugeeApp.model;

/**
 * @author Alexander Shulga
 */

import org.springframework.data.repository.CrudRepository;

public interface SettingsRepository extends CrudRepository<Setting, String> {
    public Setting findOne(String key);
}
