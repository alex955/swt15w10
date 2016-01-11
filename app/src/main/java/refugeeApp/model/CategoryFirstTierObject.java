package refugeeApp.model;

import java.util.LinkedList;

/**
 * The Class CategoryFirstTierObject.
 */
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
	 * Gets the cat.
	 *
	 * @return the cat
	 */
	public Category getCat() {
		return cat;
	}

	/**
	 * Sets the cat.
	 *
	 * @param cat the new cat
	 */
	public void setCat(Category cat) {
		this.cat = cat;
	}

	/**
	 * Gets the subcats.
	 *
	 * @return the subcats
	 */
	public LinkedList<Category> getSubcats() {
		return subcats;
	}

	/**
	 * Sets the subcats.
	 *
	 * @param subcats the new subcats
	 */
	public void setSubcats(LinkedList<Category> subcats) {
		this.subcats = subcats;
	}

	/**
	 * Checks if is subdivided.
	 *
	 * @return true, if is subdivided
	 */
	public boolean isSubdivided() {
		return subdivided;
	}

	/**
	 * Sets the subdivided.
	 *
	 * @param subdivided the new subdivided
	 */
	public void setSubdivided(boolean subdivided) {
		this.subdivided = subdivided;
	}
}
