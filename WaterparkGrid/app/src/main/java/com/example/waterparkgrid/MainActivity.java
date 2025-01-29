package com.example.waterparkgrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the GridView
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

        // Set the OnItemClickListener for GridView items
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Launch FullImageActivity with the selected item's position
                Intent intent = new Intent(getApplicationContext(), FullImageActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }

    // Method to handle the "Open Map" button click
    public void openMap(View view) {
        // Navigate to MainMap activity
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);  // Adjust to your map activity class name
        startActivity(intent);
    }
}
