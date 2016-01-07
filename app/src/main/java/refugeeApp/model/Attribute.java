package refugeeApp.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Attribute {
	
	 
	private @Id @GeneratedValue Long id;
	private String name;
	
	@ElementCollection
	private List<String> tags=new LinkedList<String>();

	public Attribute() {
		super();
	}
	
	
	public Attribute(String name, List<String> tags) {
		super();
		this.name = name;
		this.tags = tags;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		String text="";
		for(String count:tags){
			text= text +" "+count;
		}
		return id+" "+name+" "+text;
	}


	@Override
	public boolean equals(Object obj) {
		 Attribute att = (Attribute) obj;
		if (att.getName().equals(this.name) && att.getTags().equals(this.tags)) return true; else return false;
	}


	
	
	
	

	
}