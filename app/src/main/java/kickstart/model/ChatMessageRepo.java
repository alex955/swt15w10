package kickstart.model;

import org.springframework.data.repository.CrudRepository;

import kickstart.utilities.ChatMessage;

public interface ChatMessageRepo extends CrudRepository<ChatMessage, Long> {

}
