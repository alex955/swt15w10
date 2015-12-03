package kickstart.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;



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
	private String addressAddition;
	@OneToOne
	private User creator;	
	private LocalDateTime creationdate;
	@OneToOne
	private Picture picture;
	private LocalDateTime activitydate;
	

	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(LocalDateTime creationdate) {
		this.creationdate = creationdate;
	}

	public LocalDateTime getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(LocalDateTime activitydate) {
		this.activitydate = activitydate;
	}
		
	public User getCreator() {
		return creator;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
	

	public Article() {
		super();
	}

	//constructor with creationdate
	public Article(String title, String description, Picture picture, String location, String street,
			long category, String number, String zip, User creator) {
		super();
		this.title = title;
		this.description = description;
		this.picture = picture;
		this.location = location;
		this.street = street;
		this.category = category;
		this.number = number;
		this.zip = zip;
		this.creationdate = LocalDateTime.now();
		this.creator = creator;
	}
	
	//constructor without Picture
		public Article(String title, String description, String location, String street,
				long category, String number, String zip, User creator) {
			super();
			this.title = title;
			this.description = description;
			this.location = location;
			this.street = street;
			this.category = category;
			this.number = number;
			this.zip = zip;
			this.creationdate = LocalDateTime.now();
			this.creator = creator;
		}
		
	//constructor without Picture, User
			public Article(String title, String description, String location, String street,
					long category, String number, String zip) {
				super();
				this.title = title;
				this.description = description;
				this.location = location;
				this.street = street;
				this.category = category;
				this.number = number;
				this.zip = zip;
				this.creationdate = LocalDateTime.now();
			}
	
	
	//constructor with creationdate and activitydate
	public Article(String title, String description, Picture picture, String location, String street,
			long category, String number, String zip, LocalDateTime activitydate) {
		super();
		this.title = title;
		this.description = description;
		this.picture = picture;
		this.location = location;
		this.street = street;
		this.category = category;
		this.number = number;
		this.zip = zip;
		this.creationdate = LocalDateTime.now();
		this.activitydate = activitydate;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", location=" + location
				+ ", street=" + street + ", category=" + category + ", number=" + number + ", zip=" + zip + ", creator="
				+ creator + ", creationdate=" + creationdate.toString() + ", picture=" + picture ;
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

	public String getAddressAddition() {
		return addressAddition;
	}

	public void setAddressAddition(String addressAddition) {
		this.addressAddition = addressAddition;
	}


}
