package refugeeApp.model;

import org.springframework.data.repository.CrudRepository;
import refugeeApp.model.Language;

/**
 * Created by Vincenz on 04.01.16.
 */
public interface LanguageRepository extends CrudRepository<Language, Long> {
    Language findByBrowserLanguage(String browserLanguage);
}