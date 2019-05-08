package com.example.hitcompanion.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hitcompanion.R;
import com.example.hitcompanion.home_page;

public class map_page extends AppCompatActivity {
    ImageView map;
    Button prev4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);

        map = findViewById(R.id.mapp);
        prev4 = findViewById(R.id.prev4);
        prev4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), home_page.class));
            }
        });
    }
}
