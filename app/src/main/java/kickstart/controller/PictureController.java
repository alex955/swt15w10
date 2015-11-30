package kickstart.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




import kickstart.model.ArticleRepo;
import kickstart.model.PictureRepo;

@Controller
public class PictureController{
	
	private ArticleRepo articleRepo;
	private FileSystemResource resource;
	private PictureRepo pictureRepo;
	
	@Autowired
	public PictureController(ArticleRepo articleRepo, PictureRepo pictureRepo){
		this.articleRepo=articleRepo;
		this.pictureRepo = pictureRepo;
	}

	@ResponseBody
	@RequestMapping(value = "/showPicture/{id}", produces = "image/jpg")
	public FileSystemResource showArticle(@PathVariable("id") long id) throws IOException{
		resource = new FileSystemResource(articleRepo.findOne(id).getPicture().getPicPath());
		return resource;
	}

}
