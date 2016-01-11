package refugeeApp.model;


import org.springframework.data.repository.CrudRepository;

/**
 * The Interface SettingsRepository.
 */
public interface SettingsRepository extends CrudRepository<Setting, String> {
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)
     */
    public Setting findOne(String key);
}
