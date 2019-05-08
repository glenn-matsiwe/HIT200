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
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class clubsNotification extends AppCompatActivity {

    ArrayList mClubNotifs;
    ArrayAdapter adpt;
    ListView clubsNotis;
    Button main_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_notification);

    clubsNotis = findViewById(R.id.mClubsNotis);
    main_menu = findViewById(R.id.main_menu);


        ParseUser currentUser = ParseUser.getCurrentUser();
        if( currentUser == null ){
            Toast.makeText(getApplicationContext() , "You must login first" , Toast.LENGTH_LONG).show();
        }else{

            ParseQuery<ParseObject> clubs = ParseQuery.getQuery("Clubs");
            clubs.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    mClubNotifs = new ArrayList<>();
                    if (e == null){
                        Log.i("findInbackground" , "retrieved " + objects.size() + " objects");
                        if(objects.size() > 0){
                            for ( ParseObject object : objects ){
                                mClubNotifs.add(object.getString("name"));
                            }
                            adpt = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1 , mClubNotifs);
                            clubsNotis.setAdapter(adpt);
                            clubsNotis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent detail = new Intent(getApplicationContext() , club_notification.class);
                                    detail.putExtra("name" , (String) mClubNotifs.get(position));
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
        }

        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),clubs_home.class));
            }
        });




    }
}
