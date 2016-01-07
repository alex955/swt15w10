package refugeeApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Picture {
	private @Id @GeneratedValue long id;
	private String picPath;
	private String originalFileName;
	@OneToOne
	private User uploader;
	
	public Picture(String picPath, String originalFileName, User uploader) {
		super();
		this.picPath = picPath;
		this.originalFileName = originalFileName;
		this.uploader = uploader;
	
		
	}
	public Picture() {
	super();
	}
	
	@Override
	public String toString() {
		return "Picture [id=" + id + ", picPath=" + picPath + ", originalFileName=" + originalFileName + ", uploader="
				+ uploader + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public User getUploader() {
		return uploader;
	}
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
}
