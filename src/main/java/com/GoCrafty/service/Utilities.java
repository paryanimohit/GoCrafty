package com.GoCrafty.service;

import java.util.HashMap;

public class Utilities {

	public static HashMap<String, String> getVideoLinks(String videoLink) {
		HashMap<String, String> videos=new HashMap<String, String>();
		try {
		String[] videoLinks=videoLink.split(",");  //split videos by , 
		for(String video:videoLinks)
		{
			String[] nameAndLink=video.split("@");//split link by : to get name and link
			videos.put(nameAndLink[0], nameAndLink[1]);
		}
		}
		catch (Exception e) {
				e.printStackTrace();
				return null;
		}
		return videos;
	}

	public static String getEmbededLink(String videoURL) {
		String[] temp2;
		if(!videoURL.contains("form")) {
			System.out.println("video url: "+videoURL);
			String[] temp=videoURL.split("\\?v=");
			temp2=temp[1].split("&");
		}
		else {
			System.out.println("Form url: "+videoURL);
			String[] temp=videoURL.split("/e/");
			temp2=temp[1].split("/viewform");
			
		}
		return temp2[0];
	}
	
	

}
