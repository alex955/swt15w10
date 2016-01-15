package refugeeApp.model;

import lombok.Data;

import java.util.LinkedList;

/**
 * The Class NewAttributes.
 */
@Data
public class NewAttributes {
	
	/** The choosen tags. */
	private LinkedList<String> choosenTags= new LinkedList<String>();
	
	public NewAttributes() {
		super();
	}
	public NewAttributes(LinkedList<String> attributes ){
		this.choosenTags=attributes;
		
	}

}
