package com.example.hitcompanion.clubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.hitcompanion.R;
import com.example.hitcompanion.home_page;

public class clubs_home extends AppCompatActivity {
   Button clubs , notific , main_menu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_home);

        clubs = findViewById(R.id.clubs);
        notific = findViewById(R.id.notific);
        main_menu = findViewById(R.id.main_menu);

        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , clubsList.class));
            }
        });
        notific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , clubsNotification.class) );
            }
        });
        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , home_page.class));
            }
        });


    }
}
