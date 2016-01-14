package refugeeApp.model;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * The Class Picture.
 */
@Data
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

}
