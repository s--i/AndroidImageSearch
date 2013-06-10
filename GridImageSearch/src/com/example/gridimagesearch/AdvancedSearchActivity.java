package com.example.gridimagesearch;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AdvancedSearchActivity extends Activity {
    private String color;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced_search);

		Intent intent = this.getIntent();
		this.color = intent.getStringExtra("color");

		this.populateSpinners();
	}

	@Override
	public void onBackPressed() {
	    this.done();

	    super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.advanced_search, menu);
		return true;
	}

    public void onAdvancedDone(View view) {
        this.done();
        this.finish();
    }

    private void done() {
        Spinner imageColor = (Spinner) this.findViewById(R.id.spImageColor);
        String selection = imageColor.getSelectedItem().toString();

        Intent intent = new Intent();
        intent.putExtra("color", selection);

        this.setResult(Activity.RESULT_OK, intent);
    }

    private void populateSpinners() {
        List<String> colours = Arrays.asList(this.getResources().getStringArray(R.array.image_colours));
        int index = colours.indexOf(this.color);

        Spinner spinner = (Spinner) findViewById(R.id.spImageColor);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.image_colours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(index);
    }
}