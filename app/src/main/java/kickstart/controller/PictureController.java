package kickstart.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.FileSystemResource;

import kickstart.model.ArticleRepo;

@Controller
public class PictureController{
	
	private ArticleRepo articleRepo;
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
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
