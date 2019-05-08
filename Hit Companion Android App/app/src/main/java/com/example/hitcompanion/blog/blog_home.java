package com.example.hitcompanion.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.example.hitcompanion.home_page;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class blog_home extends AppCompatActivity {
    Button back , aske ;
    ListView question;
    ArrayList mblogPosts ;
    ArrayAdapter adpt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_home);

        back = findViewById(R.id.prevBtn1);
        aske = findViewById(R.id.askQn);
        question = findViewById(R.id.qns_list);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , home_page.class));
            }
        });

        aske.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ask.class));
            }
        });
        ParseUser currentUser = ParseUser.getCurrentUser();
        if( currentUser == null ){
            Toast.makeText(getApplicationContext() , "You must login first" , Toast.LENGTH_LONG).show();
        }else{

            ParseQuery<ParseObject> blog_posts = ParseQuery.getQuery("Blog");
            blog_posts.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                mblogPosts = new ArrayList<>();
                if (e == null){
                    Log.i("findInbaackground" , "retrieved " + objects.size() + " objects");
                    if(objects.size() > 0){
                        for ( ParseObject object : objects ){
                            mblogPosts.add(object.getString("body"));
                        }
                        adpt = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1 , mblogPosts);
                        question.setAdapter(adpt);
                        question.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent detail = new Intent(getApplicationContext() , question_detail.class);
                                detail.putExtra("question" , (String) mblogPosts.get(position));
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






    }
}
