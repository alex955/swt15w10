package kickstart.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Article {
	
	private @Id @GeneratedValue long id;
	private String title;
	private String description;
	private String location;	
	private String street;	
	private long category;
	private String zip;
	private String addressAddition;
	@OneToOne
	private User creator;	
	private LocalDateTime creationdate;
	@OneToOne
	private Picture picture;
	private LocalDateTime activitydate;
	@OneToMany
	(cascade=CascadeType.ALL)
	private List<Attribute> attributes=new LinkedList<Attribute>();
	private String kind;
	private double longitude;
	private double latitude;


	public Article() {
		super();
	}

	public Article(String title, String description, Picture picture, String location, String street,
			long category, String zip, User creator, String kind) {
		super();
		this.title = title;
		this.description = description;
		this.picture = picture;
		this.location = location;
		this.street = street;
		this.category = category;
		this.zip = zip;
		this.creationdate = LocalDateTime.now();
		this.creator = creator;
		this.kind = kind;
	}
	
	//constructor without Picture
		public Article(String title, String description, String location, String street,
				long category, String zip, User creator, String kind) {
			super();
			this.title = title;
			this.description = description;
			this.location = location;
			this.street = street;
			this.category = category;
			this.zip = zip;
			this.creationdate = LocalDateTime.now();
			this.creator = creator;
			this.kind = kind;
		}
	
	//constructor with and activitydate
	public Article(String title, String description, Picture picture, String location, String street,
			long category, String zip, LocalDateTime activitydate, String kind) {
		super();
		this.title = title;
		this.description = description;
		this.picture = picture;
		this.location = location;
		this.street = street;
		this.category = category;
		this.zip = zip;
		this.creationdate = LocalDateTime.now();
		this.activitydate = activitydate;
		this.kind = kind;
	}



	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", location=" + location
				+ ", street=" + street + ", category=" + category + ", zip=" + zip + ", creator="
				+ creator + ", creationdate=" + creationdate.toString() + ", picture=" + picture + " , kind=" + kind ;
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


	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public void addAttribute(Attribute attribute){
		this.attributes.add(attribute);
		System.out.println(attribute.toString());
	}

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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return  latitude;
	}

	public void setLatitude(double  latitude) {
		this. latitude =  latitude;
	}



}
