package kickstart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.joda.time.DateTime;


@Entity
public class Article {
	
	private @Id @GeneratedValue long id;
	private String title;
	private String description;
	private String picPath;
	private String location;	
	private String street;	
	private long category;
	private String number;
	private int plz;
	private long userID;
	private String date;
	private String photo;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

	public Article() {
		super();
	}

	public Article(String title, String description, String picPath, String location, String street,
			long category, String number, int plz) {
		super();
		this.title = title;
		this.description = description;
		this.picPath = picPath;
		this.location = location;
		this.street = street;
		this.category = category;
		this.number = number;
		this.plz = plz;
	}
	
	public Article(String title, String description, String picPath, String location, String street,
			long category, String number, int plz, String date) {
		super();
		this.title = title;
		this.description = description;
		this.picPath = picPath;
		this.location = location;
		this.street = street;
		this.category = category;
		this.number = number;
		this.plz = plz;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", picPath=" + picPath
				+ ", location=" + location + ", street=" + street + ", category=" + category + ", number=" + number
				+ ", plz=" + plz + ", userID=" + userID + ", date=" + date + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	} 
	

}
