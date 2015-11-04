package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class AnzeigenApplication {

	@Autowired Anzeigen Anzeigen;
	

    public static void main(String[] args) {
        SpringApplication.run(AnzeigenApplication.class, args);
    }
    
   
	@PostConstruct
    void initialize(){
    	System.out.println("wird initialize aufgerufen");
    	Anzeigen.save(new Article(122,"Fahrad made in Germany","Königsbrück","01936",88,new Date(2016,10,31),"Sehr gut erhaltenes Fahrrad!","img/fahrrad.jpg"));
    	Anzeigen.save(new Article(122,"Laserpistole","Dresden","01217",88,new Date(2015,1,31),"Tödlich!","img/pistole.jpg"));
    	Anzeigen.save(new Article(122,"Fahrad made in Greece","Pirna","01226",88,new Date(2016,8,21),"Sehr gut erhaltenes Fahrrad!","img/fahrrad.jpg"));
    	
    	
    }
}
