package com.example.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable {
	private static final long serialVersionUID = 1228859894775701320L;
	
	private String thumbUrl;
	private String url;
	
	public ImageResult(JSONObject json) {
		try {
			this.thumbUrl = json.getString("tbUrl");
			this.url = json.getString("url");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getThumbUrl() {
		return this.thumbUrl;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String toString() {
		return this.url;
	}

	public static List<ImageResult> fromJSONArray(JSONArray imageJsonResults) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
			
		for (int i = 0; i < imageJsonResults.length(); i++) {
			try {
				results.add(new ImageResult(imageJsonResults.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
}