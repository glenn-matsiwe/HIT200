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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class club_notification extends AppCompatActivity {

    TextView text;
    ArrayList notis;
    ArrayAdapter adpt;
    ListView notis_list;
    Button backBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_notification);

        text = findViewById(R.id.text);
        notis_list = findViewById(R.id.club_notis);
        backBtn3 = findViewById(R.id.backBtn3);

// RECEIVING THE CLUB
        Intent intent = getIntent();
        String club = intent.getStringExtra("name").trim();

        Log.i("club",club);

        text.append(club);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if( currentUser == null ){
            Toast.makeText(getApplicationContext() , "You must login first" , Toast.LENGTH_LONG).show();
        }else{

            ParseQuery<ParseObject> notification_query = ParseQuery.getQuery("Club_Notification");
            notification_query.whereGreaterThanOrEqualTo("expireDate", new Date());
            notification_query.whereContains("club",club).orderByDescending("createdAt");
            notification_query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    notis = new ArrayList<>();
                    if (e == null){
                        Log.i("findInbaackground" , "retrieved " + objects.size() + " objects");
                        if(objects.size() > 0){
                            for ( ParseObject object : objects ){
                                notis.add(object.getString("notification"));
                            }
                            adpt = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1 , notis);
                            notis_list.setAdapter(adpt);
                        }else{
                            Toast.makeText(getApplicationContext() , objects.size()+ " notifications where found" , Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        e.printStackTrace();
                    }
                }
            });
        }
        backBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"btn click " , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),clubsNotification.class));
            }
        });



    }
}
