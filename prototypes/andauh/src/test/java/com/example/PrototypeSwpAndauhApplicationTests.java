package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import kickstart.controller.PrototypeSwpAndauhApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PrototypeSwpAndauhApplication.class)
@WebAppConfiguration
public class PrototypeSwpAndauhApplicationTests {

	@Test
	public void contextLoads() {
	}

}
