package com.example.hitcompanion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button login , web ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            login = findViewById(R.id.log_in);
            web = findViewById(R.id.web_v);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , log_in.class));
                }
            });

            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("http://10.42.0.34:8000") ));
                }
            });




    }
}
