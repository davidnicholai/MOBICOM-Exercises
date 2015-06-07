package com.example.labexamrssfeed;

public class News {
	private String title;
	private String subtext;
	
	public News(String title, String subtext) {
		this.title = title;
		this.subtext = subtext;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSubtext() {
		return subtext;
	}
	
	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}
	
}
