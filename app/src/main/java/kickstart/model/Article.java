package kickstart.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


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
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate creationdate;
	
	@DateTimeFormat(pattern = "HH:mm dd-MM-yyyy")
	private LocalDateTime activitydate;
		
	public LocalDateTime getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(LocalDateTime activitydate) {
		this.activitydate = activitydate;
	}

	public LocalDate getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(LocalDate creationdate) {
		this.creationdate = creationdate;
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

	
	public Article(String title, String description, String picPath, String location, String street,
			long category, String number, int plz, String creationdate) {
		super();
		this.title = title;
		this.description = description;
		this.picPath = picPath;
		this.location = location;
		this.street = street;
		this.category = category;
		this.number = number;
		this.plz = plz;
		this.creationdate.parse(creationdate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
	
	public Article(String title, String description, String picPath, String location, String street,
			long category, String number, int plz, String creationdate, String activitydate) {
		super();
		this.title = title;
		this.description = description;
		this.picPath = picPath;
		this.location = location;
		this.street = street;
		this.category = category;
		this.number = number;
		this.plz = plz;
		this.creationdate.parse(creationdate,DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		this.activitydate.parse(activitydate,DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", picPath=" + picPath
				+ ", location=" + location + ", street=" + street + ", category=" + category + ", number=" + number
				+ ", plz=" + plz + ", userID=" + userID + ", date=" + creationdate.toString() + "]";
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
