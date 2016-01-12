package refugeeApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import refugeeApp.model.ArticleRepo;
import refugeeApp.model.SettingsRepository;

import java.io.IOException;

/**
 * The Class PictureController.
 */
@Controller
public class PictureController{
	
	/** The article repo. */
	private final ArticleRepo articleRepo;
	
	/** The settings repo. */
	private final SettingsRepository settingsRepo;

	/**
	 * autowired constructor.
	 *
	 * @param articleRepo the article repo
	 * @param settingsRepo the settings repo
	 */
	@Autowired
	public PictureController(ArticleRepo articleRepo, SettingsRepository settingsRepo){
		this.articleRepo=articleRepo;
		this.settingsRepo = settingsRepo;
	}
	
	/**
	 * returns an existing picture of the article or a standard picture to the template .
	 *
	 * @param id (article id)
	 * @return Picture (FileSystemResource)
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@ResponseBody
	@RequestMapping(value = "/showPicture/{id}", produces = "image/jpg")
	public FileSystemResource showArticle(@PathVariable("id") long id) throws IOException{
		/* The resource. */
		FileSystemResource resource;
		if(articleRepo.findOne(id).getPicture() == null){
			//in case someone does not upload a picture		
			resource = new FileSystemResource(settingsRepo.findOne("noUploadedPicturePath").getStringValue());
		}
		else{
			//in case there is a picture, we use the path from the model picture
			resource = new FileSystemResource(articleRepo.findOne(id).getPicture().getPicPath());
		}
		return resource;
	}
}
