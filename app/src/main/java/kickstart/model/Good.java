package kickstart.model;

import javax.persistence.Entity;

import org.joda.time.DateTime;

@Entity
public class Good extends Anzeige{
	
	private String photo;
	

	public Good(String name, String description,String photo,String street,int PLZ, String location, String number) {
		super(name, description, location);
		this.photo=photo;
	
		this.street=street;
		this.number=number;
		this.PLZ=PLZ;
	
	}

	
	protected Good() {
		super();
	}

	

	
	
	@Override
	public String toString() {
		
		return String.format("%s --- Name: %s --- %s --- %s ---  Bildpfad: %s    ",id,name,description,location,photo);
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
