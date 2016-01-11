package refugeeApp.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



/**
 * The Class Attribute.
 */
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


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the new tags
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
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
		 Attribute att = (Attribute) obj;
		if (att.getName().equals(this.name) && att.getTags().equals(this.tags)) return true; else return false;
	}


	
	
	
	

	
}