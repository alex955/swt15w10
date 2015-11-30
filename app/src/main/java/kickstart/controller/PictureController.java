package kickstart.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




import kickstart.model.ArticleRepo;

@Controller
public class PictureController{
	
	private ArticleRepo articleRepo;
	private FileSystemResource resource;

	@Autowired
	public PictureController(ArticleRepo articleRepo){
		this.articleRepo=articleRepo;
	}

	@ResponseBody
	@RequestMapping(value = "/showPicture/{id}", produces = "image/jpg")
	public FileSystemResource showArticle(@PathVariable("id") long id) throws IOException{
		resource = new FileSystemResource(articleRepo.findOne(id).getPicPath());
		return resource;
	}

}
