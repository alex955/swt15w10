package kickstart.controller;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private boolean root;
    private long predecessor;

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

}