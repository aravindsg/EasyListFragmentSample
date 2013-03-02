package com.example.sample.model;



public class Video {
	
	private String name;
	private String author;
	private String youtubeId;
	private String iconUrl;
	private String videoUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
		this.iconUrl = "http://img.youtube.com/vi/" + youtubeId + "/2.jpg";
		this.videoUrl = "http://www.youtube.com/watch?v=" + youtubeId;
	}

}