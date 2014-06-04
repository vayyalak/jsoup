package com.jsoup.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

public String getProperties(){
 
	Properties prop = new Properties();
	FileInputStream input = null;
		try {
			input = new FileInputStream("d:/config.properties");
			prop.load(input);
			prop.load(input);
			return prop.getProperty("url");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	return "";
	}
}
