package com.example.hitcompanion;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hitcompanion.blog.blog_home;
import com.example.hitcompanion.clubs.clubs_home;
import com.example.hitcompanion.department.department_home;
import com.example.hitcompanion.map.map_page;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class home_page extends AppCompatActivity {

    Button blog , map , department , clubs , logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        blog = findViewById(R.id.blog);
        map = findViewById(R.id.map);
        department = findViewById(R.id.dept);
        clubs = findViewById(R.id.clubs);
        logout = findViewById(R.id.logout);

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "For Educational collaboration" , Toast.LENGTH_LONG ).show();
                startActivity(new Intent(getApplicationContext() , blog_home.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "H.I.T Map helps with directions" , Toast.LENGTH_LONG ).show();
                startActivity(new Intent(getApplicationContext() , map_page.class));
            }
        });

        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext() , "View info about your deepartment and other" , Toast.LENGTH_LONG ).show();
                startActivity(new Intent(getApplicationContext() , department_home.class));
            }
        });

        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "Get info about the clubs at H.I.T" , Toast.LENGTH_LONG ).show();
                startActivity(new Intent(getApplicationContext() , clubs_home.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser current = ParseUser.getCurrentUser();
                current.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        startActivity(new Intent(getApplicationContext() , MainActivity.class));
                    }
                });
            }
        });




    }

}
