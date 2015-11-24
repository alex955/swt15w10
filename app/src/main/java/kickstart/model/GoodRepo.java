package kickstart.model;


import java.util.List;
import org.springframework.data.repository.CrudRepository;




public interface GoodRepo extends CrudRepository<Good, Long> {
	
 
	
 public void delete(Long id);
 
 public long count();
 
// public Good findOne(long id);
 
 public List<Good> findByname(String name);
 
 public List<Good> findByLocation(String location);
 
 public List<Good> findAll();
 
 public List<Good> findByCategory(long category);







 
}
