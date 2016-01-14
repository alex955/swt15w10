package refugeeApp.model;



import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * The Class NewArticleForm.
 */
@Data
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

}
