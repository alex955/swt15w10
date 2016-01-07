package refugeeApp.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ChatConversationRepo extends CrudRepository<ChatConversation, Long> {
	List<ChatConversation> findByFromId(long id);
	List<ChatConversation> findByToId(long id);
}
