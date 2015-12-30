package kickstart.model;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface ArticleRepo extends CrudRepository<Article, Long> {
	
 
	
 public void delete(Long id);
 
 public long count();
 
// public Good findOne(long id);
 
 public List<Article> findByLocation(String location);
 
 public List<Article> findAll();
 
 public Page<List<Article>> findAll(Pageable pageable);
 
 public Page<List<Article>> findByCreator(User creator, Pageable pageable);
 
 public List<Article> findByCategory(long category);







 
}
