package refugeeApp.model;

/**
 * you can create and save settings in the dataInitializer
 */

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * Gets the description.
 *
 * @return the description
 */
@Getter/**
 * Sets the description.
 *
 * @param description the new description
 */
@Setter
@Entity
public class Setting {
   
	/** The key. */
	@Id private String key;
    
    /** The string value. */
    private String stringValue;
    
    /** The int value. */
    private int intValue;

    /** The description. */
    private String description;

	/**
	 * Instantiates a new setting.
	 *
	 * @param key the key
	 * @param stringValue the string value
	 * @param description the description
	 */
	public Setting(String key, String stringValue, String description) {
		super();
		this.key = key;
		this.stringValue = stringValue;
		this.description = description;
	}
	
	/**
	 * Instantiates a new setting.
	 *
	 * @param key the key
	 * @param intValue the int value
	 * @param description the description
	 */
	public Setting(String key, int intValue, String description) {
		super();
		this.key = key;
		this.intValue = intValue;
		this.description = description;
	}
	
	/**
	 * Instantiates a new setting.
	 */
	public Setting() {
		super();
	}
}
