package refugeeApp.model;


import java.util.List;
import org.springframework.data.repository.CrudRepository;


/**
 * The Interface ArticleRepo.
 */
public interface ArticleRepo extends CrudRepository<Article, Long> {
	
 
	
 /* (non-Javadoc)
  * @see org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)
  */
 public void delete(Long id);
 
 /* (non-Javadoc)
  * @see org.springframework.data.repository.CrudRepository#count()
  */
 public long count();
 
// public Good findOne(long id);
 
 /**
 * Find by location.
 *
 * @param location the location
 * @return the list
 */
public List<Article> findByLocation(String location);
 
 /* (non-Javadoc)
  * @see org.springframework.data.repository.CrudRepository#findAll()
  */
 public List<Article> findAll();
 
 /**
  * Find by creator.
  *
  * @param creator the creator
  * @return the list
  */
 public List<Article> findByCreator(User creator);
 
 /**
  * Find by category.
  *
  * @param category the category
  * @return the list
  */
 public List<Article> findByCategory(long category);







 
}
