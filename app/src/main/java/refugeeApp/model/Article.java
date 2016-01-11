package refugeeApp.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The Class Article.
 */
@Entity
public class Article {
	
	/** The id. */
	private @Id @GeneratedValue long id;
	
	/** The title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The location. */
	private String location;	
	
	/** The street. */
	private String street;	
	
	/** The category. */
	private long category;
	
	/** The zip. */
	private String zip;
	
	/** The address addition. */
	private String addressAddition;
	
	/** The creator. */
	@OneToOne
	private User creator;	
	
	/** The creationdate. */
	private LocalDateTime creationdate;
	
	/** The picture. */
	@OneToOne
	private Picture picture;
	
	/** The activitydate. */
	private LocalDateTime activitydate;
	
	/** The attributes. */
	@OneToMany
	(cascade=CascadeType.ALL)
	private List<Attribute> attributes=new LinkedList<Attribute>();
	
	/** The kind. */
	private String kind;
	
	/** The longitude. */
	private double longitude;
	
	/** The latitude. */
	private double latitude;


	/**
	 * Instantiates a new article.
	 */
	public Article() {
		super();
	}

	/**
	 * Instantiates a new article.
	 *
	 * @param title the title
	 * @param description the description
	 * @param picture the picture
	 * @param location the location
	 * @param street the street
	 * @param category the category
	 * @param zip the zip
	 * @param creator the creator
	 * @param kind the kind
	 */
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
	
	/**
	 * Instantiates a new article.
	 *
	 * @param title the title
	 * @param description the description
	 * @param location the location
	 * @param street the street
	 * @param category the category
	 * @param zip the zip
	 * @param creator the creator
	 * @param kind the kind
	 */
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
	
	/**
	 * Instantiates a new article.
	 *
	 * @param title the title
	 * @param description the description
	 * @param picture the picture
	 * @param location the location
	 * @param street the street
	 * @param category the category
	 * @param zip the zip
	 * @param activitydate the activitydate
	 * @param kind the kind
	 */
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



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", location=" + location
				+ ", street=" + street + ", category=" + category + ", zip=" + zip + ", creator="
				+ creator + ", creationdate=" + creationdate.toString() + ", picture=" + picture + " , kind=" + kind ;
	}

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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public long getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(long category) {
		this.category = category;
	}

	/**
	 * Gets the zip.
	 *
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Sets the zip.
	 *
	 * @param zip the new zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Gets the address addition.
	 *
	 * @return the address addition
	 */
	public String getAddressAddition() {
		return addressAddition;
	}

	/**
	 * Sets the address addition.
	 *
	 * @param addressAddition the new address addition
	 */
	public void setAddressAddition(String addressAddition) {
		this.addressAddition = addressAddition;
	}


	/**
	 * Gets the kind.
	 *
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * Sets the kind.
	 *
	 * @param kind the new kind
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public List<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attributes the new attributes
	 */
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Adds the attribute.
	 *
	 * @param attribute the attribute
	 */
	public void addAttribute(Attribute attribute){
		this.attributes.add(attribute);
	}

	/**
	 * Gets the creationdate.
	 *
	 * @return the creationdate
	 */
	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 *
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(LocalDateTime creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the activitydate.
	 *
	 * @return the activitydate
	 */
	public LocalDateTime getActivitydate() {
		return activitydate;
	}

	/**
	 * Sets the activitydate.
	 *
	 * @param activitydate the new activitydate
	 */
	public void setActivitydate(LocalDateTime activitydate) {
		this.activitydate = activitydate;
	}

	/**
	 * Gets the creator.
	 *
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public Picture getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	/**
	 * Sets the creator.
	 *
	 * @param creator the new creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
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
		return  latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(double  latitude) {
		this. latitude =  latitude;
	}



}
