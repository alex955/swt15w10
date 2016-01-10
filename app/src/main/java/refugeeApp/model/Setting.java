package refugeeApp.model;

/**
 * you can create and save settings in the dataInitializer
 */

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class Setting {
   
	@Id private String key;
    private String stringValue;
    private int intValue;

    private String description;

	public Setting(String key, String stringValue, String description) {
		super();
		this.key = key;
		this.stringValue = stringValue;
		this.description = description;
	}
	
	public Setting(String key, int intValue, String description) {
		super();
		this.key = key;
		this.intValue = intValue;
		this.description = description;
	}
	
	public Setting() {
		super();
	}
}
