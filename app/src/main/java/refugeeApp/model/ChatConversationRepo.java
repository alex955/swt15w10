package refugeeApp.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * The Interface ChatConversationRepo.
 */
public interface ChatConversationRepo extends CrudRepository<ChatConversation, Long> {
	
	/**
	 * Find by from id.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<ChatConversation> findByFromId(long id);
	
	/**
	 * Find by to id.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<ChatConversation> findByToId(long id);
}
