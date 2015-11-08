package anzeigen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public abstract class Anzeige {
	
	protected @Id @GeneratedValue long id;
	protected String name;
	protected String description;
	protected String location;
	
	@SuppressWarnings("unused")
	protected Anzeige(){
	}
	
	
	public Anzeige(String name,String description,String location){
		this.name=name;
		this.description=description;
		this.location=location;
		
		
	}
	
	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}
	

}
