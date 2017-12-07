package com.potatolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class TreesDisplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trees_displayer);
        TextView textView = findViewById(R.id.tv);
        TextView textView1 = findViewById(R.id.tv1);
        TextView textView2 = findViewById(R.id.tv2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String tree = (String) bundle.get("COMMON_NAME");
        String tree1 = (String) bundle.get("SCIENTIFIC_NAME");
        String tree2 = (String) bundle.get("DESCRIPTION");
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(bundle.getInt("IMAGE"));
        textView.setText(tree);
        textView1.setText(tree1);
        textView2.setText(tree2);
    }
}