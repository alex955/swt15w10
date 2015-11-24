package kickstart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.joda.time.DateTime;


@Entity
public abstract class Anzeige {
	
	protected @Id @GeneratedValue long id;
	protected String name;
	protected String description;
	private String picPath;

	public String getPicPath() {
		return picPath;
	}


	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}


	protected String location;
	
	protected String street;
	
	protected long category;
	
	public long getCategory() {
		return category;
	}


	public void setCategory(long category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public int getPLZ() {
		return PLZ;
	}


	public void setPLZ(int pLZ) {
		PLZ = pLZ;
	}


	protected String number;
	protected int PLZ;
	 
	
	@SuppressWarnings("unused")
	protected Anzeige(){
	}
	
	
	public Anzeige(String name,String description, String picPath, String location){
		this.name=name;
		this.description=description;
		this.picPath = picPath;
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
