package com.potatolist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

class TreesAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<HashMap<String, String>> treeInfo = new ArrayList<>();
    public ArrayList<Integer> treeImages = new ArrayList<>();
    public ArrayList<Integer> bigtreeImages = new ArrayList<>();

    public TreesAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return treeInfo.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(treeImages.get(position));
        return imageView;
    }
}