//Form for the informations of the submitted newArticle.html

package kickstart.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class NewArticleForm {
	
	private long categoryId;

	@NotEmpty(message = "Geben Sie ihrem Angebot einen Titel.")
    private String title;
    private String description;
    private String zip;
    private String city;
    private String streetName;
    private String adressAddition;
    private MultipartFile file;
    private double longitude;
    private double latitude;
	@NotEmpty(message = "Wählen Sie die Art des Angebots.")
    private String kind;
    
    public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getAdressAddition() {
		return adressAddition;
	}
	public void setAdressAddition(String adressAddition) {
		this.adressAddition = adressAddition;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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
}
