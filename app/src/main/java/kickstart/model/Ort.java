package kickstart.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class Ort {
	
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
	
	public Ort(){ 
		super();
	}
	
	public Ort GetCoordinates(String address)  {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAXULGtfv96afP6mTq9W294gKRlIVr3EMk");
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context,address).await();
			System.out.println(results[0].formattedAddress);
				
	    	this.latitude=results[0].geometry.location.lat;
	    	this.longitude=results[0].geometry.location.lng;
	    	System.out.println(this.latitude);
			System.out.println(this.longitude);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	Ort ort = new Ort();
    	ort.setAddress(address);
    	ort.setLatitude(latitude);
    	ort.setLongitude(this.longitude);
    	return ort;
    }
	
	

}
