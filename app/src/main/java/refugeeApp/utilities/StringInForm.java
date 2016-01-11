package refugeeApp.utilities;

/**
 * utility class for passing of strings in forms.
 */
public class StringInForm {
	
	/**
	 * Instantiates a new string in form.
	 *
	 * @param content the content
	 */
	public StringInForm(String content) {
		this.content = content;
	}
	
	/**
	 * Instantiates a new string in form.
	 */
	public StringInForm(){
		
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/** The content. */
	String content;
	
	/** The content2. */
	String content2;
	
	/**
	 * Gets the content2.
	 *
	 * @return the content2
	 */
	public String getContent2() {
		return content2;
	}

	/**
	 * Sets the content2.
	 *
	 * @param content2 the new content2
	 */
	public void setContent2(String content2) {
		this.content2 = content2;
	}
}
