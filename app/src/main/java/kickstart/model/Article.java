package kickstart.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Article {
	
	private @Id @GeneratedValue long id;
	private String title;
	private String description;
	private String location;	
	private String street;	
	private long category;
	private String number;
	private String zip;
	private long userID;
	private String creationDate;
	@OneToOne
	private Picture picture;
	
	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	@DateTimeFormat(pattern = "HH:mm dd-MM-yyyy")
	private LocalDateTime activitydate;
		
	public LocalDateTime getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(LocalDateTime activitydate) {
		this.activitydate = activitydate;
	}

	public String getCreationdate() {
		return creationDate;
	}

	public void setCreationdate(String creationdate) {
		this.creationDate = creationdate;
	}

	private String photo;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	
	


	public Article() {
		super();
	}

	//constructor with creationdate
	public Article(String title, String description, Picture picture, String location, String street,
			long category, String number, String zip, String creationdate) {
		super();
		this.title = title;
		this.description = description;
		this.picture = picture;
		this.location = location;
		this.street = street;
		this.category = category;
		this.number = number;
		this.zip = zip;
		this.creationDate = creationdate;
	}
	
	//constructor without Picture
		public Article(String title, String description, String location, String street,
				long category, String number, String zip, String creationdate) {
			super();
			this.title = title;
			this.description = description;
			this.location = location;
			this.street = street;
			this.category = category;
			this.number = number;
			this.zip = zip;
			this.creationDate = creationdate;
		}
	
	
	//constructor with creationdate and activitydate
	public Article(String title, String description, Picture picture, String location, String street,
			long category, String number, String zip, String creationdate, String activitydate) {
		super();
		this.title = title;
		this.description = description;
		this.picture = picture;
		this.location = location;
		this.street = street;
		this.category = category;
		this.number = number;
		this.zip = zip;
		this.creationDate = creationdate;
		this.activitydate.parse(activitydate,DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", picture=" + picture
				+ ", location=" + location + ", street=" + street + ", category=" + category + ", number=" + number
				+ ", zip=" + zip + ", userID=" + userID + ", creationDate=" + creationDate
				+ ", activitydate=" + activitydate + ", photo=" + photo + "]";
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	} 
	

}
