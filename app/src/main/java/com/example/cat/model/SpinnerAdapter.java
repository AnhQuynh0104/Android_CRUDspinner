package com.example.cat.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.cat.R;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private int[] images = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
                            R.drawable.cat4, R.drawable.cat5, R.drawable.cat6};
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        ImageView imageView = view.findViewById(R.id.imageSpinner);
        imageView.setImageResource(images[position]);
        return view;
    }
}
