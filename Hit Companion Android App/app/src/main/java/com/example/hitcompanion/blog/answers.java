package com.example.hitcompanion.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class answers extends AppCompatActivity {

    TextView qn , topic1 ;
    ListView answersList ;
    ImageButton imgBtn2 ;

    ArrayList DB_Answers;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        qn = findViewById(R.id.qn);
        topic1 = findViewById(R.id.topic2);
        answersList = findViewById(R.id.answersList);
        imgBtn2 = findViewById(R.id.imgBtn2);

//   RECEIVING DATA ACTIVITY
        Intent intent = getIntent();
        String qn_id = intent.getStringExtra("id");
        String topic = intent.getStringExtra("topic");
        final String question = intent.getStringExtra("question");

//   SETTING THE DATA TO TEXTVIEWS
        topic1.setText(topic);
        qn.setText(question);

//   QUERY TO POPULATE THE LIST VIEW

        ParseQuery<ParseObject> mAnswers = ParseQuery.getQuery("Blog_Answers");
        mAnswers.whereContains("question_id", qn_id );
        mAnswers.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                Log.i("number of objects ", String.valueOf(objects.size()));
                DB_Answers = new ArrayList<>();
                if(e == null){
                    if( objects.size() > 0){
                        for(ParseObject obj : objects){
                            DB_Answers.add(obj.getString("answer"));
                        }
                        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1 , DB_Answers);
                        answersList.setAdapter(adapter);
                    }else{
                        Toast.makeText(getApplicationContext() , "no answers found for this question" , Toast.LENGTH_SHORT).show();
                    }
                }else{
                    e.printStackTrace();
                }
            }
        });
        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext() , question_detail.class);
                in.putExtra("question" , question );
                startActivity(in);
            }
        });


    }
}
