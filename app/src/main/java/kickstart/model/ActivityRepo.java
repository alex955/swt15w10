package kickstart.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ActivityRepo extends CrudRepository<Activity, Long> {

	 public void delete(Long id);
	 
	 public long count();
	 
	 public List<Activity> findByname(String name);
	 
	 public List<Activity> findByLocation(String location);
	 
	 public List<Activity> findAll();
	 
	 public List<Activity> findByCategory(long category);
	 

}
