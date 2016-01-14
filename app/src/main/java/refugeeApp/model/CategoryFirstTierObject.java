package refugeeApp.model;

import lombok.Data;

import java.util.LinkedList;

/**
 * The Class CategoryFirstTierObject.
 */
@Data
public class CategoryFirstTierObject {
	
	/** The cat. */
	private Category cat;
	
	/** The subcats. */
	private LinkedList<Category> subcats;
	
	/** The subdivided. */
	private boolean subdivided;
	
	/**
	 * Instantiates a new category first tier object.
	 *
	 * @param cat the cat
	 * @param subcats the subcats
	 */
	public CategoryFirstTierObject(Category cat, LinkedList<Category> subcats){
		this.setCat(cat);
		this.setSubcats(subcats);
	}

	/**
	 * Checks if is subdivided.
	 *
	 * @return true, if is subdivided
	 */
	public boolean isSubdivided() {
		return subdivided;
	}
}
