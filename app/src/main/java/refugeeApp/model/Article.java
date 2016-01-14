package refugeeApp.model;

import lombok.Data;

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
@Data
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
	 * Adds the attribute.
	 *
	 * @param attribute the attribute
	 */
	public void addAttribute(Attribute attribute){
		this.attributes.add(attribute);
	}

}
