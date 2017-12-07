package com.potatolist;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class TreesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trees);

        final TreesAdapter myAdapter = new TreesAdapter(this);
        GridView gridview = findViewById(R.id.gridview);
        gridview.setAdapter(myAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(TreesActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("POSITION", "tree in position " + position);
                HashMap<String, String> pnamedesc;
                pnamedesc = myAdapter.treeInfo.get(position);
                bundle.putString("COMMON_NAME", pnamedesc.get("common_name"));
                bundle.putString("SCIENTIFIC_NAME", pnamedesc.get("scientific_name"));
                bundle.putString("DESCRIPTION", pnamedesc.get("description"));
                bundle.putInt("IMAGE", myAdapter.bigtreeImages.get(position));
                sendMessage(bundle);
            }
        });

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());   //gets JSON file as String and assigns to obj
            JSONArray m_jArry = obj.getJSONArray("trees"); //gets portion of JSON at index "trees" and assigns to m_jArry
            HashMap<String, String> p;                              //creates a hashmap (key/value pair) called p

            for (int i = 0; i < m_jArry.length(); i++) {            //for each item in m_jArry
                JSONObject jo_inside = m_jArry.getJSONObject(i);    //assign item to jo_inside
                String p_common_name = jo_inside.getString("common_name");  //extra name from jo_inside
                String p_scientific_name = jo_inside.getString("scientific_name");
                String p_description = jo_inside.getString("description"); //extra description from jo_inside
                String p_icon = jo_inside.getString("image");  //extra icon from jo_inside

                //Toast.makeText(this, p_icon, Toast.LENGTH_SHORT).show();

                Log.d("name-->", jo_inside.getString("common_name"));
                Log.d("icon-->", jo_inside.getString("image"));

                //Add your values in your `ArrayList` as below:
                p = new HashMap<>();
                p.put("common_name", p_common_name);                              //then add name to p as key
                p.put("scientific_name", p_scientific_name);
                p.put("description", p_description);                //and add description to p as value

                myAdapter.treeInfo.add(p);                        //then add p to treeInfo Array in myAdapter

                Resources resources = getResources();               //create a Resource object called resource
                int pIconId = resources.getIdentifier(p_icon + "_small", "drawable", getPackageName()); //pull out ID of image matching p_icon
                myAdapter.treeImages.add(pIconId);                 // add ID to treeImages array in myAdapter
                pIconId = resources.getIdentifier(p_icon + "_big", "drawable", getPackageName());
                myAdapter.bigtreeImages.add(pIconId);

                //Toast.makeText(this, "" + pIconId, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("potato.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void sendMessage(Bundle bundle) {
        Intent intent = new Intent(this, TreesDisplayer.class);
        String message = "tree info";
        intent.putExtras(bundle);
        startActivity(intent);
    }
}