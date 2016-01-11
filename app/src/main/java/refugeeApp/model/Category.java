package refugeeApp.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class Category.
 */
@Entity
public class Category {

    /** The id. */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    /** The name. */
    private String name;
    
    /** The bool value whether category is an root category or not. */
    private boolean root;
    
    /** The predecessor. */
    private long predecessor;
    
    /** The attributes. */
    /* attributes which a category has */
    @OneToMany
    (cascade=CascadeType.ALL)
    private List<Attribute> attributes=new LinkedList<Attribute>();
       
    

    /**
     * Instantiates a new category.
     */
    public Category() {
    }

    /**
     * Instantiates a new category.
     *
     * @param name the name
     * @param pre the pre
     */
    public Category(String name, long pre) {
        this.name = name;
        
        if(pre == -1){
        	this.setRoot(true);
        }else{
        	this.setRoot(false);
        	this.setPredecessor(pre);
        }
    }
    
    /**
     * Instantiates a new category.
     *
     * @param name the name
     * @param pre the pre
     * @param attributes the attributes
     */
    public Category(String name, long pre,LinkedList<Attribute> attributes) {
   	 	this.name = name;
        if(pre == -1){
        	this.setRoot(true);
        }
        else{
        	this.setRoot(false);
        	this.setPredecessor(pre);
        }
        this.attributes=attributes;
   } 
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName(){
    	return this.name;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId(){
    	return this.id;	
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id){
    	this.id = id;
    }
    
    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name){
    	this.name = name;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.name;
    }

	/**
	 * Gets the predecessor.
	 *
	 * @return the predecessor
	 */
	public long getPredecessor() {
		return predecessor;
	}

	/**
	 * Sets the predecessor.
	 *
	 * @param predecessor the new predecessor
	 */
	public void setPredecessor(long predecessor) {
		this.predecessor = predecessor;
	}

	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public boolean getRoot() {
		return root;
	}

	/**
	 * Sets the root.
	 *
	 * @param root the new root
	 */
	public void setRoot(boolean root) {
		this.root = root;
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public List<Attribute> getAttributes() {
		return attributes;
	}
		 
	/**
	 * Sets the attributes.
	 *
	 * @param attributes the new attributes
	 */
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	/**
	 * Adds the attribute.
	 *
	 * @param attribute the attribute
	 */
	public void addAttribute(Attribute attribute){
		this.attributes.add(attribute);
	}

}