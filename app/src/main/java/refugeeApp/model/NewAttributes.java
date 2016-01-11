package refugeeApp.model;

import java.util.LinkedList;

/**
 * The Class NewAttributes.
 */
public class NewAttributes {
	
	/** The choosen tags. */
	private LinkedList<String> choosenTags= new LinkedList<String>();

	/**
	 * Gets the choosen tags.
	 *
	 * @return the choosen tags
	 */
	public LinkedList<String> getChoosenTags() {
		return choosenTags;
	}

	/**
	 * Sets the choosen tags.
	 *
	 * @param choosenTags the new choosen tags
	 */
	public void setChoosenTags(LinkedList<String> choosenTags) {
		this.choosenTags = choosenTags;
	}
	
}
