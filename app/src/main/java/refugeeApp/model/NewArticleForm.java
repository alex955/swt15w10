package refugeeApp.model;



import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * The Class NewArticleForm.
 */
public class NewArticleForm {
	
	/** The category id. */
	private long categoryId;
	
	/** The title. */
	@NotEmpty(message = "")
    private String title;

    /** The description. */
    private String description;
    
    /** The zip. */
    private String zip;
    
    /** The city. */
    private String city;
    
    /** The street name. */
    private String streetName;
    
    /** The adress addition. */
    private String adressAddition;
    
    /** The activity date. */
    private String activityDate;
    
    /** The file. */
    private MultipartFile file;
    
    /** The longitude. */
    private double longitude;
    
    /** The latitude. */
    private double latitude;

	/** The kind. */
	@NotEmpty(message = "")
    private String kind;
    
    /**
     * Gets the street name.
     *
     * @return the street name
     */
    public String getStreetName() {
		return streetName;
	}
	
	/**
	 * Sets the street name.
	 *
	 * @param streetName the new street name
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	/**
	 * Gets the adress addition.
	 *
	 * @return the adress addition
	 */
	public String getAdressAddition() {
		return adressAddition;
	}
	
	/**
	 * Sets the adress addition.
	 *
	 * @param adressAddition the new adress addition
	 */
	public void setAdressAddition(String adressAddition) {
		this.adressAddition = adressAddition;
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
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * Gets the file.
	 *
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}
	
	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	public long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * Sets the category id.
	 *
	 * @param categoryId the new category id
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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
		return latitude;
	}
	
	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Gets the activity date.
	 *
	 * @return the activity date
	 */
	public String getActivityDate() {
		return activityDate;
	}
	
	/**
	 * Sets the activity date.
	 *
	 * @param activityDate the new activity date
	 */
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
}
