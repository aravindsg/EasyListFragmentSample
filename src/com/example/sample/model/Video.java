package com.example.sample.model;



/**
 */
public class Video {
	
	private String name;
	private String author;
	private String youtubeId;
	private String iconUrl;
	private String videoUrl;

	/**
	 * Method getName.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method setName.
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method getAuthor.
	 * @return String
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Method setAuthor.
	 * @param author String
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Method getIconUrl.
	 * @return String
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * Method getVideoUrl.
	 * @return String
	 */
	public String getVideoUrl() {
		return videoUrl;
	}

	/**
	 * Method getYoutubeId.
	 * @return String
	 */
	public String getYoutubeId() {
		return youtubeId;
	}

	/**
	 * Method setYoutubeId.
	 * @param youtubeId String
	 */
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
		this.iconUrl = "http://img.youtube.com/vi/" + youtubeId + "/2.jpg";
		this.videoUrl = "http://www.youtube.com/watch?v=" + youtubeId;
	}

}