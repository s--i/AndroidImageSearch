package com.example.gridimagesearch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	private EditText etQuery;
	//private Button btnSearch;
	private GridView gvResults;

	private List<ImageResult> imageResults = new ArrayList<ImageResult>();
	private ImageResultArrayAdapter imageResultArrayAdapter;

	private String color = "any";
	private int offset = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		this.setupViews();

		this.imageResultArrayAdapter = new ImageResultArrayAdapter(this, this.imageResults);

		this.gvResults.setAdapter(this.imageResultArrayAdapter);
		this.gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long rowId) {
				ImageResult imageResult = imageResults.get(position);

				Intent intent = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				intent.putExtra("result", imageResult);

				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_advanced:
				Intent intent = new Intent(this, AdvancedSearchActivity.class);
				intent.putExtra("color", this.color);
				startActivityForResult(intent, 8);

				break;
			default:
				break;
		}

		return true;
	}

	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case (8): {
                if (resultCode == Activity.RESULT_CANCELED || resultCode == Activity.RESULT_OK) {
                    this.color = intent.getStringExtra("color");
                }
                break;
            }
        }
    }

	private void setupViews() {
		this.etQuery = (EditText) this.findViewById(R.id.etQuery);
		//this.btnSearch = (Button) this.findViewById(R.id.btnSearch);
		this.gvResults = (GridView) this.findViewById(R.id.gvResults);
	}

	public void onImageSearch(View view) {
		String query = this.etQuery.getText().toString();
		Toast.makeText(this, "Searching for images of "+ query, Toast.LENGTH_SHORT).show();

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		String searchUrl = buildQuery(query);
		Log.d("DEBUG", searchUrl);
		asyncHttpClient.get(searchUrl, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				try {
					JSONArray imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear();
					imageResultArrayAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

    private String buildQuery(String query) {
        String search = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=8&start=" + this.offset + "&q=" + Uri.encode(query);

        if (!this.color.equals("any")) {
            search += "&imgcolor=" + this.color;
        }

        return search;
    }
}