package kickstart.model;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface Categories extends Repository<Category, Long> {
	void delete(Long id);
	Category save(Category entry);
	Optional<Category> findOne(Long id);
	Iterable<Category> findAll();
	int count();
}
