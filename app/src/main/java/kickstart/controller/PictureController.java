package kickstart.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import kickstart.model.ArticleRepo;

@Controller
public class PictureController implements ServletContextAware{
	
	private ArticleRepo articleRepo;
	ServletContext servletContext;

	@Autowired
	public PictureController(ArticleRepo articleRepo){
		this.articleRepo=articleRepo;
	}
	
	public void setServletContext(ServletContext servletContext){
		this.servletContext = servletContext;
	}
	
	@RequestMapping(value = "/showPicture/{id}")
	public void showArticle(HttpServletResponse response, @PathVariable("id") long id) throws IOException{
		response.setContentType("image/jpg");
	    InputStream in = servletContext.getResourceAsStream(articleRepo.findOne(id).getPicPath());
	    IOUtils.copy(in, response.getOutputStream());
	}

}
