package refugeeApp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class Location {
	
	private @Id @GeneratedValue long id;
	private double longitude;
	private double latitude;
	private String address;
	private int distance;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "Ort [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", address=" + address
				+ ", distance=" + distance + "]";
	}
	
	public Location(){ 
		super();
	}
	
	public Location(String address){
		this.address=address;
		this.distance=0;
		this.latitude=0;
		this.longitude=0;
	}
	
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
