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
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String tree = bundle.get("NAME") + "\n\n" + bundle.get("DESCRIPTION");
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(bundle.getInt("ICON"));
        textView.setText(tree);
    }
}