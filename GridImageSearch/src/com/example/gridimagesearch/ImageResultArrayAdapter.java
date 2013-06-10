package com.example.gridimagesearch;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {
	public ImageResultArrayAdapter(Context context, List<ImageResult> imageResults) {
		super(context, android.R.layout.simple_list_item_1, imageResults);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageResult = this.getItem(position);
		SmartImageView smartImageView;

		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			smartImageView = (SmartImageView) inflator.inflate(R.layout.item_image_result, parent, false);
		} else {
			smartImageView = (SmartImageView) convertView;
			smartImageView.setImageResource(android.R.color.transparent);
		}
		smartImageView.setImageUrl(imageResult.getThumbUrl());
		
		return smartImageView;
	}
}