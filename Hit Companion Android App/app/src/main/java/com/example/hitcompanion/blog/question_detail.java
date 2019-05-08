package com.example.hitcompanion.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;

public class question_detail extends AppCompatActivity {
    Button answerQn , viewAnswers;
    ImageButton imgBtn ;
    TextView qTopic , qCreatedAt ;
    EditText qQuestion ;
    Date createdAt;
    String id , topic , body ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

//  DECLARATION OF TEXTVIEWS
        qTopic = findViewById(R.id.qTopic);
        qQuestion = findViewById(R.id.question);
        qQuestion.setEnabled(false);
        qCreatedAt = findViewById(R.id.createdAt);
//  DECLARATION OF BUTTONS

        answerQn = findViewById(R.id.answerQn);
        viewAnswers = findViewById(R.id.viewAnswers);
        imgBtn = findViewById(R.id.imgBtn5);


//  RECEIVING QN FROM LAST ACTIVITY
        Intent intent = getIntent();
        String question = intent.getStringExtra("question") ;
//  MAKEING A PARSE QUERY TO RETRIVE QN DETAILS
        ParseQuery<ParseObject> questionDetail = ParseQuery.getQuery("Blog");
        questionDetail.whereEqualTo("body",question);
        questionDetail.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null && objects != null ){
                   for (ParseObject object : objects){
                       id = object.getObjectId();
                       topic = object.getString("topic");
                       body = object.getString("body");
                       createdAt = object.getCreatedAt();
                   }
                    qTopic.setText(topic);
                    qQuestion.setText(body);
                    qCreatedAt.setText(String.valueOf(createdAt));

                }
            }
        });

//   ON CLICK LISTENERS FOR ANSWER BTN
        answerQn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent answer = new Intent(getApplicationContext() , answerQuestion.class);
                answer.putExtra("id" , id);
                answer.putExtra("question" , body );
                startActivity(answer);
            }
        });
//   ON CLICK LISTENER FOR BACK BTN
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , blog_home.class));
            }
        });
//   ON CLICK LISTENER FOR VIEW ANSWERS BTN
        viewAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewAns = new Intent(getApplicationContext() , answers.class);
                viewAns.putExtra("id" , id );
                viewAns.putExtra("topic" , topic );
                viewAns.putExtra("question" , body );
                startActivity(viewAns);
            }
        });
    }
}
