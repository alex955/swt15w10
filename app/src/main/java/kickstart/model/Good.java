package kickstart.model;

import javax.persistence.Entity;

@Entity
public class Good extends Anzeige{
	
	private String photo;
	

	public Good(String name, String description, String location,String photo) {
		super(name, description, location);
		this.photo=photo;
	
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
