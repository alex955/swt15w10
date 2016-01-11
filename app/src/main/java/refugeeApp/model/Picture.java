package refugeeApp.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * The Class Picture.
 */
@Entity
public class Picture {
	
	/** The id. */
	private @Id @GeneratedValue long id;
	
	/** The pic path. */
	private String picPath;
	
	/** The original file name. */
	private String originalFileName;
	
	/** The uploader. */
	@OneToOne
	private User uploader;
	
	/**
	 * Instantiates a new picture.
	 *
	 * @param picPath the pic path
	 * @param originalFileName the original file name
	 * @param uploader the uploader
	 */
	public Picture(String picPath, String originalFileName, User uploader) {
		super();
		this.picPath = picPath;
		this.originalFileName = originalFileName;
		this.uploader = uploader;
			
	}
	
	/**
	 * Instantiates a new picture.
	 */
	public Picture() {
	super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Picture [id=" + id + ", picPath=" + picPath + ", originalFileName=" + originalFileName + ", uploader="
				+ uploader + "]";
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
	 * Gets the pic path.
	 *
	 * @return the pic path
	 */
	public String getPicPath() {
		return picPath;
	}
	
	/**
	 * Sets the pic path.
	 *
	 * @param picPath the new pic path
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
	/**
	 * Gets the original file name.
	 *
	 * @return the original file name
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}
	
	/**
	 * Sets the original file name.
	 *
	 * @param originalFileName the new original file name
	 */
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	
	/**
	 * Gets the uploader.
	 *
	 * @return the uploader
	 */
	public User getUploader() {
		return uploader;
	}
	
	/**
	 * Sets the uploader.
	 *
	 * @param uploader the new uploader
	 */
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
}
