package com.example.hitcompanion.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hitcompanion.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class answerQuestion extends AppCompatActivity {

    EditText question1 , answer ;
    Button back2 , submit ;
//    ImageButton imgBtn3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        question1 = findViewById(R.id.question1);
        answer = findViewById(R.id.answer);
        back2 = findViewById(R.id.back2);
        submit = findViewById(R.id.submit);

//        imgBtn3 = findViewById(R.id.imgBtn3);

//  RECEIVING QN FROM LAST ACTIVITY
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String question = intent.getStringExtra("question");

        question1.setText(question);

//   SENDING USER ANSWER TO DATABASE

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( answer.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext() , "You have not entered your answer" , Toast.LENGTH_SHORT).show();
                }else{
                    ParseObject blog = new ParseObject("Blog_Answers");
                    blog.put("question_id", id );
                    blog.put("answer", answer.getText().toString().trim() );
                    blog.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                Toast.makeText(getApplicationContext() , "Your answer has been submitted" , Toast.LENGTH_SHORT).show();
                                answer.setText("");
                                Intent intent1 = new Intent(getApplicationContext() , question_detail.class );
                                intent1.putExtra("question" , question );
                                startActivity(intent1);
                            }else{
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext() , "Couldn't submit your" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });
//   BACK BTN
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext() , question_detail.class);
                in.putExtra("question" , question );
                startActivity(in);
            }
        });





    }
}
