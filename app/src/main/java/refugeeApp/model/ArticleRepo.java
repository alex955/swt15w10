package refugeeApp.model;


import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface ArticleRepo extends CrudRepository<Article, Long> {
	
 
	
 public void delete(Long id);
 
 public long count();
 
// public Good findOne(long id);
 
 public List<Article> findByLocation(String location);
 
 public List<Article> findAll();
 
 public List<Article> findByCreator(User creator);
 
 public List<Article> findByCategory(long category);







 
}
