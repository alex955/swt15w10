package kickstart.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vincenz on 11.12.15.
 */
public interface UserSettingsRepository extends CrudRepository <UserSettings, Long> {
    UserSettings findByUserId(long userId);
}
