package com.wy.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class IconList {

	@Test
	public void test() throws Exception{
		
		InputStream in = IconList.class.getResourceAsStream("/static/assets/css/font-awesome-ie7.min.css");
		BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while((line = bfr.readLine())!=null){
			if(line.startsWith(".icon")){
				String icon = line.substring(0, line.indexOf("{"));
				icon = icon.substring(1);
				System.out.println("<li><i class=\""+icon+" ace-icon glyphicon\"></i> "+icon+"</li>");
			}
		}
		
		
	}
	
}
