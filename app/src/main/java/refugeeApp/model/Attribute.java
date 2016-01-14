package refugeeApp.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedList;
import java.util.List;



/**
 * The Class Attribute.
 */
@Data
@Entity
public class Attribute {
	
	 
	/** The id. */
	private @Id @GeneratedValue Long id;
	
	/** The name. */
	private String name;
	
	/** The tags. */
	@ElementCollection
	private List<String> tags=new LinkedList<String>();

	/**
	 * Instantiates a new attribute.
	 */
	public Attribute() {
		super();
	}
	
	
	/**
	 * Instantiates a new attribute.
	 *
	 * @param name the name
	 * @param tags the tags
	 */
	public Attribute(String name, List<String> tags) {
		super();
		this.name = name;
		this.tags = tags;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String text="";
		for(String count:tags){
			text= text +" "+count;
		}
		return id+" "+name+" "+text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()) return false;
		Attribute att = (Attribute) obj;
		return att.getName().equals(this.name) && att.getTags().equals(this.tags);
	}


	
	
	
	

	
}