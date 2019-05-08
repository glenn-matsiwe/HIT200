package com.example.hitcompanion.clubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class club_details extends AppCompatActivity {
    TextView name , advisors , description ;
    Button back6 ;
    String Club_name ;
    String Club_advisors;
    String Club_description ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);

        Intent get = getIntent();
        String Cname = get.getStringExtra("club");


    name = findViewById(R.id.department_name);
    advisors = findViewById(R.id.advisors);
    description = findViewById(R.id.description);
    back6 = findViewById(R.id.deptMenu_back);




        back6.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity( new Intent(getApplicationContext() , clubsList.class ));
        }
    });


        ParseQuery<ParseObject> clubDetails = ParseQuery.getQuery("Clubs");
        clubDetails.whereEqualTo("name" , Cname);
        clubDetails.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if( e == null ){
                    if(objects.size() == 1){
                        for( ParseObject obj : objects ){
                            Club_name = (String) obj.get("name");
                            Club_advisors = (String) obj.get("advisors");
                            Club_description = (String) obj.get("description");
                        }
                        name.setText(Club_name);
                        advisors.setText(Club_advisors);
                        description.setText(Club_description);
                    }
                }else{
                    Toast.makeText(getApplicationContext() , " no record found" , Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
