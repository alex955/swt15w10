package refugeeApp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

/**
 * The Class Location.
 */
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
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param adress the new address
	 */
	public void setAddress(String adress) {
		this.address = adress;
	}
	
	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Sets the distance.
	 *
	 * @param distance the new distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
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
