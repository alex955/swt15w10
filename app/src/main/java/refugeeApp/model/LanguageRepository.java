package refugeeApp.model;

import org.springframework.data.repository.CrudRepository;
import refugeeApp.model.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    
    /**
     * Find by browser language.
     *
     * @param browserLanguage the browser language
     * @return the language
     */
    Language findByBrowserLanguage(String browserLanguage);
}
