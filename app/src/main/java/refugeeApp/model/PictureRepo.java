package refugeeApp.model;

/**
 * @author Alexander Shulga
 */

import org.springframework.data.repository.CrudRepository;

public interface PictureRepo extends CrudRepository<Picture, Long>{
	Picture findById(long id);
}
