package refugeeApp.model;

import org.springframework.data.repository.CrudRepository;

/**
 * The Interface PictureRepo.
 */
public interface PictureRepo extends CrudRepository<Picture, Long>{
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the picture
	 */
	Picture findById(long id);
}
