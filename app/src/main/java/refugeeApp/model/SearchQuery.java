package refugeeApp.model;

import lombok.Data;

/**
 * The Class SearchQuery.
 */
@Data
public class SearchQuery {
	
	/** The query. */
	private String query;
	
	/** The location. */
	private String location;
	
	/** The distance. */
	private int distance;
	
	/** The category. */
	private long category;

}
