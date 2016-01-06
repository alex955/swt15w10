package kickstart.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private boolean root;
    private long predecessor;
    /* attributes which a category has */
    @OneToMany
    (cascade=CascadeType.ALL)
    private List<Attribute> attributes=new LinkedList<Attribute>();
       
    

    public Category() {
    }

    public Category(String name, long pre) {
        this.name = name;
        
        if(pre == -1){
        	this.setRoot(true);
        }else{
        	this.setRoot(false);
        	this.setPredecessor(pre);
        }
    }
    
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
    
    public String getName(){
    	return this.name;
    }
    
    public long getId(){
    	return this.id;	
    }
    
    public void setId(long id){
    	this.id = id;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }

	public long getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(long predecessor) {
		this.predecessor = predecessor;
	}

	public boolean getRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
		 
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(Attribute attribute){
		this.attributes.add(attribute);
		System.out.println(attribute.toString());
	}

}