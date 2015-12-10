package kickstart.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




import kickstart.model.ArticleRepo;
import kickstart.utilities.PicturePathSettings;

@Controller
public class PictureController{
	
	private ArticleRepo articleRepo;
	private FileSystemResource resource;
	
	@Autowired
	public PictureController(ArticleRepo articleRepo){
		this.articleRepo=articleRepo;
	}

	/**
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/showPicture/{id}", produces = "image/jpg")
	public FileSystemResource showArticle(@PathVariable("id") long id) throws IOException{
		if(articleRepo.findOne(id).getPicture() == null){
			//in case someone does not upload a picture
			//this is the path in a git ignore file, at the end we have to change to the picture on the server
			String standardPicPath = PicturePathSettings.picturePath;
			resource = new FileSystemResource(standardPicPath);
		}
		else	
			//in case there is a picture, we use the path from the model picture
			resource = new FileSystemResource(articleRepo.findOne(id).getPicture().getPicPath());
		return resource;
	}

}
