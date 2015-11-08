package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Article {
	
	@Id
	@GeneratedValue
	private long ID;
	private String location;
	private String locationZIP;
	private long userID;
	private Date expirationDate;
	private String description;
	private String articlePicture;
	private String name;
	
		
	
	

	public Article(long ID,String name,String location,String locationZIP,long userID,Date expirationDate,String description,String articlePicture) {
		
		this.ID=ID;
		this.name=name;
		this.location=location;
		this.locationZIP=locationZIP;
		this.userID=userID;
		this.expirationDate=expirationDate;
		this.description=description;
		this.articlePicture=articlePicture;
	
	}
	
	@Override
	public String toString(){
	return String.format("Artikel: %s %n Beschreibung: %s %n Location: %s %n LocationPLZ: %s",name,description,location,locationZIP);	
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationZIP() {
		return locationZIP;
	}
	public void setLocationZIP(String locationZIP) {
		this.locationZIP = locationZIP;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getArticlePicture() {
		return articlePicture;
	}
	public void setArticlePicture(String articlePicture) {
		this.articlePicture = articlePicture;
	}
	
	
	

}
