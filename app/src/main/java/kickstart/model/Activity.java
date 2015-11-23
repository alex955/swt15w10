package kickstart.model;



import javax.persistence.Entity;

import org.joda.time.DateTime;
import org.joda.time.Interval;

@Entity
public class Activity extends Anzeige{
	
	//Dateformat
	// JodA libary
	private DateTime anfang;
	private Interval duration;
	

	public DateTime getAnfang() {
		return anfang;
	}


	public void setAnfang(DateTime anfang) {
		this.anfang = anfang;
	}


	public Interval getDuration() {
		return duration;
	}


	public void setDuration(Interval duration) {
		this.duration = duration;
	}


	public Activity(String name, String description, String picPath, DateTime anfang,DateTime ende,String street,int PLZ, String location, String number) {
		super(name, description, picPath, location);
		
		this.anfang=anfang;
		
		this.street=street;
		this.number=number;
		this.PLZ=PLZ;
		
		
		if (this.anfang.isBefore(ende)==true) {
		duration = new Interval(this.anfang.getMillis(),ende.getMillis());
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
