package com.example.sample.utils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.aravind.project.httputils.HttpConnector;
import com.aravind.project.httputils.Logger;
import com.example.sample.model.Video;

public class HttpUtility {

	HttpConnector helper;
	Context context;

	public HttpUtility(Context context) {
		this.context = context;
		helper = new HttpConnector();
	}

	public List<Video> getYoutubeVideos(int page, String keyword) {
		String result;

		result = helper.performRequest(context, "https://gdata.youtube.com/feeds/api/videos?q=" + URLEncoder.encode(keyword) + "&start-index=" + page + "&orderby=published&max-results=" + 20 + "&v=2&alt=json",
				HttpConnector.HTTP_GET, null, null);

		Logger.i("Related Video URL Result", result);
		try {
			return getVideos(result);
		} catch (JSONException e) {
			Logger.e("ERROR", e.getMessage());
		}

		return null;
	}

	private List<Video> getVideos(String result) throws JSONException {
		JSONObject tempObject;

		tempObject = new JSONObject(result);
		if (tempObject != null) {

			JSONObject feedJson = tempObject.optJSONObject("feed");

			if (feedJson != null) {

				JSONArray videoList = feedJson.getJSONArray("entry");

				if (videoList != null) {
					List<Video> recommdedGames = new ArrayList<Video>();
					for (int i = 0; i < videoList.length(); i++) {

						JSONObject temp = videoList.optJSONObject(i);

						Video video = new Video();
						video.setName(temp.optJSONObject("title").optString("$t"));
						video.setAuthor(temp.optJSONArray("author").optJSONObject(0).optJSONObject("name").optString("$t"));
						String[] x = temp.optJSONObject("id").optString("$t").split(":");
						String youtubeId = x[x.length - 1];
						video.setYoutubeId(youtubeId);
						recommdedGames.add(video);
					}

					return recommdedGames;
				}
			}

		}

		return null;
	}
}