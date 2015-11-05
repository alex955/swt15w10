package com.example;

import java.util.LinkedList;

public class CategoryFirstTierObject {
	private Category cat;
	private LinkedList<Category> subcats;
	
	public CategoryFirstTierObject(Category cat, LinkedList<Category> subcats){
		this.setCat(cat);
		this.setSubcats(subcats);
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public LinkedList<Category> getSubcats() {
		return subcats;
	}

	public void setSubcats(LinkedList<Category> subcats) {
		this.subcats = subcats;
	}
}
