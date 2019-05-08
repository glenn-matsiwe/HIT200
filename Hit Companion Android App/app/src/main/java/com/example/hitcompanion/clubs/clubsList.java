package com.example.hitcompanion.clubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.example.hitcompanion.blog.question_detail;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class clubsList extends AppCompatActivity {
    Button back ;
    ListView clubs_list ;

    ArrayList mClubs ;
    ArrayAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_list);

        back = findViewById(R.id.backBtn);
        clubs_list = findViewById(R.id.clubs_list);


        ParseQuery<ParseObject> clubsList = ParseQuery.getQuery("Clubs");
        clubsList.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                mClubs = new ArrayList<>();
                if(e == null){
                    if(objects.size() > 0){
                        for( ParseObject obj : objects ){
                            mClubs.add(obj.getString("name"));
                        }
                        adapter = new ArrayAdapter(getApplicationContext() , android.R.layout.simple_list_item_1 , mClubs);
                        clubs_list.setAdapter(adapter);
                        clubs_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent detail = new Intent(getApplicationContext() , club_details.class);
                                detail.putExtra("club" , (String) mClubs.get(position));
                                startActivity(detail);
                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext() , "No post found" , Toast.LENGTH_SHORT);
                    }

                }else{
                    e.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , clubs_home.class ));
            }
        });


    }
}
