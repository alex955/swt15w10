package refugeeApp.model;

import lombok.Data;

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
@Data
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

    @Override
    public String toString() {
        return this.name;
    }

}