package com.example.gridimagesearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.loopj.android.image.SmartImageView;

public class ImageDisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);

		ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra("result");

		SmartImageView ivResult = (SmartImageView) findViewById(R.id.ivResult);
		ivResult.setImageUrl(imageResult.getUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}
}