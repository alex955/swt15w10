package refugeeApp.model;

import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 * The Interface CategoryRepo.
 */
public interface CategoryRepo extends Repository<Category, Long> {
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete(Long id);
	
	/**
	 * Save.
	 *
	 * @param entry the entry
	 * @return the category
	 */
	Category save(Category entry);
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<Category> findOne(Long id);
	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	Iterable<Category> findAll();
	
	/**
	 * Count.
	 *
	 * @return the int
	 */
	int count();
}
