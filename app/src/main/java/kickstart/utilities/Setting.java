package kickstart.utilities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Setting {
   
	@Id
    private String key;
    @Lob // Falls du große Werte zulassen willst
    private String value;

    private String description;

	public Setting(String key, String value, String description) {
		super();
		this.key = key;
		this.value = value;
		this.description = description;
	}
	
	 public Setting() {
			super();
		}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
