package kickstart.model;

import java.util.Date;


import javax.persistence.Entity;

@Entity
public class Activity extends Anzeige{
	
	//Dateformat
	// JodA libary
	private Date anfang;
	private Date ende;
	private long duration;
	

	public Activity(String name, String description, String location, Date anfang,Date Ende) {
		super(name, description, location);
		
		this.anfang=anfang;
		this.ende=Ende;
		
		
		if (this.anfang.before(this.ende)==true) {
		duration = this.anfang.getTime()-this.ende.getTime();
		} 
		else throw new IllegalArgumentException( "Endzeit ist vor Anfangszeit" );
	
	}

	
	protected Activity() {
		super();
	}

	

	
	
	@Override
	public String toString() {
	
		return String.format("%s --- Name: %s --- %s --- %s ---     ",id,name,description,location);

		// return String.format("%s --- Name: %s --- Beschreibung: %s %n--- vom %t bis %t --- %d Sekunden --- in %s --- %n ",id,name,description,anfang,ende,duration,location);
	}



}
