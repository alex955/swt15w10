package kickstart.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	

}
