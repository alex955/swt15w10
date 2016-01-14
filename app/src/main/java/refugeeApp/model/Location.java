package refugeeApp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.Data;

/**
 * The Class Location.
 */
@Data
public class Location {
	
	/** The id. */
	private @Id @GeneratedValue long id;
	
	/** The longitude. */
	private double longitude;
	
	/** The latitude. */
	private double latitude;
	
	/** The address. */
	private String address;
	
	/** The distance. */
	private int distance;
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ort [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", address=" + address
				+ ", distance=" + distance + "]";
	}
	
	/**
	 * Instantiates a new location.
	 */
	public Location(){ 
		super();
	}
	
	/**
	 * Instantiates a new location.
	 *
	 * @param address the address
	 */
	public Location(String address){
		this.address=address;
		this.distance=0;
		this.latitude=0;
		this.longitude=0;
	}
	
	/**
	 * Gets the coordinates.
	 *
	 * @param ort the ort
	 * @return the location
	 */
	public Location GetCoordinates(Location ort)  {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAXULGtfv96afP6mTq9W294gKRlIVr3EMk");
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context,ort.getAddress()).await();
				
	    	ort.latitude=results[0].geometry.location.lat;
	    	ort.longitude=results[0].geometry.location.lng;
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Location ort2 = new Location();
    	ort2.setDistance(ort.distance);
    	ort2.setAddress(ort.getAddress());
    	ort2.setLatitude(ort.getLatitude());
    	ort2.setLongitude(ort.getLongitude());
    	return ort2;
    }
}
